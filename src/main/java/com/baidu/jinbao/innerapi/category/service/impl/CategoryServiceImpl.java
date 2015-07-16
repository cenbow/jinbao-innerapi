package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;
import com.baidu.jinbao.innerapi.category.service.CategoryService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("categoryService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CategoryServiceImpl extends BaseService<Category, Long> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public GenericMapperDao<Category, Long> getDao() {
        return categoryDao;
    }

    @Override
    public Integer insertRecords(List<Category> categories) {
        return this.categoryDao.batchInsert(categories);
    }

    @Override
    public Integer updateRecords(List<Category> categories) {
        return this.categoryDao.batchUpdate(categories);
    }

    @Override
    public Integer deleteRecords(List<Long> categoryIds) {
        return this.categoryDao.batchDelete(categoryIds);
    }

    @Override
    public List<Category> getRecords(List<Long> categoryIds) {
        return this.categoryDao.batchSelect(categoryIds);
    }
}