package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;

@Service
public class CategoryPropertyValueServiceImplTest {
    @Tested
    private CategoryPropertyValueServiceImpl categoryPropertyValueService;

    @Injectable
    private CategoryPropertyValueDao categoryPropertyValueDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.selectByPrimaryKey(anyLong);
                result = new CategoryPropertyValue();
            }
        };
        CategoryPropertyValue ret = categoryPropertyValueService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.batchInsert((List<CategoryPropertyValue>) any);
            }
        };
        List<CategoryPropertyValue> categoryPropertyValues = new ArrayList<CategoryPropertyValue>();
        CategoryPropertyValue categoryPropertyValue1 = new CategoryPropertyValue();
        categoryPropertyValue1.setAlias("testAlias1");
        CategoryPropertyValue categoryPropertyValue2 = new CategoryPropertyValue();
        categoryPropertyValue2.setAlias("testAlias2");
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        this.categoryPropertyValueDao.batchInsert(categoryPropertyValues);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.batchUpdate((List<CategoryPropertyValue>) any);
            }
        };
        List<CategoryPropertyValue> categoryPropertyValues = new ArrayList<CategoryPropertyValue>();
        CategoryPropertyValue categoryPropertyValue1 = new CategoryPropertyValue();
        categoryPropertyValue1.setAlias("testAlias1");
        CategoryPropertyValue categoryPropertyValue2 = new CategoryPropertyValue();
        categoryPropertyValue2.setAlias("testAlias2");
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        categoryPropertyValues.add(categoryPropertyValue1);
        categoryPropertyValues.add(categoryPropertyValue2);
        this.categoryPropertyValueDao.batchUpdate(categoryPropertyValues);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.batchSelect((List<Long>) any);
                result = new ArrayList<CategoryPropertyValue>();
            }
        };
        List<Long> categoryPropertyValueIds = new ArrayList<Long>();
        categoryPropertyValueIds.add(1L);
        List<CategoryPropertyValue> ret = categoryPropertyValueDao.batchSelect(categoryPropertyValueIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> categoryPropertyValueIds = new ArrayList<Long>();
        categoryPropertyValueIds.add(2L);
        categoryPropertyValueIds.add(3L);
        this.categoryPropertyValueDao.batchDelete(categoryPropertyValueIds);
    }
}
