package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;

public interface BaseCmPropertyService extends GenericMapperService<BaseCmProperty, Long> {

    /**
     * 批量插入BaseCmProperty
     * 
     * @param baseCmProperties 插入baseCmProperty列表
     * @return 插入条数
     */
    public Integer insertRecords(List<BaseCmProperty> baseCmPropertyList);

    /**
     * 批量更新BaseCmProperty
     * 
     * @param baseCmProperties 更新baseCmProperty列表
     * @return 更新条数
     */
    public Integer updateRecords(List<BaseCmProperty> baseCmPropertyList);

    /**
     * 批量删除BaseCmProperty
     * 
     * @param baseCmPropertyIds 删除BaseCmPropertyId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> baseCmPropertyIdList);

    /**
     * 批量查询BaseCmProperty
     * 
     * @param baseCmPropertyIds 查询BaseCmProperty Id 列表
     * @return BaseCmProperty列表
     */
    public List<BaseCmProperty> getRecords(List<Long> baseCmPropertyIdList);

    /**
     * BaseCmProperty page 查询
     * 
     * @param baseCmPropertyIds 查询BaseCmProperty Id 列表
     * @return BaseCmProperty列表
     */
    public List<BaseCmProperty> getPageRecords();
}
