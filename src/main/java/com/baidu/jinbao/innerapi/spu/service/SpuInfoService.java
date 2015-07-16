package com.baidu.jinbao.innerapi.spu.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.vo.SpuTotalInfoVo;

public interface SpuInfoService extends GenericMapperService<SpuInfo, Long> {

    /**
     * spu相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param vo sku相关全量BO数据
     * @return 插入条数
     * */
    public Integer insertSpuTotalInfo(SpuTotalInfoVo vo);

    /**
     * spu相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param voList sku相关全量BO数据List
     * @return 插入条数
     * */
    public Integer insertSpuTotalInfoList(List<SpuTotalInfoVo> voList);

    /**
     * 批量插入SpuInfo
     * 
     * @param spuInfos 插入spuInfo列表
     * @return 插入条数
     */
    public Integer insertRecords(List<SpuInfo> spuInfos);

    /**
     * 批量更新SpuInfo
     * 
     * @param spuInfos 更新spuInfo列表
     * @return 更新条数
     */
    public Integer updateRecords(List<SpuInfo> spuInfos);

    /**
     * 批量删除SpuInfo
     * 
     * @param spuInfoIds 删除SpuInfoId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> spuInfoIds);

    /**
     * 批量查询SpuInfo
     * 
     * @param spuInfoIds 查询SpuInfo Id 列表
     * @return SpuInfo列表
     */
    public List<SpuInfo> getRecords(List<Long> spuInfoIds);
}
