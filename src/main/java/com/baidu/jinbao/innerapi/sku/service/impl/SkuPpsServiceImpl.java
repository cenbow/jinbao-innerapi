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
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuPpsDao;
import com.baidu.jinbao.innerapi.sku.service.SkuPpsService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuPpsService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuPpsServiceImpl extends BaseService<SkuPps, Long> implements SkuPpsService {

    @Autowired
    private SkuPpsDao skuPpsDao;
    @Autowired
    private SkuInfoDao skuInfoDao;
    
    @Override
    public GenericMapperDao<SkuPps, Long> getDao() {
        return skuPpsDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuPps> skuPpsList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuPpsList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuPpsDao.batchInsert(splitNumber, filterAndSetSkuInnerId(splitNumber, skuPpsList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuPps> skuPpsList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuPpsList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        // 按skuid, regionid, terminal更新
        return this.skuPpsDao.batchUpdateByKey(splitNumber, skuPpsList);
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuPpsDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuPps> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuPpsDao.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuPps> skuPpsList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuPpsList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuPpsDao.batchInsert(splitNumber, skuPpsList);
    }
    
    @Override
    public Integer updateInsertRecord(String splitDbInfo, SkuPps skuPps) {
        if (splitDbInfo == null || skuPps == null || skuPps.getSkuid() == null || skuPps.getRegionid() == null
                || skuPps.getTerminal() == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        skuPpsList.add(skuPps);
        // 按skuid, regionid, terminal获取
        List<SkuPps> querySkuPpsList = this.skuPpsDao.batchSelectByKey(splitNumber, skuPpsList);

        if (querySkuPpsList.size() == 0) {
            // 不存在则插入
            if (!skuPps.checkRequiredField()) {
                throw new RuntimeException("SkuPps required fields is empty!");
            }
            // 设置默认值
            skuPps.setDefaultValue();
            this.skuPpsDao.batchInsert(splitNumber, skuPpsList);
        } else {
            SkuPps querySkuPps = querySkuPpsList.get(0);
            skuPps.mergeValue(querySkuPps);
            // 存在且不相同则更新
            if (!querySkuPps.signature().equals(skuPps.signature())) {
                this.skuPpsDao.batchUpdateByKey(splitNumber, skuPpsList);
            }
        }
        return 1;
    }
    
    private List<SkuPps> filterAndSetSkuInnerId(Integer splitNumber, List<SkuPps> skuPpsList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        for (SkuPps skuPps : skuPpsList) {
            skuHashKeyList.add((long) skuPps.getSkuid().hashCode());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        List<SkuPps> querySkuPpsList = this.skuPpsDao.batchSelectByKey(splitNumber, skuPpsList);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        Map<String, SkuPps> querySkuPpsMap = new HashMap<String, SkuPps>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }
        for (SkuPps skuPps : querySkuPpsList) {
            querySkuPpsMap.put(skuPps.getSkuid() + skuPps.getRegionid() + skuPps.getTerminal(), skuPps);
        }
        List<SkuPps> ret = new ArrayList<SkuPps>();
        for (SkuPps skuPps : skuPpsList) {
            if (!querySkuPpsMap.containsKey(skuPps.getSkuid() + skuPps.getRegionid() + skuPps.getTerminal()) 
                    && skuInfoMap.containsKey(skuPps.getSkuid())) {
                skuPps.setSkuInnerid(skuInfoMap.get(skuPps.getSkuid()));
                ret.add(skuPps);
            }
        }
        return ret;
    }

}
