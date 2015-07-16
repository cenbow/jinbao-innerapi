package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyValueDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CmPropertyValueMapper;

@Repository
public class CmPropertyValueDaoImpl extends BaseDao<CmPropertyValue, Long> implements CmPropertyValueDao {
    @Autowired
    private CmPropertyValueMapper cmPropertyValueMapper;

    @Override
    public GenericMapper<CmPropertyValue, Long> getMapper() {
        return this.cmPropertyValueMapper;
    }

    @Override
    public Integer batchInsert(List<CmPropertyValue> cmPropertyValues) {
        if (CollectionUtils.isEmpty(cmPropertyValues)) {
            return 0;
        }
        return this.cmPropertyValueMapper.batchInsert(cmPropertyValues);
    }

    @Override
    public Integer batchUpdate(List<CmPropertyValue> cmPropertyValues) {
        if (CollectionUtils.isEmpty(cmPropertyValues)) {
            return 0;
        }
        return this.cmPropertyValueMapper.batchUpdate(cmPropertyValues);
    }

    @Override
    public Integer batchDelete(List<Long> cmPropertyValueIds) {
        if (CollectionUtils.isEmpty(cmPropertyValueIds)) {
            return 0;
        }
        return this.cmPropertyValueMapper.batchDelete(cmPropertyValueIds);
    }

    @Override
    public List<CmPropertyValue> batchSelect(List<Long> cmPropertyValueIds) {
        if (CollectionUtils.isEmpty(cmPropertyValueIds)) {
            return new ArrayList<CmPropertyValue>();
        }
        return this.cmPropertyValueMapper.batchSelect(cmPropertyValueIds);
    }
}
