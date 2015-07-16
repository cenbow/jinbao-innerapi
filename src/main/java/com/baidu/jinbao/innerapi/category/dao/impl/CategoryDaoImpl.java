package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CategoryMapper;

@Repository
public class CategoryDaoImpl extends BaseDao<Category, Long> implements CategoryDao {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public GenericMapper<Category, Long> getMapper() {
        return this.categoryMapper;
    }

    @Override
    public Integer batchInsert(List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return 0;
        }
        return this.categoryMapper.batchInsert(categories);
    }

    @Override
    public Integer batchUpdate(List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return 0;
        }
        return this.categoryMapper.batchUpdate(categories);
    }

    @Override
    public Integer batchDelete(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return 0;
        }
        return this.categoryMapper.batchDelete(categoryIds);
    }

    @Override
    public List<Category> batchSelect(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return new ArrayList<Category>();
        }
        return this.categoryMapper.batchSelect(categoryIds);
    }

    @Override
    public List<Category> selectAll() {
        return this.categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectByParentid(Long parentid) {
        if (parentid == null) {
            return new ArrayList<Category>();
        }
        return this.categoryMapper.selectByParentid(parentid);
    }

}
