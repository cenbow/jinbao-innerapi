package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;

public interface PropertyValueMapMapper extends GenericMapper<PropertyValueMap, Long> {
    /**
     * 批量插入PropertyValueMap
     * 
     * @param propertyValueMaps 插入propertyValueMap列表
     * @return 插入条数
     */
    public Integer batchInsert(List<PropertyValueMap> propertyValueMaps);

    /**
     * 批量更新PropertyValueMap
     * 
     * @param propertyValueMaps 更新propertyValueMap列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<PropertyValueMap> propertyValueMaps);

    /**
     * 批量删除PropertyValueMap
     * 
     * @param propertyValueMapIds 删除PropertyValueMapId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> propertyValueMapIds);

    /**
     * 批量查询PropertyValueMap
     * 
     * @param propertyValueMapIds 查询PropertyValueMap Id 列表
     * @return PropertyValueMap列表
     */
    public List<PropertyValueMap> batchSelect(List<Long> propertyValueMapIds);
}
