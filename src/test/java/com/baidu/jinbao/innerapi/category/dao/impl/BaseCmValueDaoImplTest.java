package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;
import com.baidu.jinbao.innerapi.category.dao.BaseCmValueDao;

public class BaseCmValueDaoImplTest extends AbstractDAOTests {
    @Resource
    private BaseCmValueDao baseCmValueDao;

    @Before
    public void setUp() {
        this.executeDatas("category/base_cm_value_init.sql");
    }

    @Test
    public void testFindById() {
        BaseCmValue ret = this.baseCmValueDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<BaseCmValue> baseCmValues = new ArrayList<BaseCmValue>();
        BaseCmValue baseCmValue1 = new BaseCmValue();
        baseCmValue1.setValue("test1");
        BaseCmValue baseCmValue2 = new BaseCmValue();
        baseCmValue2.setValue("test2");
        baseCmValues.add(baseCmValue1);
        baseCmValues.add(baseCmValue2);
        this.baseCmValueDao.batchInsert(baseCmValues);
        BaseCmValue ret = this.baseCmValueDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getValue().equals("test1"));
        ret = this.baseCmValueDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getValue().equals("test2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<BaseCmValue> baseCmValues = new ArrayList<BaseCmValue>();
        BaseCmValue baseCmValue1 = new BaseCmValue();
        baseCmValue1.setValue("test1");
        BaseCmValue baseCmValue2 = new BaseCmValue();
        baseCmValue2.setValue("test2");
        baseCmValues.add(baseCmValue1);
        baseCmValues.add(baseCmValue2);
        this.baseCmValueDao.batchInsert(baseCmValues);
        baseCmValue1.setId(1L);
        baseCmValue1.setValue("test3");
        baseCmValue2.setId(2L);
        baseCmValue2.setValue("test4");
        this.baseCmValueDao.batchUpdate(baseCmValues);
    }

    @Test
    public void testBatchSelect() {
        List<Long> baseCmValueIds = new ArrayList<Long>();
        baseCmValueIds.add(1L);
        List<BaseCmValue> ret = baseCmValueDao.batchSelect(baseCmValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> baseCmValueIds = new ArrayList<Long>();
        baseCmValueIds.add(2L);
        baseCmValueIds.add(3L);
        List<BaseCmValue> ret = baseCmValueDao.batchSelect(baseCmValueIds);
        Assert.assertNotNull(ret);
        this.baseCmValueDao.batchDelete(baseCmValueIds);
        ret = baseCmValueDao.batchSelect(baseCmValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
