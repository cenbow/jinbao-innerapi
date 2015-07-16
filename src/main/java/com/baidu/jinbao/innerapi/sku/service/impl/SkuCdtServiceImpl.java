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
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCdtDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuCdtService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuCdtService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuCdtServiceImpl extends BaseService<SkuCdt, Long> implements SkuCdtService {

    @Autowired
    private SkuCdtDao skuCdtDao;
    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public GenericMapperDao<SkuCdt, Long> getDao() {
        return skuCdtDao;
    }

    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuCdt> skuCdtList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCdtDao.batchInsert(splitNumber, skuCdtList);
    }
    
    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuCdt> skuCdtList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCdtDao.batchInsert(splitNumber, filterAndSetSkuInnerId(splitNumber, skuCdtList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuCdt> skuCdtList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        
        return this.skuCdtDao.batchUpdateByKey(splitNumber, skuCdtList);
    }

    @Override
    public Integer updateInsertRecord(String splitDbInfo, SkuCdt skuCdt) {
        if (splitDbInfo == null || skuCdt == null || skuCdt.getSkuid() == null
                || skuCdt.getClassificationtype() == null) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add(skuCdt.getSkuid());
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        // 按skuid获取
        List<SkuCdt> querySkuCdtList = this.skuCdtDao.batchSelect(splitNumber, condition);
        Map<String, SkuCdt> querySkuCdtMap = new HashMap<String, SkuCdt>();
        for (SkuCdt querySkuCdt : querySkuCdtList) {
            querySkuCdtMap.put(querySkuCdt.getSkuid() + querySkuCdt.getClassificationtype(), querySkuCdt);
        }
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        skuCdtList.add(skuCdt);
        if (!querySkuCdtMap.containsKey(skuCdt.getSkuid() + skuCdt.getClassificationtype())) {
            // 不存在则插入
            if (!skuCdt.checkRequiredField()) {
                throw new RuntimeException("SkuCdt required fields is empty!");
            }
            // 设置默认值
            skuCdt.setDefaultValue();
            this.skuCdtDao.batchInsert(splitNumber, skuCdtList);
        } else {
            SkuCdt querySkuCdt = querySkuCdtMap.get(skuCdt.getSkuid() + skuCdt.getClassificationtype());
            skuCdt.mergeValue(querySkuCdt);
            // 存在且不相同则更新
            if (!querySkuCdt.signature().equals(skuCdt.signature())) {
                // 按照主键更新
                skuCdt.setId(querySkuCdtMap.get(skuCdt.getSkuid() + skuCdt.getClassificationtype()).getId());
                this.skuCdtDao.batchUpdate(splitNumber, skuCdtList);
            }
        }
        
        return 1;
    }
    
    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCdtDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuCdt> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCdtDao.batchSelect(splitNumber, condition);
    }

    private List<SkuCdt> filterAndSetSkuInnerId(Integer splitNumber, List<SkuCdt> skuCdtList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuCdt skuCdt : skuCdtList) {
            skuHashKeyList.add((long) skuCdt.getSkuid().hashCode());
            skuIdList.add(skuCdt.getSkuid());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuCdt> querySkuCdtList = this.skuCdtDao.batchSelect(splitNumber, condition);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        Map<String, SkuCdt> querySkuCdtMap = new HashMap<String, SkuCdt>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }
        for (SkuCdt skuCdt : querySkuCdtList) {
            querySkuCdtMap.put(skuCdt.getSkuid() + skuCdt.getClassificationtype(), skuCdt);
        }
        List<SkuCdt> ret = new ArrayList<SkuCdt>();
        for (SkuCdt skuCdt : skuCdtList) {
            if (!querySkuCdtMap.containsKey(skuCdt.getSkuid() + skuCdt.getClassificationtype()) 
                    && skuInfoMap.containsKey(skuCdt.getSkuid())) {
                skuCdt.setSkuInnerid(skuInfoMap.get(skuCdt.getSkuid()));
                ret.add(skuCdt);
            }
        }
        return ret;
    }
}
