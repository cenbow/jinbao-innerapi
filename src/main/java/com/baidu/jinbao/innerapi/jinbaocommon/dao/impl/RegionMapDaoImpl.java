
/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.RegionMapDao;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.mapper.RegionMapMapper;

@Repository
public class RegionMapDaoImpl extends BaseDao<RegionMap, Long>  implements RegionMapDao {

    @Autowired
    private RegionMapMapper regionMapMapper;

    @Override
    public GenericMapper<RegionMap, Long> getMapper() {
        return this.regionMapMapper;
    }

    @Override
    public int batchInsert(List<RegionMap> regionMapList) {
        if (CollectionUtils.isEmpty(regionMapList)) {
            return 0;
        }
        return regionMapMapper.batchInsert(regionMapList);
    }

    @Override
    public int batchUpdate(List<RegionMap> regionMapList) {
        if (CollectionUtils.isEmpty(regionMapList)) {
            return 0;
        }
        return regionMapMapper.batchUpdate(regionMapList);
    }

    @Override
    public int batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return regionMapMapper.batchDelete(idList);
    }

    @Override
    public List<RegionMap> batchSelect(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<RegionMap>();
        }
        return regionMapMapper.batchSelect(idList);
    }


}
