package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryMap;

public interface CategoryMapMapper extends GenericMapper<CategoryMap, Long> {
    /**
     * 批量插入CategoryMap
     * 
     * @param categoryMaps 插入categoryMap列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CategoryMap> categoryMaps);

    /**
     * 批量更新CategoryMap
     * 
     * @param categoryMaps 更新categoryMap列表
     */
    public Integer batchUpdate(List<CategoryMap> categoryMaps);

    /**
     * 批量删除CategoryMap
     * 
     * @param categoryMapIds 删除CategoryMapId列表
     */

    public Integer batchDelete(List<Long> categoryMapIds);

    /**
     * 批量查询CategoryMap
     * 
     * @param categoryMapIds 查询CategoryMap Id 列表
     * @return CategoryMap列表
     */
    public List<CategoryMap> batchSelect(List<Long> categoryMapIds);
}
