package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.mall.category.service.MallCategoryPropertyService;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;

;

@Service("mallCategoryPropertyService")
@SplitModule(moduleName = PdsConstants.MALL_CATEGORY_MODULE_DATASOURCE_KEY)
public class MallCategoryPropertyServiceImpl implements MallCategoryPropertyService {

    @Autowired
    private CategoryPropertyDao categoryPropertyDao;

    @Override
    public List<PropertyInfo> getPropertyInfo(List<PropertyIdCondition> propertyIdConditionList) {
        if (CollectionUtils.isEmpty(propertyIdConditionList)) {
            return new ArrayList<PropertyInfo>();
        }
        return this.categoryPropertyDao.batchSelectPropertyInfo(propertyIdConditionList);
    }
}
