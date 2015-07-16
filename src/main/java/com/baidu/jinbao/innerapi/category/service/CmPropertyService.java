package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CmProperty;

public interface CmPropertyService extends GenericMapperService<CmProperty, Long> {
    /**
     * 批量插入CmProperty
     * 
     * @param cmProperties 插入cmProperty列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CmProperty> cmPropertyList);

    /**
     * 批量更新CmProperty
     * 
     * @param cmProperties 更新cmProperty列表
     */
    public Integer updateRecords(List<CmProperty> cmPropertyList);

    /**
     * 批量删除CmProperty
     * 
     * @param cmPropertyIds 删除CmPropertyId列表
     */

    public Integer deleteRecords(List<Long> cmPropertyIdList);

    /**
     * 批量查询CmProperty
     * 
     * @param cmPropertyIds 查询CmProperty Id 列表
     * @return CmProperty列表
     */
    public List<CmProperty> getRecords(List<Long> cmPropertyIdList);
}
