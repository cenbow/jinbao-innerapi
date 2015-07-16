package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;

public interface CategoryPropertyMapper extends GenericMapper<CategoryProperty, Long> {
    /**
     * 批量插入CategoryProperty
     * 
     * @param categoryProperties 插入categoryProperty列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CategoryProperty> categoryProperties);

    /**
     * 批量更新CategoryProperty
     * 
     * @param categoryProperties 更新categoryProperty列表
     */
    public Integer batchUpdate(List<CategoryProperty> categoryProperties);

    /**
     * 批量删除CategoryProperty
     * 
     * @param categoryPropertyIds 删除CategoryPropertyId列表
     */

    public Integer batchDelete(List<Long> categoryPropertyIds);

    /**
     * 批量查询CategoryProperty
     * 
     * @param categoryPropertyIds 查询CategoryProperty Id 列表
     * @return CategoryProperty列表
     */
    public List<CategoryProperty> batchSelect(List<Long> categoryPropertyIds);
    
    /**
     * 根据cid联表查询category_property，base_property
     * 
     * @param propertyIdVoList ，含cid List
     * @return 获取到的PropertyInfo列表
     */
    public List<PropertyInfo> batchSelectPropertyInfo(@Param("list")List<PropertyIdCondition> propertyIdConditonList);
}
