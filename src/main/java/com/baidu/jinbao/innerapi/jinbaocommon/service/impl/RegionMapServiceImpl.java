/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.RegionMapDao;
import com.baidu.jinbao.innerapi.jinbaocommon.service.RegionMapService;

@Service("regionMapService")
@SplitModule(moduleName = PdsConstants.COMMON_MODULE_DATASOURCE_KEY)
public class RegionMapServiceImpl extends BaseService<RegionMap, Long> implements RegionMapService {

    @Autowired
    private RegionMapDao regionMapDao;

    @Override
    public GenericMapperDao<RegionMap, Long> getDao() {
        return regionMapDao;
    }

    @Override
    public int insertRecords(List<RegionMap> regionMapList) {
        if (CollectionUtils.isEmpty(regionMapList)) {
            return 0;
        }
        return this.regionMapDao.batchInsert(regionMapList);
    }

    @Override
    public int updateRecords(List<RegionMap> regionMapList) {
        if (CollectionUtils.isEmpty(regionMapList)) {
            return 0;
        }
        return this.regionMapDao.batchUpdate(regionMapList);
    }

    @Override
    public int deleteRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return this.regionMapDao.batchDelete(idList);
    }

    @Override
    public List<RegionMap> getRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<RegionMap>();
        }
        return this.regionMapDao.batchSelect(idList);
    }
}
