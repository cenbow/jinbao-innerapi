package com.baidu.jinbao.innerapi.category.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;

public interface CategoryPropertyDao extends GenericMapperDao<CategoryProperty, Long> {
    /**
     * 批量插入CategoryProperty
     * 
     * @param categoryProperties 插入CategoryProperty列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CategoryProperty> categoryProperties);

    /**
     * 批量插入CategoryProperty
     * 
     * @param categoryProperties 更新CategoryProperty列表
     */
    public Integer batchUpdate(List<CategoryProperty> categoryProperties);

    /**
     * 批量删除CategoryProperty
     * 
     * @param categoryPropertyIds 删除CategoryProperty Id 列表
     */

    public Integer batchDelete(List<Long> categoryPropertyIds);

    /**
     * 批量获取CategoryProperty
     * 
     * @param categoryPropertyIds CategoryProperty Id 列表
     * @return 获取到的CategoryProperty列表
     */
    public List<CategoryProperty> batchSelect(List<Long> categoryPropertyIds);
    
    /**
     * 根据cid联表查询category_property，base_property
     * 
     * @param propertyIdVoList ，含cid List
     * @return 获取到的PropertyInfo列表
     */
    public List<PropertyInfo> batchSelectPropertyInfo(List<PropertyIdCondition> propertyIdConditonList);
}
