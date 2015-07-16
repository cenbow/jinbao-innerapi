package com.baidu.jinbao.innerapi.spu.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;

public interface SpuInfoDao extends GenericMapperDao<SpuInfo, Long> {
    /**
     * 批量插入SpuInfo
     * 
     * @param spuInfos 插入spuInfo列表
     * @return 插入条数
     */
    public Integer batchInsert(List<SpuInfo> spuInfos);

    /**
     * 批量更新SpuInfo
     * 
     * @param spuInfos 更新spuInfo列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<SpuInfo> spuInfos);

    /**
     * 批量删除SpuInfo
     * 
     * @param spuInfoIds 删除SpuInfoId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> spuInfoIds);

    /**
     * 批量查询SpuInfo
     * 
     * @param spuInfoIds 查询SpuInfo Id 列表
     * @return SpuInfo列表
     */
    public List<SpuInfo> batchSelect(List<Long> spuInfoIds);
}
