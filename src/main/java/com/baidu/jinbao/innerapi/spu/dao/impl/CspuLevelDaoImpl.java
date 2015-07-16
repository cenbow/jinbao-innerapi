package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;
import com.baidu.jinbao.innerapi.spu.dao.CspuLevelDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.CspuLevelMapper;

@Repository
public class CspuLevelDaoImpl extends BaseDao<CspuLevel, Long> implements CspuLevelDao {
    @Autowired
    private CspuLevelMapper cspuLevelMapper;

    @Override
    public GenericMapper<CspuLevel, Long> getMapper() {
        return this.cspuLevelMapper;
    }

    @Override
    public Integer batchInsert(List<CspuLevel> cspuLevels) {
        if (CollectionUtils.isEmpty(cspuLevels)) {
            return 0;
        }
        return this.cspuLevelMapper.batchInsert(cspuLevels);
    }

    @Override
    public Integer batchUpdate(List<CspuLevel> cspuLevels) {
        if (CollectionUtils.isEmpty(cspuLevels)) {
            return 0;
        }
        return this.cspuLevelMapper.batchUpdate(cspuLevels);
    }

    @Override
    public Integer batchDelete(List<Long> cspuLevelIds) {
        if (CollectionUtils.isEmpty(cspuLevelIds)) {
            return 0;
        }
        return this.cspuLevelMapper.batchDelete(cspuLevelIds);
    }

    @Override
    public List<CspuLevel> batchSelect(List<Long> cspuLevelIds) {
        if (CollectionUtils.isEmpty(cspuLevelIds)) {
            return new ArrayList<CspuLevel>();
        }
        return this.cspuLevelMapper.batchSelect(cspuLevelIds);
    }
}
