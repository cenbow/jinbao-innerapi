package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.dao.BaseCmPropertyDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.BaseCmPropertyMapper;

@Repository
public class BaseCmPropertyDaoImpl extends BaseDao<BaseCmProperty, Long> implements BaseCmPropertyDao {
    @Autowired
    private BaseCmPropertyMapper baseCmPropertyMapper;

    @Override
    public GenericMapper<BaseCmProperty, Long> getMapper() {
        return this.baseCmPropertyMapper;
    }

    @Override
    public Integer batchInsert(List<BaseCmProperty> baseCmProperties) {
        if (CollectionUtils.isEmpty(baseCmProperties)) {
            return 0;
        }
        return this.baseCmPropertyMapper.batchInsert(baseCmProperties);
    }

    @Override
    public Integer batchUpdate(List<BaseCmProperty> baseCmProperties) {
        if (CollectionUtils.isEmpty(baseCmProperties)) {
            return 0;
        }
        return this.baseCmPropertyMapper.batchUpdate(baseCmProperties);
    }

    @Override
    public Integer batchDelete(List<Long> baseCmPropertyIds) {
        if (CollectionUtils.isEmpty(baseCmPropertyIds)) {
            return 0;
        }
        return this.baseCmPropertyMapper.batchDelete(baseCmPropertyIds);
    }

    @Override
    public List<BaseCmProperty> batchSelect(List<Long> baseCmPropertyIds) {
        if (CollectionUtils.isEmpty(baseCmPropertyIds)) {
            return new ArrayList<BaseCmProperty>();
        }
        return this.baseCmPropertyMapper.batchSelect(baseCmPropertyIds);
    }
}
