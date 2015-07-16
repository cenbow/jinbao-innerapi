package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuInfoMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuInfoDaoImpl extends BaseDao<SkuInfo, Long> implements SkuInfoDao {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Override
    public GenericMapper<SkuInfo, Long> getMapper() {
        return this.skuInfoMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuInfo> skuInfoList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuInfoList)) {
            return 0;
        }

        return this.skuInfoMapper.batchInsert(splitNumber, skuInfoList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuInfo> skuInfoList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuInfoList)) {
            return 0;
        }

        return this.skuInfoMapper.batchUpdate(splitNumber, skuInfoList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuInfoMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuInfo> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuInfo>();
        }

        return this.skuInfoMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer insertWithSplitNumber(Integer splitNumber, SkuInfo skuInfo) {
        if ((splitNumber == null) || (skuInfo == null)) {
            return 0;
        }
        return this.skuInfoMapper.insertWithSplitNumber(splitNumber, skuInfo);
    }

    @Override
    public List<SkuInfo> batchSelectBySkuHashkey(Integer splitNumber, List<Long> skuHashKeyList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuHashKeyList)) {
            return new ArrayList<SkuInfo>();
        }
        return this.skuInfoMapper.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
    }

    @Override
    public Integer updateBySkuId(Integer splitNumber, SkuInfo skuInfo) {
        if ((splitNumber == null) || (skuInfo == null)) {
            return 0;
        }
        return this.skuInfoMapper.updateBySkuId(splitNumber, skuInfo);
    }

}
