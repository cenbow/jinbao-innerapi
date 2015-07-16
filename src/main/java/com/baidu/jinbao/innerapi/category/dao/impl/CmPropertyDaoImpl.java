package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CmProperty;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CmPropertyMapper;

@Repository
public class CmPropertyDaoImpl extends BaseDao<CmProperty, Long> implements CmPropertyDao {
    @Autowired
    private CmPropertyMapper cmPropertyMapper;

    @Override
    public GenericMapper<CmProperty, Long> getMapper() {
        return this.cmPropertyMapper;
    }

    @Override
    public Integer batchInsert(List<CmProperty> cmProperties) {
        if (CollectionUtils.isEmpty(cmProperties)) {
            return 0;
        }
        return this.cmPropertyMapper.batchInsert(cmProperties);
    }

    @Override
    public Integer batchUpdate(List<CmProperty> cmProperties) {
        if (CollectionUtils.isEmpty(cmProperties)) {
            return 0;
        }
        return this.cmPropertyMapper.batchUpdate(cmProperties);
    }

    @Override
    public Integer batchDelete(List<Long> cmPropertyIds) {
        if (CollectionUtils.isEmpty(cmPropertyIds)) {
            return 0;
        }
        return this.cmPropertyMapper.batchDelete(cmPropertyIds);
    }

    @Override
    public List<CmProperty> batchSelect(List<Long> cmPropertyIds) {
        if (CollectionUtils.isEmpty(cmPropertyIds)) {
            return new ArrayList<CmProperty>();
        }
        return this.cmPropertyMapper.batchSelect(cmPropertyIds);
    }
}
