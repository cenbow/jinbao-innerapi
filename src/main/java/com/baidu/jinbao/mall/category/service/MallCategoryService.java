package com.baidu.jinbao.mall.category.service;

import java.util.List;

import com.baidu.jinbao.innerapi.category.bo.Category;

public interface MallCategoryService {
    /**
     * 根据categoryId 查询Category
     * 
     * @param categoryIds 删除Category Id列表
     */

    public List<Category> getRecordByParentid(Long parentid);

    /**
     * 查询所有Category
     * 
     * @return Category list
     */
    public List<Category> getAllRecords();
}
