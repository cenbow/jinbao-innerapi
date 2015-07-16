package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;
import com.baidu.jinbao.innerapi.category.dao.BaseCmValueDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.BaseCmValueMapper;

@Repository
public class BaseCmValueDaoImpl extends BaseDao<BaseCmValue, Long> implements BaseCmValueDao {
    @Autowired
    private BaseCmValueMapper baseCmValueMapper;

    @Override
    public GenericMapper<BaseCmValue, Long> getMapper() {
        return this.baseCmValueMapper;
    }

    @Override
    public Integer batchInsert(List<BaseCmValue> baseCmValues) {
        if (CollectionUtils.isEmpty(baseCmValues)) {
            return 0;
        }
        return this.baseCmValueMapper.batchInsert(baseCmValues);
    }

    @Override
    public Integer batchUpdate(List<BaseCmValue> baseCmValues) {
        if (CollectionUtils.isEmpty(baseCmValues)) {
            return 0;
        }
        return this.baseCmValueMapper.batchUpdate(baseCmValues);
    }

    @Override
    public Integer batchDelete(List<Long> baseCmValueIds) {
        if (CollectionUtils.isEmpty(baseCmValueIds)) {
            return 0;
        }
        return this.baseCmValueMapper.batchDelete(baseCmValueIds);
    }

    @Override
    public List<BaseCmValue> batchSelect(List<Long> baseCmValueIds) {
        if (CollectionUtils.isEmpty(baseCmValueIds)) {
            return new ArrayList<BaseCmValue>();
        }
        return this.baseCmValueMapper.batchSelect(baseCmValueIds);
    }
}
