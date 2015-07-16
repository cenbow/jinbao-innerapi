package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CategoryMap;
import com.baidu.jinbao.innerapi.category.dao.CategoryMapDao;
import com.baidu.jinbao.innerapi.category.service.CategoryMapService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("categoryMapService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CategoryMapServiceImpl extends BaseService<CategoryMap, Long> implements CategoryMapService {
    @Autowired
    private CategoryMapDao categoryMapDao;

    @Override
    public GenericMapperDao<CategoryMap, Long> getDao() {
        return this.categoryMapDao;
    }

    @Override
    public Integer insertRecords(List<CategoryMap> categoryMapList) {
        return this.categoryMapDao.batchInsert(categoryMapList);
    }

    @Override
    public Integer updateRecords(List<CategoryMap> categoryMapList) {
        return this.categoryMapDao.batchUpdate(categoryMapList);
    }

    @Override
    public Integer deleteRecords(List<Long> categoryMapIdList) {
        return this.categoryMapDao.batchDelete(categoryMapIdList);
    }

    @Override
    public List<CategoryMap> getRecords(List<Long> categoryMapIdList) {
        return this.categoryMapDao.batchSelect(categoryMapIdList);
    }
}