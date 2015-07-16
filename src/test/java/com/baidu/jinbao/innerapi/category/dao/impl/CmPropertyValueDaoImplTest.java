package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyValueDao;

public class CmPropertyValueDaoImplTest extends AbstractDAOTests {
    @Resource
    private CmPropertyValueDao cmPropertyValueDao;

    @Before
    public void setUp() {
        this.executeDatas("category/cm_property_value_init.sql");
    }

    @Test
    public void testFindById() {
        CmPropertyValue ret = this.cmPropertyValueDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CmPropertyValue> cmPropertyValues = new ArrayList<CmPropertyValue>();
        CmPropertyValue cmPropertyValue1 = new CmPropertyValue();
        cmPropertyValue1.setCmPid(1);
        CmPropertyValue cmPropertyValue2 = new CmPropertyValue();
        cmPropertyValue2.setCmPid(2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        this.cmPropertyValueDao.batchInsert(cmPropertyValues);
        CmPropertyValue ret = this.cmPropertyValueDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCmPid().equals(1));
        ret = this.cmPropertyValueDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCmPid().equals(2));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CmPropertyValue> cmPropertyValues = new ArrayList<CmPropertyValue>();
        CmPropertyValue cmPropertyValue1 = new CmPropertyValue();
        cmPropertyValue1.setCmPid(1);
        CmPropertyValue cmPropertyValue2 = new CmPropertyValue();
        cmPropertyValue2.setCmPid(2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        this.cmPropertyValueDao.batchInsert(cmPropertyValues);
        cmPropertyValue1.setId(1L);
        cmPropertyValue1.setCmPid(3);
        cmPropertyValue2.setId(2L);
        cmPropertyValue2.setCmPid(4);
        this.cmPropertyValueDao.batchUpdate(cmPropertyValues);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cmPropertyValueIds = new ArrayList<Long>();
        cmPropertyValueIds.add(1L);
        List<CmPropertyValue> ret = cmPropertyValueDao.batchSelect(cmPropertyValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cmPropertyValueIds = new ArrayList<Long>();
        cmPropertyValueIds.add(2L);
        cmPropertyValueIds.add(3L);
        List<CmPropertyValue> ret = cmPropertyValueDao.batchSelect(cmPropertyValueIds);
        Assert.assertNotNull(ret);
        this.cmPropertyValueDao.batchDelete(cmPropertyValueIds);
        ret = cmPropertyValueDao.batchSelect(cmPropertyValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
