package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.BaseProperty;

public interface BasePropertyService extends GenericMapperService<BaseProperty, Long> {

    /**
     * 批量插入BaseProperty
     * 
     * @param baseProperties 插入baseProperty列表
     * @return 插入条数
     */
    public Integer insertRecords(List<BaseProperty> basePropertyList);

    /**
     * 批量更新BaseProperty
     * 
     * @param baseProperties 更新baseProperty列表
     */
    public Integer updateRecords(List<BaseProperty> basePropertyList);

    /**
     * 批量删除BaseProperty
     * 
     * @param basePropertyIds 删除BasePropertyId列表
     */

    public Integer deleteRecords(List<Long> basePropertyIdList);

    /**
     * 批量查询BaseProperty
     * 
     * @param basePropertyIds 查询BaseProperty Id 列表
     * @return BaseProperty列表
     */
    public List<BaseProperty> getRecords(List<Long> basePropertyIdList);
}
