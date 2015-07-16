package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.dao.CspuCommentDao;

@Service
public class CspuCommentServiceImplTest {
    @Tested
    private CspuCommentServiceImpl cspuCommentService;

    @Injectable
    private CspuCommentDao cspuCommentDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cspuCommentDao.selectByPrimaryKey(anyLong);
                result = new CspuComment();
            }
        };
        CspuComment ret = cspuCommentService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cspuCommentDao.batchInsert((List<CspuComment>) any);
            }
        };
        List<CspuComment> cspuCommentis = new ArrayList<CspuComment>();
        CspuComment cspuComment1 = new CspuComment();
        cspuComment1.setCspuid(1L);
        CspuComment cspuComment2 = new CspuComment();
        cspuComment2.setCspuid(2L);
        cspuCommentis.add(cspuComment1);
        cspuCommentis.add(cspuComment2);
        this.cspuCommentDao.batchInsert(cspuCommentis);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cspuCommentDao.batchUpdate((List<CspuComment>) any);
            }
        };
        List<CspuComment> cspuCommentis = new ArrayList<CspuComment>();
        CspuComment cspuComment1 = new CspuComment();
        cspuComment1.setCspuid(2L);
        CspuComment cspuComment2 = new CspuComment();
        cspuComment2.setCspuid(3L);
        cspuCommentis.add(cspuComment1);
        cspuCommentis.add(cspuComment2);
        this.cspuCommentDao.batchUpdate(cspuCommentis);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cspuCommentDao.batchSelect((List<Long>) any);
                result = new ArrayList<CspuComment>();
            }
        };
        List<Long> cspuCommentIds = new ArrayList<Long>();
        cspuCommentIds.add(1L);
        List<CspuComment> ret = cspuCommentDao.batchSelect(cspuCommentIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cspuCommentDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cspuCommentIds = new ArrayList<Long>();
        cspuCommentIds.add(2L);
        cspuCommentIds.add(3L);
        this.cspuCommentDao.batchDelete(cspuCommentIds);
    }
}
