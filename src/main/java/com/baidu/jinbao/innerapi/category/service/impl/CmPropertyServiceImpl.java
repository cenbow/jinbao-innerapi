package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CmProperty;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyDao;
import com.baidu.jinbao.innerapi.category.service.CmPropertyService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("cmPropertyService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CmPropertyServiceImpl extends BaseService<CmProperty, Long> implements CmPropertyService {
    @Autowired
    private CmPropertyDao cmPropertyDao;

    @Override
    public GenericMapperDao<CmProperty, Long> getDao() {
        return this.cmPropertyDao;
    }

    @Override
    public Integer insertRecords(List<CmProperty> cmPropertyList) {
        return this.cmPropertyDao.batchInsert(cmPropertyList);
    }

    @Override
    public Integer updateRecords(List<CmProperty> cmPropertyList) {
        return this.cmPropertyDao.batchUpdate(cmPropertyList);
    }

    @Override
    public Integer deleteRecords(List<Long> cmPropertyIdList) {
        return this.cmPropertyDao.batchDelete(cmPropertyIdList);
    }

    @Override
    public List<CmProperty> getRecords(List<Long> cmPropertyIdList) {
        return this.cmPropertyDao.batchSelect(cmPropertyIdList);
    }
}