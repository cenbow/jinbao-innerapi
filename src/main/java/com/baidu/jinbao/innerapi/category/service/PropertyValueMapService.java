package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;

public interface PropertyValueMapService extends GenericMapperService<PropertyValueMap, Long> {

    /**
     * 批量插入PropertyValueMap
     * 
     * @param propertyValueMaps 插入propertyValueMap列表
     * @return 插入条数
     */
    public Integer insertRecords(List<PropertyValueMap> propertyValueMapList);

    /**
     * 批量更新PropertyValueMap
     * 
     * @param propertyValueMaps 更新propertyValueMap列表
     * @return 更新条数
     */
    public Integer updateRecords(List<PropertyValueMap> propertyValueMapList);

    /**
     * 批量删除PropertyValueMap
     * 
     * @param propertyValueMapIds 删除PropertyValueMapId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> propertyValueMapIdList);

    /**
     * 批量查询PropertyValueMap
     * 
     * @param propertyValueMapIds 查询PropertyValueMap Id 列表
     * @return PropertyValueMap列表
     */
    public List<PropertyValueMap> getRecords(List<Long> propertyValueMapIdList);
}
