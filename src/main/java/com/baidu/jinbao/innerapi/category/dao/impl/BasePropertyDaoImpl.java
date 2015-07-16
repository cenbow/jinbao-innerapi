package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.dao.BasePropertyDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.BasePropertyMapper;

@Repository
public class BasePropertyDaoImpl extends BaseDao<BaseProperty, Long> implements BasePropertyDao {
    @Autowired
    private BasePropertyMapper basePropertyMapper;

    @Override
    public GenericMapper<BaseProperty, Long> getMapper() {
        return this.basePropertyMapper;
    }

    @Override
    public Integer batchInsert(List<BaseProperty> baseProperties) {
        if (CollectionUtils.isEmpty(baseProperties)) {
            return 0;
        }
        return this.basePropertyMapper.batchInsert(baseProperties);
    }

    @Override
    public Integer batchUpdate(List<BaseProperty> baseProperties) {
        if (CollectionUtils.isEmpty(baseProperties)) {
            return 0;
        }
        return this.basePropertyMapper.batchUpdate(baseProperties);
    }

    @Override
    public Integer batchDelete(List<Long> basePropertyIds) {
        if (CollectionUtils.isEmpty(basePropertyIds)) {
            return 0;
        }
        return this.basePropertyMapper.batchDelete(basePropertyIds);
    }

    @Override
    public List<BaseProperty> batchSelect(List<Long> basePropertyIds) {
        if (CollectionUtils.isEmpty(basePropertyIds)) {
            return new ArrayList<BaseProperty>();
        }
        return this.basePropertyMapper.batchSelect(basePropertyIds);
    }
}
