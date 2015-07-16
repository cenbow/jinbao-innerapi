package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;
import com.baidu.jinbao.innerapi.category.dao.PropertyValueMapDao;
import com.baidu.jinbao.innerapi.category.service.PropertyValueMapService;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;

@Service("propertyValueMapService")
@SplitModule(moduleName = PdsConstants.CATEGROY_MODULE_DATASOURCE_KEY)
public class PropertyValueMapServiceImpl extends BaseService<PropertyValueMap, Long> 
        implements PropertyValueMapService {
    @Autowired
    private PropertyValueMapDao propertyValueMapDao;

    @Override
    public GenericMapperDao<PropertyValueMap, Long> getDao() {
        return this.propertyValueMapDao;
    }

    @Override
    public Integer insertRecords(List<PropertyValueMap> propertyValueMapList) {
        return this.propertyValueMapDao.batchInsert(propertyValueMapList);
    }

    @Override
    public Integer updateRecords(List<PropertyValueMap> propertyValueMapList) {
        return this.propertyValueMapDao.batchUpdate(propertyValueMapList);
    }

    @Override
    public Integer deleteRecords(List<Long> propertyValueMapIdList) {
        return this.propertyValueMapDao.batchDelete(propertyValueMapIdList);
    }

    @Override
    public List<PropertyValueMap> getRecords(List<Long> propertyValueMapIdList) {
        return this.propertyValueMapDao.batchSelect(propertyValueMapIdList);
    }
}