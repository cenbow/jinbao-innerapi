package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.dao.SkuCommentDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.SkuCommentMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class SkuCommentDaoImpl extends BaseDao<SkuComment, Long> implements SkuCommentDao {

    @Autowired
    private SkuCommentMapper skuCommentMapper;

    @Override
    public GenericMapper<SkuComment, Long> getMapper() {
        return this.skuCommentMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<SkuComment> skuCommentList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }

        return this.skuCommentMapper.batchInsert(splitNumber, skuCommentList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<SkuComment> skuCommentList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }

        return this.skuCommentMapper.batchUpdate(splitNumber, skuCommentList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.skuCommentMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuComment> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<SkuComment>();
        }

        return this.skuCommentMapper.batchSelect(splitNumber, condition);
    }

    @Override
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuComment> skuCommentList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(skuCommentList)) {
            return 0;
        }
        return this.skuCommentMapper.batchUpdateByKey(splitNumber, skuCommentList);
    }

}
