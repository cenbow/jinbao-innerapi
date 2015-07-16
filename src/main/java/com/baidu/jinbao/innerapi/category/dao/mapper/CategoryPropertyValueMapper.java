package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

public interface CategoryPropertyValueMapper extends GenericMapper<CategoryPropertyValue, Long> {
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
     */
    public Integer batchUpdate(List<CategoryPropertyValue> categoryPropertyValues);

    /**
     * 批量删除CategoryPropertyValue
     * 
     * @param categoryPropertyValueIds 删除CategoryPropertyValueId列表
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
    public List<PropertyValueInfo> batchSelectPropertyValueInfo(
            @Param("list") List<PropertyIdCondition> propertyIdConditonList);
}
