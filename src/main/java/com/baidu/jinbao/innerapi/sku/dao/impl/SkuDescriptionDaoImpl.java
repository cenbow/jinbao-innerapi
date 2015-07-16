package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.dao.SkuDescriptionDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuDescriptionMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuDescriptionDaoImpl extends BaseDao<SkuDescription, Long> implements SkuDescriptionDao {

    @Autowired
    private SkuDescriptionMapper skuDescriptionMapper;

    @Override
    public GenericMapper<SkuDescription, Long> getMapper() {
        return this.skuDescriptionMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuDescription> skuDescriptionList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuDescriptionList)) {
            return 0;
        }

        return this.skuDescriptionMapper.batchInsert(splitNumber, skuDescriptionList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuDescription> skuDescriptionList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuDescriptionList)) {
            return 0;
        }

        return this.skuDescriptionMapper.batchUpdate(splitNumber, skuDescriptionList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuDescriptionMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuDescription> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuDescription>();
        }

        return this.skuDescriptionMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer updateBySkuId(Integer splitNumber, SkuDescription skuDescription) {
        if ((splitNumber == null) || (skuDescription == null)) {
            return 0;
        }
        return this.skuDescriptionMapper.updateBySkuId(splitNumber, skuDescription);
    }

}
