package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;

public class CategoryPropertyValueDaoImplTest extends AbstractDAOTests {
    @Resource
    private CategoryPropertyValueDao categoryPropertyValueDao;

    @Before
    public void setUp() {
        this.executeDatas("category/category_property_value_init.sql");
    }

    @Test
    public void testFindById() {
        CategoryPropertyValue ret = this.categoryPropertyValueDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CategoryPropertyValue> categoryPropertyValues = new ArrayList<CategoryPropertyValue>();
        CategoryPropertyValue categoryPropertyValue1 = new CategoryPropertyValue();
        categoryPropertyValue1.setAlias("testAlias1");
        CategoryPropertyValue categoryPropertyValue2 = new CategoryPropertyValue();
        categoryPropertyValue2.setAlias("testAlias2");
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        this.categoryPropertyValueDao.batchInsert(categoryPropertyValues);
        CategoryPropertyValue ret = this.categoryPropertyValueDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getAlias().equals("testAlias1"));
        ret = this.categoryPropertyValueDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getAlias().equals("testAlias2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CategoryPropertyValue> categoryPropertyValues = new ArrayList<CategoryPropertyValue>();
        CategoryPropertyValue categoryPropertyValue1 = new CategoryPropertyValue();
        categoryPropertyValue1.setAlias("testAlias1");
        CategoryPropertyValue categoryPropertyValue2 = new CategoryPropertyValue();
        categoryPropertyValue2.setAlias("testAlias2");
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        this.categoryPropertyValueDao.batchInsert(categoryPropertyValues);
        categoryPropertyValue1.setId(1L);
        categoryPropertyValue1.setAlias("testAlias3");
        categoryPropertyValue2.setId(2L);
        categoryPropertyValue2.setAlias("testAlias4");
        this.categoryPropertyValueDao.batchUpdate(categoryPropertyValues);
    }

    @Test
    public void testBatchSelect() {
        List<Long> categoryPropertyValueIds = new ArrayList<Long>();
        categoryPropertyValueIds.add(1L);
        List<CategoryPropertyValue> ret = categoryPropertyValueDao.batchSelect(categoryPropertyValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> categoryPropertyValueIds = new ArrayList<Long>();
        categoryPropertyValueIds.add(2L);
        categoryPropertyValueIds.add(3L);
        List<CategoryPropertyValue> ret = categoryPropertyValueDao.batchSelect(categoryPropertyValueIds);
        Assert.assertNotNull(ret);
        this.categoryPropertyValueDao.batchDelete(categoryPropertyValueIds);
        ret = categoryPropertyValueDao.batchSelect(categoryPropertyValueIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
    
    @Test(expected = Exception.class)
    public void testSelectPropertyValueInfoByCidPid() {
        List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
        PropertyIdCondition condition = new PropertyIdCondition();
        condition.setCid(1L);
        condition.setPid(1L);
        propertyIdConditionList.add(condition);
        this.categoryPropertyValueDao.batchSelectPropertyValueInfo(propertyIdConditionList);
        Assert.assertTrue(true);
    }
}
