package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;
import com.baidu.jinbao.innerapi.category.dao.BaseCmValueDao;
import com.baidu.jinbao.innerapi.category.service.BaseCmValueService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("baseCmValueService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class BaseCmValueServiceImpl extends BaseService<BaseCmValue, Long> implements BaseCmValueService {
    @Autowired
    private BaseCmValueDao baseCmValueDao;

    @Override
    public GenericMapperDao<BaseCmValue, Long> getDao() {
        return this.baseCmValueDao;
    }

    @Override
    public Integer insertRecords(List<BaseCmValue> baseCmValueList) {
        return this.baseCmValueDao.batchInsert(baseCmValueList);
    }

    @Override
    public Integer updateRecords(List<BaseCmValue> baseCmValueList) {
        return this.baseCmValueDao.batchUpdate(baseCmValueList);
    }

    @Override
    public Integer deleteRecords(List<Long> baseCmValueIdList) {
        return this.baseCmValueDao.batchDelete(baseCmValueIdList);
    }

    @Override
    public List<BaseCmValue> getRecords(List<Long> baseCmValueIdList) {
        return this.baseCmValueDao.batchSelect(baseCmValueIdList);
    }
}