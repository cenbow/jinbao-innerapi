package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.dao.SpuCommentDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.SpuCommentMapper;

@Repository
public class SpuCommentDaoImpl extends BaseDao<SpuComment, Long> implements SpuCommentDao {
    @Autowired
    private SpuCommentMapper spuCommentMapper;

    @Override
    public GenericMapper<SpuComment, Long> getMapper() {
        return this.spuCommentMapper;
    }

    @Override
    public Integer batchInsert(List<SpuComment> spuComments) {
        if (CollectionUtils.isEmpty(spuComments)) {
            return 0;
        }
        return this.spuCommentMapper.batchInsert(spuComments);
    }

    @Override
    public Integer batchUpdate(List<SpuComment> spuComments) {
        if (CollectionUtils.isEmpty(spuComments)) {
            return 0;
        }
        return this.spuCommentMapper.batchUpdate(spuComments);
    }

    @Override
    public Integer batchDelete(List<Long> spuCommentIds) {
        if (CollectionUtils.isEmpty(spuCommentIds)) {
            return 0;
        }
        return this.spuCommentMapper.batchDelete(spuCommentIds);
    }

    @Override
    public List<SpuComment> batchSelect(List<Long> spuCommentIds) {
        if (CollectionUtils.isEmpty(spuCommentIds)) {
            return new ArrayList<SpuComment>();
        }
        return this.spuCommentMapper.batchSelect(spuCommentIds);
    }
}
