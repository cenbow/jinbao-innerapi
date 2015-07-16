package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CategoryMap;
import com.baidu.jinbao.innerapi.category.dao.CategoryMapDao;

public class CategoryMapDaoImplTest extends AbstractDAOTests {
    @Resource
    private CategoryMapDao categoryMapDao;

    @Before
    public void setUp() {
        this.executeDatas("category/category_map_init.sql");
    }

    @Test
    public void testFindById() {
        CategoryMap ret = this.categoryMapDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CategoryMap> categoryMaps = new ArrayList<CategoryMap>();
        CategoryMap categoryMap1 = new CategoryMap();
        categoryMap1.setBaseCid(1);
        CategoryMap categoryMap2 = new CategoryMap();
        categoryMap2.setBaseCid(2);
        categoryMaps.add(categoryMap1);
        categoryMaps.add(categoryMap2);
        this.categoryMapDao.batchInsert(categoryMaps);
        CategoryMap ret = this.categoryMapDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getBaseCid().equals(1));
        ret = this.categoryMapDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getBaseCid().equals(2));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CategoryMap> categoryMaps = new ArrayList<CategoryMap>();
        CategoryMap categoryMap1 = new CategoryMap();
        categoryMap1.setBaseCid(1);
        CategoryMap categoryMap2 = new CategoryMap();
        categoryMap2.setBaseCid(2);
        categoryMaps.add(categoryMap1);
        categoryMaps.add(categoryMap2);
        this.categoryMapDao.batchInsert(categoryMaps);
        categoryMap1.setId(1L);
        categoryMap1.setBaseCid(3);
        categoryMap2.setId(2L);
        categoryMap2.setBaseCid(4);
        this.categoryMapDao.batchUpdate(categoryMaps);
    }

    @Test
    public void testBatchSelect() {
        List<Long> categoryMapIds = new ArrayList<Long>();
        categoryMapIds.add(1L);
        List<CategoryMap> ret = categoryMapDao.batchSelect(categoryMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> categoryMapIds = new ArrayList<Long>();
        categoryMapIds.add(2L);
        categoryMapIds.add(3L);
        List<CategoryMap> ret = categoryMapDao.batchSelect(categoryMapIds);
        Assert.assertNotNull(ret);
        this.categoryMapDao.batchDelete(categoryMapIds);
        ret = categoryMapDao.batchSelect(categoryMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
