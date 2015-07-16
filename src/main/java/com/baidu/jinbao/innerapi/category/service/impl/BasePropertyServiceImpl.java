package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.dao.BasePropertyDao;
import com.baidu.jinbao.innerapi.category.service.BasePropertyService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("basePropertyService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class BasePropertyServiceImpl extends BaseService<BaseProperty, Long> implements BasePropertyService {
    @Autowired
    private BasePropertyDao basePropertyDao;

    @Override
    public GenericMapperDao<BaseProperty, Long> getDao() {
        return this.basePropertyDao;
    }

    @Override
    public Integer insertRecords(List<BaseProperty> basePropertyList) {
        return this.basePropertyDao.batchInsert(basePropertyList);
    }

    @Override
    public Integer updateRecords(List<BaseProperty> basePropertyList) {
        return this.basePropertyDao.batchUpdate(basePropertyList);
    }

    @Override
    public Integer deleteRecords(List<Long> basePropertyIdList) {
        return this.basePropertyDao.batchDelete(basePropertyIdList);
    }

    @Override
    public List<BaseProperty> getRecords(List<Long> basePropertyIdList) {
        return this.basePropertyDao.batchSelect(basePropertyIdList);
    }
}