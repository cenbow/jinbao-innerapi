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
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuDescriptionDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuDescriptionService;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.util.SignatureUtil;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuDescriptionService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuDescriptionServiceImpl extends BaseService<SkuDescription, Long> implements SkuDescriptionService {

    @Autowired
    private SkuDescriptionDao skuDescriptionDao;
    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuInfoService skuInfoService;
    
    @Override
    public GenericMapperDao<SkuDescription, Long> getDao() {
        return skuDescriptionDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuDescription> skuDescriptionList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuDescriptionList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        skuDescriptionList = filterAndSetSkuInnerId(splitNumber, skuDescriptionList);
        Integer successNum = this.skuDescriptionDao.batchInsert(splitNumber, skuDescriptionList);
        successNum = Math.min(successNum, this.updateSignature(splitDbInfo, skuDescriptionList, false));
        return successNum;
    }

    @Override
    public Integer updateBySkuId(String splitDbInfo, SkuDescription skuDescription) {
        if ((splitDbInfo == null) || skuDescription == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuDescriptionDao.updateBySkuId(splitNumber, skuDescription);
    }
    
    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuDescription> skuDescriptionList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuDescriptionList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        Integer successNum = this.skuDescriptionDao.batchUpdate(splitNumber, skuDescriptionList);
        List<Long> idList = new ArrayList<Long>();
        for (SkuDescription skuDescription : skuDescriptionList) {
            idList.add(skuDescription.getId());

        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setIdList(idList);
        skuDescriptionList = this.getRecords(splitDbInfo, condition);
        successNum = Math.min(successNum, this.updateSignature(splitDbInfo, skuDescriptionList, false));
        return successNum;
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        Integer successNum =
                this.updateSignature(splitDbInfo, this.skuDescriptionDao.batchSelect(splitNumber, condition), true);
        return Math.min(successNum, this.skuDescriptionDao.batchDelete(splitNumber, condition));
    }

    @Override
    public List<SkuDescription> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuDescriptionDao.batchSelect(splitNumber, condition);
    }
    
    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuDescription> skuDescriptionList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuDescriptionList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuDescriptionDao.batchInsert(splitNumber, skuDescriptionList);
    }
    
    private List<SkuDescription> filterAndSetSkuInnerId(Integer splitNumber, List<SkuDescription> skuDescriptionList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuDescription skuDescription : skuDescriptionList) {
            skuHashKeyList.add((long) skuDescription.getSkuid().hashCode());
            skuIdList.add(skuDescription.getSkuid());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        List<SkuDescription> querySkuDescriptionList = this.skuDescriptionDao.batchSelect(splitNumber, condition);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        Map<String, SkuDescription> querySkuDescriptionMap = new HashMap<String, SkuDescription>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }
        for (SkuDescription skuDescription : querySkuDescriptionList) {
            querySkuDescriptionMap.put(skuDescription.getSkuid(), skuDescription);
        }
        List<SkuDescription> ret = new ArrayList<SkuDescription>();
        for (SkuDescription skuDescription : skuDescriptionList) {
            if (!querySkuDescriptionMap.containsKey(skuDescription.getSkuid())
                    && skuInfoMap.containsKey(skuDescription.getSkuid())) {
                skuDescription.setSkuInnerid(skuInfoMap.get(skuDescription.getSkuid()));
                ret.add(skuDescription);
            }
        }
        return ret;
    }

    private Integer updateSignature(String splitDbInfo, List<SkuDescription> skuDescriptionList, boolean isDelete) {
        List<String> signatureList = new ArrayList<String>();
        List<String> skuIdList = new ArrayList<String>();
        
        for (SkuDescription skuDescription : skuDescriptionList) {
            if (isDelete) {
                signatureList.add("");
            } else {
                signatureList.add(skuDescription.signature());
            }
            skuIdList.add(skuDescription.getSkuid());
        }
        return this.skuInfoService.updateSignature(splitDbInfo, skuIdList, signatureList,
                SignatureUtil.SKU_DESCRIPTION);
    }
}
