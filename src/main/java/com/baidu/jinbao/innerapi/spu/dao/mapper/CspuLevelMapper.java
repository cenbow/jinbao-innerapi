package com.baidu.jinbao.innerapi.spu.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;

public interface CspuLevelMapper extends GenericMapper<CspuLevel, Long> {
    /**
     * 批量插入CspuLevel
     * 
     * @param cspuLevels 插入cspuLevel列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CspuLevel> cspuLevels);

    /**
     * 批量更新CspuLevel
     * 
     * @param cspuLevels 更新cspuLevel列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<CspuLevel> cspuLevels);

    /**
     * 批量删除CspuLevel
     * 
     * @param cspuLevelIds 删除CspuLevelId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> cspuLevelIds);

    /**
     * 批量查询CspuLevel
     * 
     * @param cspuLevelIds 查询CspuLevel Id 列表
     * @return CspuLevel列表
     */
    public List<CspuLevel> batchSelect(List<Long> cspuLevelIds);
}
