package com.baidu.jinbao.innerapi.category.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

public interface CategoryPropertyValueDao extends GenericMapperDao<CategoryPropertyValue, Long> {
    /**
     * 批量插入CategoryPropertyValue
     * 
     * @param categoryPropertyValues 插入categoryPropertyValue列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CategoryPropertyValue> categoryPropertyValues);

    /**
     * 批量更新CategoryPropertyValue
     * 
     * @param categoryPropertyValues 更新categoryPropertyValue列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<CategoryPropertyValue> categoryPropertyValues);

    /**
     * 批量删除CategoryPropertyValue
     * 
     * @param categoryPropertyValueIds 删除CategoryPropertyValueId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> categoryPropertyValueIds);

    /**
     * 批量查询CategoryPropertyValue
     * 
     * @param categoryPropertyValueIds 查询CategoryPropertyValue Id 列表
     * @return CategoryPropertyValue列表
     */
    public List<CategoryPropertyValue> batchSelect(List<Long> categoryPropertyValueIds);
    
    /**
     * 根据cid,pid联表查询category_property_value，base_val
     * 
     * @param propertyIdConditonList, 含cid， pid List
     * @return 获取到的PropertyValueInfo列表
     */
    public List<PropertyValueInfo> batchSelectPropertyValueInfo(List<PropertyIdCondition> propertyIdConditonList);
}
