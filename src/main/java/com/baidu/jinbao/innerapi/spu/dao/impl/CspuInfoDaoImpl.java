package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.dao.CspuInfoDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.CspuInfoMapper;

@Repository
public class CspuInfoDaoImpl extends BaseDao<CspuInfo, Long> implements CspuInfoDao {
    @Autowired
    private CspuInfoMapper cspuInfoMapper;

    @Override
    public GenericMapper<CspuInfo, Long> getMapper() {
        return this.cspuInfoMapper;
    }

    @Override
    public Integer batchInsert(List<CspuInfo> cspuInfos) {
        if (CollectionUtils.isEmpty(cspuInfos)) {
            return 0;
        }
        return this.cspuInfoMapper.batchInsert(cspuInfos);
    }

    @Override
    public Integer batchUpdate(List<CspuInfo> cspuInfos) {
        if (CollectionUtils.isEmpty(cspuInfos)) {
            return 0;
        }
        return this.cspuInfoMapper.batchUpdate(cspuInfos);
    }

    @Override
    public Integer batchDelete(List<Long> cspuInfoIds) {
        if (CollectionUtils.isEmpty(cspuInfoIds)) {
            return 0;
        }
        return this.cspuInfoMapper.batchDelete(cspuInfoIds);
    }

    @Override
    public List<CspuInfo> batchSelect(List<Long> cspuInfoIds) {
        if (CollectionUtils.isEmpty(cspuInfoIds)) {
            return new ArrayList<CspuInfo>();
        }
        return this.cspuInfoMapper.batchSelect(cspuInfoIds);
    }
}
