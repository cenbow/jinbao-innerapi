/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;

public interface RegionMapService extends GenericMapperService<RegionMap, Long> {

    /**
     * 批量插入regionMap
     * 
     * @param regionMaps : regionMap list
     * @return 影响的行数
     */
    public int insertRecords(List<RegionMap> regionMapList);

    /**
     * 批量更新regionMap
     * 
     * @param regionMaps : regionMap list
     */
    public int updateRecords(List<RegionMap> regionMapList);

    /**
     * 批量删除
     * 
     * @param ids
     * @return 影响的行数
     */
    public int deleteRecords(List<Long> idList);

    /**
     * 批量查询
     * 
     * @param ids
     * @return 获取的regionMap list
     */
    public List<RegionMap> getRecords(List<Long> idList);

}
