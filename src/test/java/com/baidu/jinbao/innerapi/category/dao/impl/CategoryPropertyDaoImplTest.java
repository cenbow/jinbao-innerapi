package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;

public class CategoryPropertyDaoImplTest extends AbstractDAOTests {
    @Resource
    private CategoryPropertyDao categoryPropertyDao;

    @Before
    public void setUp() {
        this.executeDatas("category/category_property_init.sql");
    }

    @Test
    public void testFindById() {
        CategoryProperty ret = this.categoryPropertyDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CategoryProperty> baseCmProperties = new ArrayList<CategoryProperty>();
        CategoryProperty categoryProperty1 = new CategoryProperty();
        categoryProperty1.setName("testname1");
        CategoryProperty categoryProperty2 = new CategoryProperty();
        categoryProperty2.setName("testname2");
        baseCmProperties.add(categoryProperty1);
        baseCmProperties.add(categoryProperty2);
        this.categoryPropertyDao.batchInsert(baseCmProperties);
        CategoryProperty ret = this.categoryPropertyDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.categoryPropertyDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CategoryProperty> baseCmProperties = new ArrayList<CategoryProperty>();
        CategoryProperty categoryProperty1 = new CategoryProperty();
        categoryProperty1.setName("testname1");
        CategoryProperty categoryProperty2 = new CategoryProperty();
        categoryProperty2.setName("testname2");
        baseCmProperties.add(categoryProperty1);
        baseCmProperties.add(categoryProperty2);
        this.categoryPropertyDao.batchInsert(baseCmProperties);
        categoryProperty1.setId(1L);
        categoryProperty1.setName("testname3");
        categoryProperty2.setId(2L);
        categoryProperty2.setName("testname4");
        this.categoryPropertyDao.batchUpdate(baseCmProperties);
    }

    @Test
    public void testBatchSelect() {
        List<Long> categoryPropertyIds = new ArrayList<Long>();
        categoryPropertyIds.add(1L);
        List<CategoryProperty> ret = categoryPropertyDao.batchSelect(categoryPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> categoryPropertyIds = new ArrayList<Long>();
        categoryPropertyIds.add(2L);
        categoryPropertyIds.add(3L);
        List<CategoryProperty> ret = categoryPropertyDao.batchSelect(categoryPropertyIds);
        Assert.assertNotNull(ret);
        this.categoryPropertyDao.batchDelete(categoryPropertyIds);
        ret = categoryPropertyDao.batchSelect(categoryPropertyIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
    
    @Test
    public void testBatchSelectPropertyInfo() {
        List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
        PropertyIdCondition condition = new PropertyIdCondition();
        condition.setCid(1L);
        propertyIdConditionList.add(condition);
        this.categoryPropertyDao.batchSelectPropertyInfo(propertyIdConditionList);
        Assert.assertTrue(true);
    }
}
