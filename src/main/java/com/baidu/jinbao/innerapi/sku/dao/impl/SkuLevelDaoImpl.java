package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.dao.SkuLevelDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuLevelMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuLevelDaoImpl extends BaseDao<SkuLevel, Long> implements SkuLevelDao {

    @Autowired
    private SkuLevelMapper skuLevelMapper;

    @Override
    public GenericMapper<SkuLevel, Long> getMapper() {
        return this.skuLevelMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuLevel> skuLevelList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuLevelList)) {
            return 0;
        }
        return this.skuLevelMapper.batchInsert(splitNumber, skuLevelList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuLevel> skuLevelList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuLevelList)) {
            return 0;
        }

        return this.skuLevelMapper.batchUpdate(splitNumber, skuLevelList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuLevelMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuLevel> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuLevel>();
        }

        return this.skuLevelMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer updateBySkuId(Integer splitNumber, SkuLevel skuLevel) {
        if ((splitNumber == null) || (skuLevel == null)) {
            return 0;
        }
        return this.skuLevelMapper.updateBySkuId(splitNumber, skuLevel);
    }

}
