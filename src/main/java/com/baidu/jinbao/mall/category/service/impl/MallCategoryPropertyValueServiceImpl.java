package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.mall.category.service.MallCategoryPropertyValueService;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

@Service("mallCategoryPropertyValueService")
@SplitModule(moduleName = PdsConstants.MALL_CATEGORY_MODULE_DATASOURCE_KEY)
public class MallCategoryPropertyValueServiceImpl implements MallCategoryPropertyValueService {

    @Autowired
    private CategoryPropertyValueDao categoryPropertyValueDao;

    @Override
    public List<PropertyValueInfo> getPropertyValueInfo(List<PropertyIdCondition> propertyIdConditionList) {
        if (CollectionUtils.isEmpty(propertyIdConditionList)) {
            return new ArrayList<PropertyValueInfo>();
        }
        return this.categoryPropertyValueDao.batchSelectPropertyValueInfo(propertyIdConditionList);
    }
}