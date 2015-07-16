package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;

@Service
public class CategoryServiceImplTest {

    @Tested
    private CategoryServiceImpl categoryService;

    @Injectable
    private CategoryDao categoryDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                categoryDao.selectByPrimaryKey(anyLong);
                result = new Category();
            }
        };
        Category ret = categoryService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                categoryDao.batchInsert((List<Category>) any);
            }
        };
        List<Category> categories = new ArrayList<Category>();
        Category category1 = new Category();
        category1.setName("testname1");
        Category category2 = new Category();
        category2.setName("testname2");
        categories.add(category1);
        categories.add(category2);
        this.categoryDao.batchInsert(categories);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                categoryDao.batchUpdate((List<Category>) any);
            }
        };
        List<Category> categories = new ArrayList<Category>();
        Category category1 = new Category();
        category1.setName("testname2");
        Category category2 = new Category();
        category2.setName("testname3");
        categories.add(category1);
        categories.add(category2);
        this.categoryDao.batchUpdate(categories);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                categoryDao.batchSelect((List<Long>) any);
                result = new ArrayList<Category>();
            }
        };
        List<Long> categoryIds = new ArrayList<Long>();
        categoryIds.add(1L);
        List<Category> ret = categoryDao.batchSelect(categoryIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                categoryDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> categoryIds = new ArrayList<Long>();
        categoryIds.add(2L);
        categoryIds.add(3L);
        this.categoryDao.batchDelete(categoryIds);
    }
}
