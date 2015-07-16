package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyValueDao;
import com.baidu.jinbao.innerapi.category.service.CmPropertyValueService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("cmPropertyValueService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class CmPropertyValueServiceImpl extends BaseService<CmPropertyValue, Long> implements CmPropertyValueService {
    @Autowired
    private CmPropertyValueDao cmPropertyValueDao;

    @Override
    public GenericMapperDao<CmPropertyValue, Long> getDao() {
        return this.cmPropertyValueDao;
    }

    @Override
    public Integer insertRecords(List<CmPropertyValue> cmPropertyValueList) {
        return this.cmPropertyValueDao.batchInsert(cmPropertyValueList);
    }

    @Override
    public Integer updateRecords(List<CmPropertyValue> cmPropertyValueList) {
        return this.cmPropertyValueDao.batchUpdate(cmPropertyValueList);
    }

    @Override
    public Integer deleteRecords(List<Long> cmPropertyValueIdList) {
        return this.cmPropertyValueDao.batchDelete(cmPropertyValueIdList);
    }

    @Override
    public List<CmPropertyValue> getRecords(List<Long> cmPropertyValueIdList) {
        return this.cmPropertyValueDao.batchSelect(cmPropertyValueIdList);
    }
}