/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.Category;

public interface CategoryService extends GenericMapperService<Category, Long> {
    /**
     * 批量插入Category
     * 
     * @param catgories 插入Category列表
     * @return 插入条数
     */
    public Integer insertRecords(List<Category> categoryList);

    /**
     * 批量更新Category
     * 
     * @param categories 更新Category列表
     */
    public Integer updateRecords(List<Category> categoryList);

    /**
     * 批量删除Category
     * 
     * @param categoryIds 删除Category Id列表
     */

    public Integer deleteRecords(List<Long> categoryIdList);

    /**
     * 批量查询Category
     * 
     * @param categoryIds 查询Category Id列表
     * @return Category list
     */
    public List<Category> getRecords(List<Long> categoryIdList);
}
