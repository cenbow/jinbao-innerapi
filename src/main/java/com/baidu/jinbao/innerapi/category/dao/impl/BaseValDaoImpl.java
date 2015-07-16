package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.dao.BaseValDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.BaseValMapper;

@Repository
public class BaseValDaoImpl extends BaseDao<BaseVal, Long> implements BaseValDao {
    @Autowired
    private BaseValMapper baseValMapper;

    @Override
    public GenericMapper<BaseVal, Long> getMapper() {
        return this.baseValMapper;
    }

    @Override
    public Integer batchInsert(List<BaseVal> baseVals) {
        if (CollectionUtils.isEmpty(baseVals)) {
            return 0;
        }
        return this.baseValMapper.batchInsert(baseVals);
    }

    @Override
    public Integer batchUpdate(List<BaseVal> baseVals) {
        if (CollectionUtils.isEmpty(baseVals)) {
            return 0;
        }
        return this.baseValMapper.batchUpdate(baseVals);
    }

    @Override
    public Integer batchDelete(List<Long> baseValIds) {
        if (CollectionUtils.isEmpty(baseValIds)) {
            return 0;
        }
        return this.baseValMapper.batchDelete(baseValIds);
    }

    @Override
    public List<BaseVal> batchSelect(List<Long> baseValIds) {
        if (CollectionUtils.isEmpty(baseValIds)) {
            return new ArrayList<BaseVal>();
        }
        return this.baseValMapper.batchSelect(baseValIds);
    }
}
