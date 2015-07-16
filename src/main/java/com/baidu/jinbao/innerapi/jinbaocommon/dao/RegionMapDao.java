
/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;

public interface RegionMapDao extends GenericMapperDao<RegionMap, Long> {
    /**
     * 批量插入regionMap
     * 
     * @param regionMaps : regionMap list
     * @return 影响的行数
     */    
    public int batchInsert(List<RegionMap> regionMapList);
    
    /**
     * 批量更新regionMap
     * @param regionMaps : regionMap list
     */
    public int batchUpdate(List<RegionMap> regionMapList);
    
    /**
     * 批量删除
     * @param ids
     * @return 影响的行数
     */
    public int batchDelete(List<Long> idList);

    /**
     * 批量查询
     * @param ids
     * @return 获取的regionMap list
     */
    public List<RegionMap> batchSelect(List<Long> idList);
    
}
