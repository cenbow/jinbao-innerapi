package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.dao.SkuAttributeDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuAttributeMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuAttributeDaoImpl extends BaseDao<SkuAttribute, Long> implements SkuAttributeDao {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public GenericMapper<SkuAttribute, Long> getMapper() {
        return this.skuAttributeMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuAttribute> skuAttributeList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuAttributeList)) {
            return 0;
        }

        return this.skuAttributeMapper.batchInsert(splitNumber, skuAttributeList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuAttribute> skuAttributeList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuAttributeList)) {
            return 0;
        }

        return this.skuAttributeMapper.batchUpdate(splitNumber, skuAttributeList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuAttributeMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuAttribute> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuAttribute>();
        }

        return this.skuAttributeMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer updateBySkuId(Integer splitNumber, SkuAttribute skuAttribute) {
        if ((splitNumber == null) || (skuAttribute == null)) {
            return 0;
        }
        return this.skuAttributeMapper.updateBySkuId(splitNumber, skuAttribute);
    }

}
