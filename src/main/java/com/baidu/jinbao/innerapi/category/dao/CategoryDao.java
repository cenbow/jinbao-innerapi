package com.baidu.jinbao.innerapi.category.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.Category;

public interface CategoryDao extends GenericMapperDao<Category, Long> {
    /**
     * 批量插入Category
     * 
     * @param categories 插入Category列表
     * @return 插入条数
     */
    public Integer batchInsert(List<Category> categories);

    /**
     * 批量插入Category
     * 
     * @param categories 更新Category列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<Category> categories);

    /**
     * 批量删除Category
     * 
     * @param categoryIds 删除Category Id 列表
     * @return 删除条数
     */

    public Integer batchDelete(List<Long> categoryIds);

    /**
     * 批量获取Category
     * 
     * @param categoryIds Category Id 列表
     * @return 获取到的Category列表
     */
    public List<Category> batchSelect(List<Long> categoryIds);
    
    /**
     * 获取所有Category
     * 
     * @return 获取到的Category列表
     */
    public List<Category> selectAll();
    
    /**
     * 通过Pid获取Category所有子类目
     * 
     * @return 获取到的Category列表
     */
    public List<Category> selectByParentid(Long parentid);
}
