package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;
import com.baidu.jinbao.innerapi.category.service.CategoryPropertyService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("categoryPropertyService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CategoryPropertyServiceImpl extends BaseService<CategoryProperty, Long> 
        implements CategoryPropertyService {
    @Autowired
    private CategoryPropertyDao categoryPropertyDao;

    @Override
    public GenericMapperDao<CategoryProperty, Long> getDao() {
        return this.categoryPropertyDao;
    }

    @Override
    public Integer insertRecords(List<CategoryProperty> categoryPropertyList) {
        return this.categoryPropertyDao.batchInsert(categoryPropertyList);
    }

    @Override
    public Integer updateRecords(List<CategoryProperty> categoryPropertyList) {
        return this.categoryPropertyDao.batchUpdate(categoryPropertyList);
    }

    @Override
    public Integer deleteRecords(List<Long> categoryPropertyIdList) {
        return this.categoryPropertyDao.batchDelete(categoryPropertyIdList);
    }

    @Override
    public List<CategoryProperty> getRecords(List<Long> categoryPropertyIdList) {
        return this.categoryPropertyDao.batchSelect(categoryPropertyIdList);
    }
}