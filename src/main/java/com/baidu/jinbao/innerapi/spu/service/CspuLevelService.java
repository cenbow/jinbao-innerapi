package com.baidu.jinbao.innerapi.spu.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;

public interface CspuLevelService extends GenericMapperService<CspuLevel, Long> {

    /**
     * 批量插入CspuLevel
     * 
     * @param cspuLevels 插入cspuLevel列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CspuLevel> cspuLevelList);

    /**
     * 批量更新CspuLevel
     * 
     * @param cspuLevels 更新cspuLevel列表
     * @return 更新条数
     */
    public Integer updateRecords(List<CspuLevel> cspuLevelList);

    /**
     * 批量删除CspuLevel
     * 
     * @param cspuLevelIds 删除CspuLevelId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> cspuLevelIdList);

    /**
     * 批量查询CspuLevel
     * 
     * @param cspuLevelIds 查询CspuLevel Id 列表
     * @return CspuLevel列表
     */
    public List<CspuLevel> getRecords(List<Long> cspuLevelIdList);
}
