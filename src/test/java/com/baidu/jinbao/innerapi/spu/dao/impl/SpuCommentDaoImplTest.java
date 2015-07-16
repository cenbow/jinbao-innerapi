package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.dao.SpuCommentDao;

public class SpuCommentDaoImplTest extends AbstractDAOTests {
    @Resource
    private SpuCommentDao spuCommentDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/spu_comment_init.sql");
    }

    @Test
    public void testFindById() {
        SpuComment ret = this.spuCommentDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<SpuComment> spuComments = new ArrayList<SpuComment>();
        SpuComment spuComment1 = new SpuComment();
        spuComment1.setSpuid(1L);
        SpuComment spuComment2 = new SpuComment();
        spuComment2.setSpuid(2L);
        spuComments.add(spuComment1);
        spuComments.add(spuComment2);
        this.spuCommentDao.batchInsert(spuComments);
        SpuComment ret = this.spuCommentDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getSpuid().equals(1L));
        ret = this.spuCommentDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getSpuid().equals(2L));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<SpuComment> spuComments = new ArrayList<SpuComment>();
        SpuComment spuComment1 = new SpuComment();
        spuComment1.setSpuid(1L);
        SpuComment spuComment2 = new SpuComment();
        spuComment2.setSpuid(2L);
        spuComments.add(spuComment1);
        spuComments.add(spuComment2);
        this.spuCommentDao.batchInsert(spuComments);
        spuComment1.setId(1L);
        spuComment1.setSpuid(3L);
        spuComment2.setId(2L);
        spuComment2.setSpuid(4L);
        this.spuCommentDao.batchUpdate(spuComments);
    }

    @Test
    public void testBatchSelect() {
        List<Long> spuCommentIds = new ArrayList<Long>();
        spuCommentIds.add(1L);
        List<SpuComment> ret = spuCommentDao.batchSelect(spuCommentIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> spuCommentIds = new ArrayList<Long>();
        spuCommentIds.add(2L);
        spuCommentIds.add(3L);
        List<SpuComment> ret = spuCommentDao.batchSelect(spuCommentIds);
        Assert.assertNotNull(ret);
        this.spuCommentDao.batchDelete(spuCommentIds);
        ret = spuCommentDao.batchSelect(spuCommentIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
