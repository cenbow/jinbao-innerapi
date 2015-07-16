package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.dao.SkuPpsDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuPpsMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuPpsDaoImpl extends BaseDao<SkuPps, Long> implements SkuPpsDao {

    @Autowired
    private SkuPpsMapper skuPpsMapper;

    @Override
    public GenericMapper<SkuPps, Long> getMapper() {
        return this.skuPpsMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuPps> skuPpsList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuPpsList)) {
            return 0;
        }

        return this.skuPpsMapper.batchInsert(splitNumber, skuPpsList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuPps> skuPpsList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuPpsList)) {
            return 0;
        }

        return this.skuPpsMapper.batchUpdate(splitNumber, skuPpsList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuPpsMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuPps> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuPps>();
        }

        return this.skuPpsMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public List<SkuPps> batchSelectByKey(Integer splitNumber, List<SkuPps> skuPpsList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuPpsList)) {
            return new ArrayList<SkuPps>();
        }
        return this.skuPpsMapper.batchSelectByKey(splitNumber, skuPpsList);
    }

    @Override
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuPps> skuPpsList) {
        return this.skuPpsMapper.batchUpdateByKey(splitNumber, skuPpsList);
    }
}
