package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;

@Service
public class MallCategoryPropertyServiceImplTest {
    @Tested
    private MallCategoryPropertyServiceImpl mallCategoryPropertyService;
    @Injectable
    private CategoryPropertyDao categoryPropertyDao;

    @Test
    public void testGetPropertyInfo() {
        new NonStrictExpectations() {
            {
                categoryPropertyDao.batchSelectPropertyInfo((List<PropertyIdCondition>) any);
                result = new ArrayList<PropertyInfo>();
            }
        };
        List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
        PropertyIdCondition condition = new PropertyIdCondition();
        condition.setCid(1L);
        propertyIdConditionList.add(condition);
        this.mallCategoryPropertyService.getPropertyInfo(propertyIdConditionList);
        Assert.assertTrue(true);
    }
}
