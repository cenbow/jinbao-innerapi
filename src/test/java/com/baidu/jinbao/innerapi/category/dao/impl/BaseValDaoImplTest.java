package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.dao.BaseValDao;

public class BaseValDaoImplTest extends AbstractDAOTests {
    @Resource
    private BaseValDao baseValDao;

    @Before
    public void setUp() {
        this.executeDatas("category/base_val_init.sql");
    }

    @Test
    public void testFindById() {
        BaseVal ret = this.baseValDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<BaseVal> baseVals = new ArrayList<BaseVal>();
        BaseVal baseVal1 = new BaseVal();
        baseVal1.setValue("testname1");
        BaseVal baseVal2 = new BaseVal();
        baseVal2.setValue("testname2");
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        this.baseValDao.batchInsert(baseVals);
        BaseVal ret = this.baseValDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getValue().equals("testname1"));
        ret = this.baseValDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getValue().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<BaseVal> baseVals = new ArrayList<BaseVal>();
        BaseVal baseVal1 = new BaseVal();
        baseVal1.setValue("testname1");
        BaseVal baseVal2 = new BaseVal();
        baseVal2.setValue("testname2");
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        this.baseValDao.batchInsert(baseVals);
        baseVal1.setId(1L);
        baseVal1.setValue("testname3");
        baseVal2.setId(2L);
        baseVal2.setValue("testname4");
        this.baseValDao.batchUpdate(baseVals);
    }

    @Test
    public void testBatchSelect() {
        List<Long> baseValIds = new ArrayList<Long>();
        baseValIds.add(1L);
        List<BaseVal> ret = baseValDao.batchSelect(baseValIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> baseValIds = new ArrayList<Long>();
        baseValIds.add(2L);
        baseValIds.add(3L);
        List<BaseVal> ret = baseValDao.batchSelect(baseValIds);
        Assert.assertNotNull(ret);
        this.baseValDao.batchDelete(baseValIds);
        ret = baseValDao.batchSelect(baseValIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
