package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;

import junit.framework.Assert;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;

@Service
public class MallCategoryServiceImplTest {
    @Tested
    private MallCategoryServiceImpl mallCategoryService;
    @Injectable
    private CategoryDao categoryDao;

    @Test
    public void testGetAllRecords() {
        new NonStrictExpectations() {
            {
                categoryDao.selectAll();
                result = new ArrayList<Category>();
            }
        };
        this.mallCategoryService.getAllRecords();
        Assert.assertTrue(true);
    }

    @Test
    public void testGetRecordByParentid() {
        new NonStrictExpectations() {
            {
                categoryDao.selectByParentid((Long) any);
                result = new ArrayList<Category>();
            }
        };
        this.mallCategoryService.getRecordByParentid(1L);
        Assert.assertTrue(true);
    }
}
