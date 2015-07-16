package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CmProperty;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyDao;

public class CmPropertyDaoImplTest extends AbstractDAOTests {
    @Resource
    private CmPropertyDao cmPropertyDao;

    @Before
    public void setUp() {
        this.executeDatas("category/cm_property_init.sql");
    }

    @Test
    public void testFindById() {
        CmProperty ret = this.cmPropertyDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CmProperty> cmProperties = new ArrayList<CmProperty>();
        CmProperty cmProperty1 = new CmProperty();
        cmProperty1.setBasePid(1);
        CmProperty cmProperty2 = new CmProperty();
        cmProperty2.setBasePid(2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        this.cmPropertyDao.batchInsert(cmProperties);
        CmProperty ret = this.cmPropertyDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getBasePid().equals(1));
        ret = this.cmPropertyDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getBasePid().equals(2));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CmProperty> cmProperties = new ArrayList<CmProperty>();
        CmProperty cmProperty1 = new CmProperty();
        cmProperty1.setBasePid(1);
        CmProperty cmProperty2 = new CmProperty();
        cmProperty2.setBasePid(2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        this.cmPropertyDao.batchInsert(cmProperties);
        cmProperty1.setId(1L);
        cmProperty1.setBasePid(3);
        cmProperty2.setId(2L);
        cmProperty2.setBasePid(4);
        this.cmPropertyDao.batchUpdate(cmProperties);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cmPropertyIds = new ArrayList<Long>();
        cmPropertyIds.add(1L);
        List<CmProperty> ret = cmPropertyDao.batchSelect(cmPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cmPropertyIds = new ArrayList<Long>();
        cmPropertyIds.add(2L);
        cmPropertyIds.add(3L);
        List<CmProperty> ret = cmPropertyDao.batchSelect(cmPropertyIds);
        Assert.assertNotNull(ret);
        this.cmPropertyDao.batchDelete(cmPropertyIds);
        ret = cmPropertyDao.batchSelect(cmPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
