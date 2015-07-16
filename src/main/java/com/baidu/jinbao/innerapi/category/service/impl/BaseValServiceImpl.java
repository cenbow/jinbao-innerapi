package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.dao.BaseValDao;
import com.baidu.jinbao.innerapi.category.service.BaseValService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("baseValService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class BaseValServiceImpl extends BaseService<BaseVal, Long> implements BaseValService {
    @Autowired
    private BaseValDao baseValDao;

    @Override
    public GenericMapperDao<BaseVal, Long> getDao() {
        return this.baseValDao;
    }

    @Override
    public Integer insertRecords(List<BaseVal> baseValList) {
        return this.baseValDao.batchInsert(baseValList);
    }

    @Override
    public Integer updateRecords(List<BaseVal> baseValList) {
        return this.baseValDao.batchUpdate(baseValList);
    }

    @Override
    public Integer deleteRecords(List<Long> baseValIdList) {
        return this.baseValDao.batchDelete(baseValIdList);
    }

    @Override
    public List<BaseVal> getRecords(List<Long> baseValIdList) {
        return this.baseValDao.batchSelect(baseValIdList);
    }
}