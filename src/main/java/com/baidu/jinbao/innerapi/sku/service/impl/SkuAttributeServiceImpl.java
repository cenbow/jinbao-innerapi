/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.sku.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuAttributeDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuAttributeService;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.util.SignatureUtil;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuAttributeService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuAttributeServiceImpl extends BaseService<SkuAttribute, Long> implements SkuAttributeService {

    @Autowired
    private SkuAttributeDao skuAttributeDao;
    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuInfoService skuInfoService;

    @Override
    public GenericMapperDao<SkuAttribute, Long> getDao() {
        return skuAttributeDao;
    }

    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuAttributeList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuAttributeDao.batchInsert(splitNumber, skuAttributeList);
    }
    
    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuAttributeList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        skuAttributeList = filterAndSetSkuInnerId(splitNumber, skuAttributeList);
        Integer successNum = this.skuAttributeDao.batchInsert(splitNumber, skuAttributeList);
        successNum = Math.min(successNum, updateSignature(splitDbInfo,  skuAttributeList, false));
        return successNum;
    }

    @Override
    public Integer updateBySkuId(String splitDbInfo, SkuAttribute skuAttribute) {
        if ((splitDbInfo == null) || skuAttribute == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuAttributeDao.updateBySkuId(splitNumber, skuAttribute);
    }
    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuAttributeList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        List<Long> idList = new ArrayList<Long>();
        for (SkuAttribute skuAttribute : skuAttributeList) {
            idList.add(skuAttribute.getId());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setIdList(idList);
        // 获取数据库中的值(由于property_hash是由property_id和property_values计算得到，需要先计算新hash值后更新
        List<SkuAttribute> queryList = this.skuAttributeDao.batchSelect(splitNumber, condition);
        Map<Long, SkuAttribute> queryMap = new HashMap<Long, SkuAttribute>();
        for (SkuAttribute skuAttribute : queryList) {
            queryMap.put(skuAttribute.getId(), skuAttribute);
        }
        for (SkuAttribute skuAttribute : skuAttributeList) {
            // 合并值，计算property_hash
            skuAttribute.mergeValue(queryMap.get(skuAttribute.getId()));
        }
        Integer successNum = this.skuAttributeDao.batchUpdate(splitNumber, skuAttributeList);
        // 更新签名
        return successNum = Math.min(successNum, updateSignature(splitDbInfo, skuAttributeList, false));
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        Integer successNum =
                this.updateSignature(splitDbInfo, this.skuAttributeDao.batchSelect(splitNumber, condition), true);
        return Math.min(successNum, this.skuAttributeDao.batchDelete(splitNumber, condition));
    }

    @Override
    public List<SkuAttribute> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuAttributeDao.batchSelect(splitNumber, condition);
    }
    
    private List<SkuAttribute> filterAndSetSkuInnerId(Integer splitNumber, List<SkuAttribute> skuAttributeList) {

        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuAttribute skuAttribute : skuAttributeList) {
            skuHashKeyList.add((long) skuAttribute.getSkuid().hashCode());
            skuIdList.add(skuAttribute.getSkuid());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        List<SkuAttribute> querySkuAttributeList = this.skuAttributeDao.batchSelect(splitNumber, condition);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        Map<String, SkuAttribute> querySkuAttributeMap = new HashMap<String, SkuAttribute>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }

        for (SkuAttribute skuAttribute : querySkuAttributeList) {
            querySkuAttributeMap.put(skuAttribute.getSkuid(), skuAttribute);
        }
        List<SkuAttribute> ret = new ArrayList<SkuAttribute>();
        for (SkuAttribute skuAttribute : skuAttributeList) {
            if (!querySkuAttributeMap.containsKey(skuAttribute.getSkuid())
                    && skuInfoMap.containsKey(skuAttribute.getSkuid())) {
                skuAttribute.setSkuInnerid(skuInfoMap.get(skuAttribute.getSkuid()));
                ret.add(skuAttribute);
            }
        }
        return ret;
    }

    private Integer updateSignature(String splitDbInfo, List<SkuAttribute> skuAttributeList, boolean isDelete) {
        List<String> signatureList = new ArrayList<String>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuAttribute skuAttribute : skuAttributeList) {
            if (isDelete) {
                signatureList.add("");
            } else {
                signatureList.add(skuAttribute.signature());
            }
            skuIdList.add(skuAttribute.getSkuid());
        }
        return this.skuInfoService.updateSignature(splitDbInfo, skuIdList, signatureList,
                SignatureUtil.SKU_ATTRIBUTE);
    }
}
