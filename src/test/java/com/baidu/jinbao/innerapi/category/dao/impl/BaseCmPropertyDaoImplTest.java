package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.dao.BaseCmPropertyDao;

public class BaseCmPropertyDaoImplTest extends AbstractDAOTests {
    @Resource
    private BaseCmPropertyDao baseCmPropertyDao;

    @Before
    public void setUp() {
        this.executeDatas("category/base_cm_property_init.sql");
    }

    @Test
    public void testFindById() {
        BaseCmProperty ret = this.baseCmPropertyDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<BaseCmProperty> baseCmProperties = new ArrayList<BaseCmProperty>();
        BaseCmProperty baseCmProperty1 = new BaseCmProperty();
        baseCmProperty1.setName("testname1");
        BaseCmProperty baseCmProperty2 = new BaseCmProperty();
        baseCmProperty2.setName("testname2");
        baseCmProperties.add(baseCmProperty1);
        baseCmProperties.add(baseCmProperty2);
        this.baseCmPropertyDao.batchInsert(baseCmProperties);
        BaseCmProperty ret = this.baseCmPropertyDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.baseCmPropertyDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<BaseCmProperty> baseCmProperties = new ArrayList<BaseCmProperty>();
        BaseCmProperty baseCmProperty1 = new BaseCmProperty();
        baseCmProperty1.setName("testname1");
        BaseCmProperty baseCmProperty2 = new BaseCmProperty();
        baseCmProperty2.setName("testname2");
        baseCmProperties.add(baseCmProperty1);
        baseCmProperties.add(baseCmProperty2);
        this.baseCmPropertyDao.batchInsert(baseCmProperties);
        baseCmProperty1.setId(1L);
        baseCmProperty1.setName("testname3");
        baseCmProperty2.setId(2L);
        baseCmProperty2.setName("testname4");
        this.baseCmPropertyDao.batchUpdate(baseCmProperties);
    }

    @Test
    public void testBatchSelect() {
        List<Long> baseCmPropertyIds = new ArrayList<Long>();
        baseCmPropertyIds.add(1L);
        List<BaseCmProperty> ret = baseCmPropertyDao.batchSelect(baseCmPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> baseCmPropertyIds = new ArrayList<Long>();
        baseCmPropertyIds.add(2L);
        baseCmPropertyIds.add(3L);
        List<BaseCmProperty> ret = baseCmPropertyDao.batchSelect(baseCmPropertyIds);
        Assert.assertNotNull(ret);
        this.baseCmPropertyDao.batchDelete(baseCmPropertyIds);
        ret = baseCmPropertyDao.batchSelect(baseCmPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
