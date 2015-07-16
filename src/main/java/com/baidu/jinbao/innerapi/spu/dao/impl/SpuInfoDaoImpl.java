package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.dao.SpuInfoDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.SpuInfoMapper;

@Repository
public class SpuInfoDaoImpl extends BaseDao<SpuInfo, Long> implements SpuInfoDao {
    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Override
    public GenericMapper<SpuInfo, Long> getMapper() {
        return this.spuInfoMapper;
    }

    @Override
    public Integer batchInsert(List<SpuInfo> spuInfos) {
        if (CollectionUtils.isEmpty(spuInfos)) {
            return 0;
        }
        return this.spuInfoMapper.batchInsert(spuInfos);
    }

    @Override
    public Integer batchUpdate(List<SpuInfo> spuInfos) {
        if (CollectionUtils.isEmpty(spuInfos)) {
            return 0;
        }
        return this.spuInfoMapper.batchUpdate(spuInfos);
    }

    @Override
    public Integer batchDelete(List<Long> spuInfoIds) {
        if (CollectionUtils.isEmpty(spuInfoIds)) {
            return 0;
        }
        return this.spuInfoMapper.batchDelete(spuInfoIds);
    }

    @Override
    public List<SpuInfo> batchSelect(List<Long> spuInfoIds) {
        if (CollectionUtils.isEmpty(spuInfoIds)) {
            return new ArrayList<SpuInfo>();
        }
        return this.spuInfoMapper.batchSelect(spuInfoIds);
    }
}
