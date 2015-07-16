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
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCspuDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuCspuService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuCspuService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuCspuServiceImpl extends BaseService<SkuCspu, Long> implements SkuCspuService {

    @Autowired
    private SkuCspuDao skuCspuDao;
    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public GenericMapperDao<SkuCspu, Long> getDao() {
        return skuCspuDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuCspu> skuCspuList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCspuDao.batchInsert(splitNumber, filterAndSetSkuInnerId(splitNumber, skuCspuList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuCspu> skuCspuList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.skuCspuDao.batchUpdateByKey(splitNumber, skuCspuList);
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCspuDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuCspu> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCspuDao.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuCspu> skuCspuList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCspuDao.batchInsert(splitNumber, skuCspuList);
    }
    
    @Override
    public Integer updateInsertRecord(String splitDbInfo, SkuCspu skuCspu) {
        if (splitDbInfo == null || skuCspu == null || skuCspu.getSkuid() == null || skuCspu.getType() == null) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add(skuCspu.getSkuid());
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        // 按skuid获取
        List<SkuCspu> querySkuCspuList = this.skuCspuDao.batchSelect(splitNumber, condition);
        Map<String, SkuCspu> querySkuCspuMap = new HashMap<String, SkuCspu>();
        for (SkuCspu querySkuCspu : querySkuCspuList) {
            querySkuCspuMap.put(querySkuCspu.getSkuid() + querySkuCspu.getType(), querySkuCspu);
        }
        List<SkuCspu> skuCspuList = new ArrayList<SkuCspu>();
        skuCspuList.add(skuCspu);
        if (!querySkuCspuMap.containsKey(skuCspu.getSkuid() + skuCspu.getType())) {
            // 不存在则插入
            if (!skuCspu.checkRequiredField()) {
                throw new RuntimeException("SkuCspu required fields is empty!");
            }
            // 设置默认值
            skuCspu.setDefaultValue();
            this.skuCspuDao.batchInsert(splitNumber, skuCspuList);
        } else {
            SkuCspu querySkuCspu = querySkuCspuMap.get(skuCspu.getSkuid() + skuCspu.getType());
            skuCspu.mergeValue(querySkuCspu);
            // 存在且不相同则更新
            if (!querySkuCspu.signature().equals(skuCspu.signature())) {
                // 按照主键更新
                skuCspu.setId(querySkuCspuMap.get(skuCspu.getSkuid() + skuCspu.getType()).getId());

                this.skuCspuDao.batchUpdate(splitNumber, skuCspuList);
            }
        }

        return 1;
    }
    
    private List<SkuCspu> filterAndSetSkuInnerId(Integer splitNumber, List<SkuCspu> skuCspuList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuCspu skuCspu : skuCspuList) {
            skuHashKeyList.add((long) skuCspu.getSkuid().hashCode());
            skuIdList.add(skuCspu.getSkuid());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();      
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }

        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuCspu> querySkuCspuList = this.skuCspuDao.batchSelect(splitNumber, condition);
        Map<String, SkuCspu> querySkuCspuMap = new HashMap<String, SkuCspu>();
        for (SkuCspu skuCspu : querySkuCspuList) {
            querySkuCspuMap.put(skuCspu.getSkuid() + skuCspu.getType(), skuCspu);
        }
        List<SkuCspu> ret = new ArrayList<SkuCspu>();
        for (SkuCspu skuCspu : skuCspuList) {
            if (!querySkuCspuMap.containsKey(skuCspu.getSkuid() + skuCspu.getType())
                    && skuInfoMap.containsKey(skuCspu.getSkuid())) {
                skuCspu.setSkuInnerid(skuInfoMap.get(skuCspu.getSkuid()));
                ret.add(skuCspu);
            }
        }
        return ret;
    }
}
