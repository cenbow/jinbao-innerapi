package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CmCategory;

public interface CmCategoryService extends GenericMapperService<CmCategory, Long> {
    /**
     * 批量插入CmCategory
     * 
     * @param cmCategories 插入cmCategory列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CmCategory> cmCategoryList);

    /**
     * 批量更新CmCategory
     * 
     * @param cmCategories 更新cmCategory列表
     */
    public Integer updateRecords(List<CmCategory> cmCategoryList);

    /**
     * 批量删除CmCategory
     * 
     * @param cmCategoryIds 删除CmCategoryId列表
     */

    public Integer deleteRecords(List<Long> cmCategoryIdList);

    /**
     * 批量查询CmCategory
     * 
     * @param cmCategoryIds 查询CmCategory Id 列表
     * @return CmCategory列表
     */
    public List<CmCategory> getRecords(List<Long> cmCategoryIdList);
}
