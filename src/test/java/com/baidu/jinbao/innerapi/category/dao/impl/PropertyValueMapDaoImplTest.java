package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;
import com.baidu.jinbao.innerapi.category.dao.PropertyValueMapDao;

public class PropertyValueMapDaoImplTest extends AbstractDAOTests {
    @Resource
    private PropertyValueMapDao propertyValueMapDao;

    @Before
    public void setUp() {
        this.executeDatas("category/property_value_map_init.sql");
    }

    @Test
    public void testFindById() {
        PropertyValueMap ret = this.propertyValueMapDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<PropertyValueMap> propertyValueMaps = new ArrayList<PropertyValueMap>();
        PropertyValueMap propertyValueMap1 = new PropertyValueMap();
        propertyValueMap1.setCmVid(1);
        PropertyValueMap propertyValueMap2 = new PropertyValueMap();
        propertyValueMap2.setCmVid(2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        this.propertyValueMapDao.batchInsert(propertyValueMaps);
        PropertyValueMap ret = this.propertyValueMapDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCmVid().equals(1));
        ret = this.propertyValueMapDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCmVid().equals(2));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<PropertyValueMap> propertyValueMaps = new ArrayList<PropertyValueMap>();
        PropertyValueMap propertyValueMap1 = new PropertyValueMap();
        propertyValueMap1.setCmVid(1);
        PropertyValueMap propertyValueMap2 = new PropertyValueMap();
        propertyValueMap2.setCmVid(2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        this.propertyValueMapDao.batchInsert(propertyValueMaps);
        propertyValueMap1.setId(1L);
        propertyValueMap1.setCmVid(3);
        propertyValueMap2.setId(2L);
        propertyValueMap2.setCmVid(4);
        this.propertyValueMapDao.batchUpdate(propertyValueMaps);
    }

    @Test
    public void testBatchSelect() {
        List<Long> propertyValueMapIds = new ArrayList<Long>();
        propertyValueMapIds.add(1L);
        List<PropertyValueMap> ret = propertyValueMapDao.batchSelect(propertyValueMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> propertyValueMapIds = new ArrayList<Long>();
        propertyValueMapIds.add(2L);
        propertyValueMapIds.add(3L);
        List<PropertyValueMap> ret = propertyValueMapDao.batchSelect(propertyValueMapIds);
        Assert.assertNotNull(ret);
        this.propertyValueMapDao.batchDelete(propertyValueMapIds);
        ret = propertyValueMapDao.batchSelect(propertyValueMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
