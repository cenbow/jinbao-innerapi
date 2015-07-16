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
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuLevelDao;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.service.SkuLevelService;
import com.baidu.jinbao.innerapi.sku.util.SignatureUtil;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuLevelService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuLevelServiceImpl extends BaseService<SkuLevel, Long> implements SkuLevelService {

    @Autowired
    private SkuLevelDao skuLevelDao;
    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuInfoService skuInfoService;
    
    @Override
    public GenericMapperDao<SkuLevel, Long> getDao() {
        return skuLevelDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuLevel> skuLevelList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuLevelList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        skuLevelList = filterAndSetSkuInnerId(splitNumber, skuLevelList);
        Integer successNum = this.skuLevelDao.batchInsert(splitNumber, skuLevelList);
        successNum = Math.min(successNum, this.updateSignature(splitDbInfo, skuLevelList, false));
        return successNum;
    }

    @Override
    public Integer updateBySkuId(String splitDbInfo, SkuLevel skuLevel) {
        if ((splitDbInfo == null) || skuLevel == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuLevelDao.updateBySkuId(splitNumber, skuLevel);
    }
    
    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuLevel> skuLevelList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuLevelList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        Integer successNum = this.skuLevelDao.batchUpdate(splitNumber, skuLevelList);
        List<Long> idList = new ArrayList<Long>();
        for (SkuLevel skuLevel : skuLevelList) {
            idList.add(skuLevel.getId());

        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setIdList(idList);
        skuLevelList = this.skuLevelDao.batchSelect(splitNumber, condition);
        // 更新签名
        successNum = Math.min(successNum, this.updateSignature(splitDbInfo, skuLevelList, false));
        return successNum;
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        Integer successNum =
                this.updateSignature(splitDbInfo, this.skuLevelDao.batchSelect(splitNumber, condition), true);
        return Math.min(successNum, this.skuLevelDao.batchDelete(splitNumber, condition));
    }

    @Override
    public List<SkuLevel> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuLevelDao.batchSelect(splitNumber, condition);
    }
    
    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuLevel> skuLevelList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuLevelList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuLevelDao.batchInsert(splitNumber, skuLevelList);
    }
    
    private List<SkuLevel> filterAndSetSkuInnerId(Integer splitNumber, List<SkuLevel> skuLevelList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuLevel skuLevel : skuLevelList) {
            skuHashKeyList.add((long) skuLevel.getSkuid().hashCode());
            skuIdList.add(skuLevel.getSkuid());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        List<SkuLevel> querySkuLevelList = this.skuLevelDao.batchSelect(splitNumber, condition);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        Map<String, SkuLevel> skuLevelMap = new HashMap<String, SkuLevel>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }
        for (SkuLevel skuLevel : querySkuLevelList) {
            skuLevelMap.put(skuLevel.getSkuid(), skuLevel);
        }
        List<SkuLevel> ret = new ArrayList<SkuLevel>();
        for (SkuLevel skuLevel : skuLevelList) {
            if (!skuLevelMap.containsKey(skuLevel.getSkuid()) && skuInfoMap.containsKey(skuLevel.getSkuid())) {
                skuLevel.setSkuInnerid(skuInfoMap.get(skuLevel.getSkuid()));
                ret.add(skuLevel);
            }
        }
        return ret;
    }

    private Integer updateSignature(String splitDbInfo, List<SkuLevel> skuLevelList, boolean isDelete) {
        List<String> signatureList = new ArrayList<String>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuLevel skuLevel : skuLevelList) {
            if (isDelete) {
                signatureList.add("");
            } else {
                signatureList.add(skuLevel.signature());
            }
            skuIdList.add(skuLevel.getSkuid());
        }
        return this.skuInfoService.updateSignature(splitDbInfo, skuIdList, signatureList,
                SignatureUtil.SKU_LEVEL);
    }
}
