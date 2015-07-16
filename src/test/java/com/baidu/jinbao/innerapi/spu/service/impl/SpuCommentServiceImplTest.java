package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.dao.SpuCommentDao;

@Service
public class SpuCommentServiceImplTest {
    @Tested
    private SpuCommentServiceImpl spuCommentService;

    @Injectable
    private SpuCommentDao spuCommentDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                spuCommentDao.selectByPrimaryKey(anyLong);
                result = new SpuComment();
            }
        };
        SpuComment ret = spuCommentService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                spuCommentDao.batchInsert((List<SpuComment>) any);
            }
        };
        List<SpuComment> spuComments = new ArrayList<SpuComment>();
        SpuComment spuComment1 = new SpuComment();
        spuComment1.setSpuid(1L);
        SpuComment spuComment2 = new SpuComment();
        spuComment2.setSpuid(2L);
        spuComments.add(spuComment1);
        spuComments.add(spuComment2);
        this.spuCommentDao.batchInsert(spuComments);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                spuCommentDao.batchUpdate((List<SpuComment>) any);
            }
        };
        List<SpuComment> spuComments = new ArrayList<SpuComment>();
        SpuComment spuComment1 = new SpuComment();
        spuComment1.setSpuid(3L);
        SpuComment spuComment2 = new SpuComment();
        spuComment2.setSpuid(4L);
        spuComments.add(spuComment1);
        spuComments.add(spuComment2);
        this.spuCommentDao.batchUpdate(spuComments);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                spuCommentDao.batchSelect((List<Long>) any);
                result = new ArrayList<SpuComment>();
            }
        };
        List<Long> spuCommentIds = new ArrayList<Long>();
        spuCommentIds.add(1L);
        List<SpuComment> ret = spuCommentDao.batchSelect(spuCommentIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                spuCommentDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> spuCommentIds = new ArrayList<Long>();
        spuCommentIds.add(2L);
        spuCommentIds.add(3L);
        this.spuCommentDao.batchDelete(spuCommentIds);
    }
}
