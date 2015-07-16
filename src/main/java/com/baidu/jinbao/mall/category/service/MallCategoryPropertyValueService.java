package com.baidu.jinbao.mall.category.service;

import java.util.List;

import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

public interface MallCategoryPropertyValueService {
    /**
     * 查询指定类目下的某一个属性项对应的属性值信息
     * 
     * @param propertyIdVoList, 含(cid, pid) List
     * @return 返回属性值信息
     * */
    public List<PropertyValueInfo> getPropertyValueInfo(List<PropertyIdCondition> propertyIdConditionList);

}
