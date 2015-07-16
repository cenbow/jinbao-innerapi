package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.dao.BasePropertyDao;

public class BasePropertyDaoImplTest extends AbstractDAOTests {
    @Resource
    private BasePropertyDao basePropertyDao;

    @Before
    public void setUp() {
        this.executeDatas("category/base_property_init.sql");
    }

    @Test
    public void testFindById() {
        BaseProperty ret = this.basePropertyDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<BaseProperty> baseProperties = new ArrayList<BaseProperty>();
        BaseProperty baseProperty1 = new BaseProperty();
        baseProperty1.setPropertyName("testname1");
        BaseProperty baseProperty2 = new BaseProperty();
        baseProperty2.setPropertyName("testname2");
        baseProperties.add(baseProperty1);
        baseProperties.add(baseProperty2);
        this.basePropertyDao.batchInsert(baseProperties);
        BaseProperty ret = this.basePropertyDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getPropertyName().equals("testname1"));
        ret = this.basePropertyDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getPropertyName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<BaseProperty> baseProperties = new ArrayList<BaseProperty>();
        BaseProperty baseProperty1 = new BaseProperty();
        baseProperty1.setPropertyName("testname1");
        BaseProperty baseProperty2 = new BaseProperty();
        baseProperty2.setPropertyName("testname2");
        baseProperties.add(baseProperty1);
        baseProperties.add(baseProperty2);
        this.basePropertyDao.batchInsert(baseProperties);
        baseProperty1.setId(1L);
        baseProperty1.setPropertyName("testname3");
        baseProperty2.setId(2L);
        baseProperty2.setPropertyName("testname4");
        this.basePropertyDao.batchUpdate(baseProperties);
    }

    @Test
    public void testBatchSelect() {
        List<Long> basePropertyIds = new ArrayList<Long>();
        basePropertyIds.add(1L);
        List<BaseProperty> ret = basePropertyDao.batchSelect(basePropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> basePropertyIds = new ArrayList<Long>();
        basePropertyIds.add(2L);
        basePropertyIds.add(3L);
        List<BaseProperty> ret = basePropertyDao.batchSelect(basePropertyIds);
        Assert.assertNotNull(ret);
        this.basePropertyDao.batchDelete(basePropertyIds);
        ret = basePropertyDao.batchSelect(basePropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
