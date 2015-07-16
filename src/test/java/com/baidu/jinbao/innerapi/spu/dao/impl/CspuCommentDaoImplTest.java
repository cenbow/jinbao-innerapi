package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.dao.CspuCommentDao;

public class CspuCommentDaoImplTest extends AbstractDAOTests {
    @Resource
    private CspuCommentDao cspuCommentDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/cspu_comment_init.sql");
    }

    @Test
    public void testFindById() {
        CspuComment ret = this.cspuCommentDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CspuComment> cspuCommentis = new ArrayList<CspuComment>();
        CspuComment cspuComment1 = new CspuComment();
        cspuComment1.setCspuid(1L);
        CspuComment cspuComment2 = new CspuComment();
        cspuComment2.setCspuid(2L);
        cspuCommentis.add(cspuComment1);
        cspuCommentis.add(cspuComment2);
        this.cspuCommentDao.batchInsert(cspuCommentis);
        CspuComment ret = this.cspuCommentDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(1L));
        ret = this.cspuCommentDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(2L));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CspuComment> cspuCommentis = new ArrayList<CspuComment>();
        CspuComment cspuComment1 = new CspuComment();
        cspuComment1.setCspuid(1L);
        CspuComment cspuComment2 = new CspuComment();
        cspuComment2.setCspuid(2L);
        cspuCommentis.add(cspuComment1);
        cspuCommentis.add(cspuComment2);
        this.cspuCommentDao.batchInsert(cspuCommentis);
        cspuComment1.setId(1L);
        cspuComment1.setCspuid(3L);
        cspuComment2.setId(2L);
        cspuComment2.setCspuid(4L);
        this.cspuCommentDao.batchUpdate(cspuCommentis);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cspuCommentIds = new ArrayList<Long>();
        cspuCommentIds.add(1L);
        List<CspuComment> ret = cspuCommentDao.batchSelect(cspuCommentIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cspuCommentIds = new ArrayList<Long>();
        cspuCommentIds.add(2L);
        cspuCommentIds.add(3L);
        List<CspuComment> ret = cspuCommentDao.batchSelect(cspuCommentIds);
        Assert.assertNotNull(ret);
        this.cspuCommentDao.batchDelete(cspuCommentIds);
        ret = cspuCommentDao.batchSelect(cspuCommentIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
