package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CmCategory;
import com.baidu.jinbao.innerapi.category.dao.CmCategoryDao;
import com.baidu.jinbao.innerapi.category.service.CmCategoryService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("cmCategoryService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CmCategoryServiceImpl extends BaseService<CmCategory, Long> implements CmCategoryService {
    @Autowired
    private CmCategoryDao cmCategoryDao;

    @Override
    public GenericMapperDao<CmCategory, Long> getDao() {
        return this.cmCategoryDao;
    }

    @Override
    public Integer insertRecords(List<CmCategory> cmCategoryList) {
        return this.cmCategoryDao.batchInsert(cmCategoryList);
    }

    @Override
    public Integer updateRecords(List<CmCategory> cmCategoryList) {
        return this.cmCategoryDao.batchUpdate(cmCategoryList);
    }

    @Override
    public Integer deleteRecords(List<Long> cmCategoryIdList) {
        return this.cmCategoryDao.batchDelete(cmCategoryIdList);
    }

    @Override
    public List<CmCategory> getRecords(List<Long> cmCategoryIdList) {
        return this.cmCategoryDao.batchSelect(cmCategoryIdList);
    }
}