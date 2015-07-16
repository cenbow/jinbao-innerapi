package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.dao.SkuCspuDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuCspuMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuCspuDaoImpl extends BaseDao<SkuCspu, Long> implements SkuCspuDao {

    @Autowired
    private SkuCspuMapper skuCspuMapper;

    @Override
    public GenericMapper<SkuCspu, Long> getMapper() {
        return this.skuCspuMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuCspu> skuCspuList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }

        return this.skuCspuMapper.batchInsert(splitNumber, skuCspuList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuCspu> skuCspuList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }

        return this.skuCspuMapper.batchUpdate(splitNumber, skuCspuList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuCspuMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuCspu> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuCspu>();
        }

        return this.skuCspuMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuCspu> skuCspuList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCspuList)) {
            return 0;
        }
        return this.skuCspuMapper.batchUpdateByKey(splitNumber, skuCspuList);
    }

}
