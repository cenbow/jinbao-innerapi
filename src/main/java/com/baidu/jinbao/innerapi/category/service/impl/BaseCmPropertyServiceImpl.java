package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.dao.BaseCmPropertyDao;
import com.baidu.jinbao.innerapi.category.service.BaseCmPropertyService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.page.PageHelper;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("baseCmPropertyService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class BaseCmPropertyServiceImpl extends BaseService<BaseCmProperty, Long> implements BaseCmPropertyService {
    @Autowired
    private BaseCmPropertyDao baseCmPropertyDao;

    @Override
    public GenericMapperDao<BaseCmProperty, Long> getDao() {
        return this.baseCmPropertyDao;
    }

    @Override
    public Integer insertRecords(List<BaseCmProperty> baseCmPropertyList) {
        return this.baseCmPropertyDao.batchInsert(baseCmPropertyList);
    }

    @Override
    public Integer updateRecords(List<BaseCmProperty> baseCmPropertyList) {
        return this.baseCmPropertyDao.batchUpdate(baseCmPropertyList);
    }

    @Override
    public Integer deleteRecords(List<Long> baseCmPropertyIdList) {
        return this.baseCmPropertyDao.batchDelete(baseCmPropertyIdList);
    }

    @Override
    public List<BaseCmProperty> getRecords(List<Long> baseCmPropertyIdList) {
        return this.baseCmPropertyDao.batchSelect(baseCmPropertyIdList);
    }

    @Override
    public List<BaseCmProperty> getPageRecords() {
        PageHelper.startPage(0, 100);
        return this.baseCmPropertyDao.batchSelect(Arrays.asList(1L, 2L));
    }
}