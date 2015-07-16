package com.baidu.jinbao.mall.category.service;

import java.util.List;

import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;

public interface MallCategoryPropertyService {
    /**
     * 查询某一类目的属性项信息
     * 
     * @param propertyIdVoList, 含cid Lists
     * @return 返回属性信息
     * */
    public List<PropertyInfo> getPropertyInfo(List<PropertyIdCondition> propertyIdConditionList);
}
