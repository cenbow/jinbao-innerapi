package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;

public interface CategoryPropertyService extends GenericMapperService<CategoryProperty, Long> {

    /**
     * 批量插入CategoryProperty
     * 
     * @param categoryProperties 插入categoryProperty列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CategoryProperty> categoryPropertyList);

    /**
     * 批量更新CategoryProperty
     * 
     * @param categoryProperties 更新categoryProperty列表
     * @return 更新条数
     */
    public Integer updateRecords(List<CategoryProperty> categoryPropertyList);

    /**
     * 批量删除CategoryProperty
     * 
     * @param categoryPropertyIds 删除CategoryPropertyId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> categoryPropertyIdList);

    /**
     * 批量查询CategoryProperty
     * 
     * @param categoryPropertyIds 查询CategoryProperty Id 列表
     * @return CategoryProperty列表
     */
    public List<CategoryProperty> getRecords(List<Long> categoryPropertyIdList);
}
