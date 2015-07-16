package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CategoryMap;
import com.baidu.jinbao.innerapi.category.dao.CategoryMapDao;

@Service
public class CategoryMapServiceImplTest {
    @Tested
    private CategoryMapServiceImpl categoryMapService;

    @Injectable
    private CategoryMapDao categoryMapDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                categoryMapDao.selectByPrimaryKey(anyLong);
                result = new CategoryMap();
            }
        };
        CategoryMap ret = categoryMapService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                categoryMapDao.batchInsert((List<CategoryMap>) any);
            }
        };
        List<CategoryMap> categoryMaps = new ArrayList<CategoryMap>();
        CategoryMap categoryMap1 = new CategoryMap();
        categoryMap1.setBaseCid(1);
        CategoryMap categoryMap2 = new CategoryMap();
        categoryMap2.setBaseCid(2);
        categoryMaps.add(categoryMap1);
        categoryMaps.add(categoryMap2);
        this.categoryMapDao.batchInsert(categoryMaps);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                categoryMapDao.batchUpdate((List<CategoryMap>) any);
            }
        };
        List<CategoryMap> categoryMaps = new ArrayList<CategoryMap>();
        CategoryMap categoryMap1 = new CategoryMap();
        categoryMap1.setBaseCid(1);
        CategoryMap categoryMap2 = new CategoryMap();
        categoryMap2.setBaseCid(2);
        categoryMaps.add(categoryMap1);
        categoryMaps.add(categoryMap2);
        categoryMaps.add(categoryMap1);
        categoryMaps.add(categoryMap2);
        this.categoryMapDao.batchUpdate(categoryMaps);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                categoryMapDao.batchSelect((List<Long>) any);
                result = new ArrayList<CategoryMap>();
            }
        };
        List<Long> categoryMapIds = new ArrayList<Long>();
        categoryMapIds.add(1L);
        List<CategoryMap> ret = categoryMapDao.batchSelect(categoryMapIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                categoryMapDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> categoryMapIds = new ArrayList<Long>();
        categoryMapIds.add(2L);
        categoryMapIds.add(3L);
        this.categoryMapDao.batchDelete(categoryMapIds);
    }
}
