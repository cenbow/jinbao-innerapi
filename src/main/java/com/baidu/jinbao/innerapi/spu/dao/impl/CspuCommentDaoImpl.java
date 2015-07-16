package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.dao.CspuCommentDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.CspuCommentMapper;

@Repository
public class CspuCommentDaoImpl extends BaseDao<CspuComment, Long> implements CspuCommentDao {
    @Autowired
    private CspuCommentMapper cspuCommentMapper;

    @Override
    public GenericMapper<CspuComment, Long> getMapper() {
        return this.cspuCommentMapper;
    }

    @Override
    public Integer batchInsert(List<CspuComment> cspuCommentis) {
        if (CollectionUtils.isEmpty(cspuCommentis)) {
            return 0;
        }
        return this.cspuCommentMapper.batchInsert(cspuCommentis);
    }

    @Override
    public Integer batchUpdate(List<CspuComment> cspuCommentis) {
        if (CollectionUtils.isEmpty(cspuCommentis)) {
            return 0;
        }
        return this.cspuCommentMapper.batchUpdate(cspuCommentis);
    }

    @Override
    public Integer batchDelete(List<Long> cspuCommentIds) {
        if (CollectionUtils.isEmpty(cspuCommentIds)) {
            return 0;
        }
        return this.cspuCommentMapper.batchDelete(cspuCommentIds);
    }

    @Override
    public List<CspuComment> batchSelect(List<Long> cspuCommentIds) {
        if (CollectionUtils.isEmpty(cspuCommentIds)) {
            return new ArrayList<CspuComment>();
        }
        return this.cspuCommentMapper.batchSelect(cspuCommentIds);
    }
}
