package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.Category;

public interface CategoryMapper extends GenericMapper<Category, Long> {
    /**
     * 批量插入Category
     * 
     * @param catgories 插入Category列表
     * @return 插入条数
     */
    public Integer batchInsert(List<Category> categories);

    /**
     * 批量更新Category
     * 
     * @param categories 更新Category列表
     */
    public Integer batchUpdate(List<Category> categories);

    /**
     * 批量删除Category
     * 
     * @param categoryIds 删除Category Id列表
     */

    public Integer batchDelete(List<Long> categoryIds);

    /**
     * 批量查询Category
     * 
     * @param categoryIds 查询Category Id列表
     * @return Category list
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
    public List<Category> selectByParentid(@Param("parentid") Long parentid);
}