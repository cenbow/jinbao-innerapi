package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;
import com.baidu.jinbao.innerapi.category.dao.PropertyValueMapDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.PropertyValueMapMapper;

@Repository
public class PropertyValueMapDaoImpl extends BaseDao<PropertyValueMap, Long> implements PropertyValueMapDao {
    @Autowired
    private PropertyValueMapMapper propertyValueMapMapper;

    @Override
    public GenericMapper<PropertyValueMap, Long> getMapper() {
        return this.propertyValueMapMapper;
    }

    @Override
    public Integer batchInsert(List<PropertyValueMap> propertyValueMaps) {
        if (CollectionUtils.isEmpty(propertyValueMaps)) {
            return 0;
        }
        return this.propertyValueMapMapper.batchInsert(propertyValueMaps);
    }

    @Override
    public Integer batchUpdate(List<PropertyValueMap> propertyValueMaps) {
        if (CollectionUtils.isEmpty(propertyValueMaps)) {
            return 0;
        }
        return this.propertyValueMapMapper.batchUpdate(propertyValueMaps);
    }

    @Override
    public Integer batchDelete(List<Long> propertyValueMapIds) {
        if (CollectionUtils.isEmpty(propertyValueMapIds)) {
            return 0;
        }
        return this.propertyValueMapMapper.batchDelete(propertyValueMapIds);
    }

    @Override
    public List<PropertyValueMap> batchSelect(List<Long> propertyValueMapIds) {
        if (CollectionUtils.isEmpty(propertyValueMapIds)) {
            return new ArrayList<PropertyValueMap>();
        }
        return this.propertyValueMapMapper.batchSelect(propertyValueMapIds);
    }
}
