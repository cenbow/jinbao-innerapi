package com.baidu.jinbao.innerapi.category.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CmCategory;

public interface CmCategoryDao extends GenericMapperDao<CmCategory, Long> {
    /**
     * 批量插入CmCategory
     * 
     * @param cmCategories 插入cmCategory列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CmCategory> cmCategories);

    /**
     * 批量更新CmCategory
     * 
     * @param cmCategories 更新cmCategory列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<CmCategory> cmCategories);

    /**
     * 批量删除CmCategory
     * 
     * @param cmCategoryIds 删除CmCategoryId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> cmCategoryIds);

    /**
     * 批量查询CmCategory
     * 
     * @param cmCategoryIds 查询CmCategory Id 列表
     * @return CmCategory列表
     */
    public List<CmCategory> batchSelect(List<Long> cmCategoryIds);
}
