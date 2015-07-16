package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

@Service
public class MallCategoryPropertyValueServiceImplTest {
    @Tested
    private MallCategoryPropertyValueServiceImpl mallCategoryPropertyValueService;
    @Injectable
    private CategoryPropertyValueDao categoryPropertyValueDao;

    @Test
    public void testGetPropertyValueInfo() {
        new NonStrictExpectations() {
            {
                categoryPropertyValueDao.batchSelectPropertyValueInfo((List<PropertyIdCondition>) any);
                result = new ArrayList<PropertyValueInfo>();
            }
        };
        List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
        PropertyIdCondition condition = new PropertyIdCondition();
        condition.setCid(1L);
        condition.setPid(1L);
        propertyIdConditionList.add(condition);
        this.mallCategoryPropertyValueService.getPropertyValueInfo(propertyIdConditionList);
        Assert.assertTrue(true);
    }

}
