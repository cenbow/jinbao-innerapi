package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;

public interface CategoryPropertyValueService extends GenericMapperService<CategoryPropertyValue, Long> {
    /**
     * 批量插入CategoryPropertyValue
     * 
     * @param categoryPropertyValues 插入categoryPropertyValue列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CategoryPropertyValue> categoryPropertyValueList);

    /**
     * 批量更新CategoryPropertyValue
     * 
     * @param categoryPropertyValues 更新categoryPropertyValue列表
     * @return 更新条数
     */
    public Integer updateRecords(List<CategoryPropertyValue> categoryPropertyValueList);

    /**
     * 批量删除CategoryPropertyValue
     * 
     * @param categoryPropertyValueIds 删除CategoryPropertyValueId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> categoryPropertyValueIdList);

    /**
     * 批量查询CategoryPropertyValue
     * 
     * @param categoryPropertyValueIds 查询CategoryPropertyValue Id 列表
     * @return CategoryPropertyValue列表
     */
    public List<CategoryPropertyValue> getRecords(List<Long> categoryPropertyValueIdList);
}
