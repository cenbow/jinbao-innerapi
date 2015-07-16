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
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCommentDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuCommentService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("skuCommentService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuCommentServiceImpl extends BaseService<SkuComment, Long> implements SkuCommentService {

    @Autowired
    private SkuCommentDao skuCommentDao;
    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public GenericMapperDao<SkuComment, Long> getDao() {
        return skuCommentDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuComment> skuCommentList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCommentDao.batchInsert(splitNumber, filterAndSetSkuInnerId(splitNumber, skuCommentList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuComment> skuCommentList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCommentDao.batchUpdateByKey(splitNumber, skuCommentList);
    }

    @Override
    public Integer updateInsertRecord(String splitDbInfo, SkuComment skuComment) {
        if (splitDbInfo == null || skuComment == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add(skuComment.getSkuid());
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        // 按skuid获取
        List<SkuComment> querySkuCommentList = this.skuCommentDao.batchSelect(splitNumber, condition);
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        skuCommentList.add(skuComment);

        if (querySkuCommentList.size() == 0) {
            // 不存在则插入
            if (!skuComment.checkRequiredField()) {
                throw new RuntimeException("SkuComment required fields is empty!");
            }
            // 设置默认值
            skuComment.setDefaultValue();
            this.skuCommentDao.batchInsert(splitNumber, skuCommentList);
        } else {
            SkuComment querySkuComment = querySkuCommentList.get(0);
            skuComment.mergeValue(querySkuComment);
            if (!querySkuComment.signature().equals(skuComment.signature())) {
                // 存在且签名不相同则更新
                this.skuCommentDao.batchUpdateByKey(splitNumber, skuCommentList);
            }
        }
        return null;
    }
    
    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCommentDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuComment> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCommentDao.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer directInsertRecords(String splitDbInfo, List<SkuComment> skuCommentList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuCommentDao.batchInsert(splitNumber, skuCommentList);
    }
    
    private List<SkuComment> filterAndSetSkuInnerId(Integer splitNumber, List<SkuComment> skuCommentList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        List<String> skuIdList = new ArrayList<String>();
        for (SkuComment skuComment : skuCommentList) {
            skuHashKeyList.add((long) skuComment.getSkuid().hashCode());
            skuIdList.add(skuComment.getSkuid());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }

        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuComment> querySkuCommentList = this.skuCommentDao.batchSelect(splitNumber, condition);
        Map<String, SkuComment> querySkuCommentMap = new HashMap<String, SkuComment>();
        for (SkuComment skuComment : querySkuCommentList) {
            querySkuCommentMap.put(skuComment.getSkuid(), skuComment);
        }
        List<SkuComment> ret = new ArrayList<SkuComment>();
        for (SkuComment skuComment : skuCommentList) {
            if (!querySkuCommentMap.containsKey(skuComment.getSkuid()) 
                    && skuInfoMap.containsKey(skuComment.getSkuid())) {
                skuComment.setSkuInnerid(skuInfoMap.get(skuComment.getSkuid()));
                ret.add(skuComment);
            }
        }
        return ret;
    }
}
