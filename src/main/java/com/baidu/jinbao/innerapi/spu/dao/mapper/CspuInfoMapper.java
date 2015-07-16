package com.baidu.jinbao.innerapi.spu.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;

public interface CspuInfoMapper extends GenericMapper<CspuInfo, Long> {
    /**
     * 批量插入CspuInfo
     * 
     * @param cspuInfos 插入cspuInfo列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CspuInfo> cspuInfos);

    /**
     * 批量更新CspuInfo
     * 
     * @param cspuInfos 更新cspuInfo列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<CspuInfo> cspuInfos);

    /**
     * 批量删除CspuInfo
     * 
     * @param cspuInfoIds 删除CspuInfoId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> cspuInfoIds);

    /**
     * 批量查询CspuInfo
     * 
     * @param cspuInfoIds 查询CspuInfo Id 列表
     * @return CspuInfo列表
     */
    public List<CspuInfo> batchSelect(List<Long> cspuInfoIds);
}
