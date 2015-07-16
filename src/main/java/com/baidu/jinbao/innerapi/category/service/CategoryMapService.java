package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CategoryMap;

public interface CategoryMapService extends GenericMapperService<CategoryMap, Long> {

    /**
     * 批量插入CategoryMap
     * 
     * @param categoryMaps 插入categoryMap列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CategoryMap> categoryMapList);

    /**
     * 批量更新CategoryMap
     * 
     * @param categoryMaps 更新categoryMap列表
     */
    public Integer updateRecords(List<CategoryMap> categoryMapList);

    /**
     * 批量删除CategoryMap
     * 
     * @param categoryMapIds 删除CategoryMapId列表
     */

    public Integer deleteRecords(List<Long> categoryMapIdList);

    /**
     * 批量查询CategoryMap
     * 
     * @param categoryMapIds 查询CategoryMap Id 列表
     * @return CategoryMap列表
     */
    public List<CategoryMap> getRecords(List<Long> categoryMapIdList);
}
