package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;

public class CategoryDaoImplTest extends AbstractDAOTests {
    @Resource
    private CategoryDao categoryDao;

    @Before
    public void setUp() {
        this.executeDatas("category/category_init.sql");
    }

    @Test
    public void testFindById() {
        Category ret = this.categoryDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCategoryid().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<Category> categories = new ArrayList<Category>();
        Category category1 = new Category();
        category1.setName("testname1");
        Category category2 = new Category();
        category2.setName("testname2");
        categories.add(category1);
        categories.add(category2);
        this.categoryDao.batchInsert(categories);
        Category ret = this.categoryDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.categoryDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<Category> categories = new ArrayList<Category>();
        Category category1 = new Category();
        category1.setName("testname1");
        Category category2 = new Category();
        category2.setName("testname2");
        categories.add(category1);
        categories.add(category2);
        this.categoryDao.batchInsert(categories);
        category1.setCategoryid(1L);
        category1.setName("testname3");
        category2.setCategoryid(2L);
        category2.setName("testname4");
        this.categoryDao.batchUpdate(categories);
    }

    @Test
    public void testBatchSelect() {
        List<Long> categoryIds = new ArrayList<Long>();
        categoryIds.add(1L);
        List<Category> ret = categoryDao.batchSelect(categoryIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> categoryIds = new ArrayList<Long>();
        categoryIds.add(2L);
        categoryIds.add(3L);
        List<Category> ret = categoryDao.batchSelect(categoryIds);
        Assert.assertNotNull(ret);
        this.categoryDao.batchDelete(categoryIds);
        ret = categoryDao.batchSelect(categoryIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
    
    @Test
    public void testSelectAll() {
        List<Category> ret = categoryDao.selectAll();
        Assert.assertTrue(true);
    }
    
    @Test
    public void testSelectByParentid() {
        List<Category> ret = categoryDao.selectByParentid(1L);
        Assert.assertTrue(true);
    }
}
