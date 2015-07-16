package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;

@Service
public class CategoryPropertyServiceImplTest {
    @Tested
    private CategoryPropertyServiceImpl categoryPropertyService;

    @Injectable
    private CategoryPropertyDao categoryPropertyDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.selectByPrimaryKey(anyLong);
                result = new CategoryProperty();
            }
        };
        CategoryProperty ret = categoryPropertyService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.batchInsert((List<CategoryProperty>) any);
            }
        };
        List<CategoryProperty> categoryProperties = new ArrayList<CategoryProperty>();
        CategoryProperty categoryProperty1 = new CategoryProperty();
        categoryProperty1.setName("testname1");
        CategoryProperty categoryProperty2 = new CategoryProperty();
        categoryProperty2.setName("testname2");
        categoryProperties.add(categoryProperty1);
        categoryProperties.add(categoryProperty2);
        this.categoryPropertyDao.batchInsert(categoryProperties);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.batchUpdate((List<CategoryProperty>) any);
            }
        };
        List<CategoryProperty> categoryProperties = new ArrayList<CategoryProperty>();
        CategoryProperty categoryProperty1 = new CategoryProperty();
        categoryProperty1.setName("testname2");
        CategoryProperty categoryProperty2 = new CategoryProperty();
        categoryProperty2.setName("testname3");
        categoryProperties.add(categoryProperty1);
        categoryProperties.add(categoryProperty2);
        this.categoryPropertyDao.batchUpdate(categoryProperties);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.batchSelect((List<Long>) any);
                result = new ArrayList<CategoryProperty>();
            }
        };
        List<Long> categoryPropertyIds = new ArrayList<Long>();
        categoryPropertyIds.add(1L);
        List<CategoryProperty> ret = categoryPropertyDao.batchSelect(categoryPropertyIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> categoryPropertyIds = new ArrayList<Long>();
        categoryPropertyIds.add(2L);
        categoryPropertyIds.add(3L);
        this.categoryPropertyDao.batchDelete(categoryPropertyIds);
    }
}
