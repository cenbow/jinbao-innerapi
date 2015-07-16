package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.dao.SkuCdtDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuCdtMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuCdtDaoImpl extends BaseDao<SkuCdt, Long> implements SkuCdtDao {

    @Autowired
    private SkuCdtMapper skuCdtMapper;

    @Override
    public GenericMapper<SkuCdt, Long> getMapper() {
        return this.skuCdtMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuCdt> skuCdtList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }

        return this.skuCdtMapper.batchInsert(splitNumber, skuCdtList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuCdt> skuCdtList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }

        return this.skuCdtMapper.batchUpdate(splitNumber, skuCdtList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuCdtMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuCdt> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuCdt>();
        }

        return this.skuCdtMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuCdt> skuCdtList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCdtList)) {
            return 0;
        }
        return this.skuCdtMapper.batchUpdateByKey(splitNumber, skuCdtList);
    }
}
