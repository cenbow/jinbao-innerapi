package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;
import com.baidu.jinbao.innerapi.category.service.CategoryPropertyValueService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("categoryPropertyValueService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CategoryPropertyValueServiceImpl extends BaseService<CategoryPropertyValue, Long> 
        implements CategoryPropertyValueService {
    @Autowired
    private CategoryPropertyValueDao categoryPropertyValueDao;

    @Override
    public GenericMapperDao<CategoryPropertyValue, Long> getDao() {
        return this.categoryPropertyValueDao;
    }

    @Override
    public Integer insertRecords(List<CategoryPropertyValue> categoryPropertyValueList) {
        return this.categoryPropertyValueDao.batchInsert(categoryPropertyValueList);
    }

    @Override
    public Integer updateRecords(List<CategoryPropertyValue> categoryPropertyValueList) {
        return this.categoryPropertyValueDao.batchUpdate(categoryPropertyValueList);
    }

    @Override
    public Integer deleteRecords(List<Long> categoryPropertyValueIdList) {
        return this.categoryPropertyValueDao.batchDelete(categoryPropertyValueIdList);
    }

    @Override
    public List<CategoryPropertyValue> getRecords(List<Long> categoryPropertyValueIdList) {
        return this.categoryPropertyValueDao.batchSelect(categoryPropertyValueIdList);
    }
}