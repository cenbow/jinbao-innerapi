package com.baidu.jinbao.innerapi.spu.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.vo.CspuTotalInfoVo;

public interface CspuInfoService extends GenericMapperService<CspuInfo, Long> {

    /**
     * cspu相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param vo cspu相关全量BO数据
     * @return 插入条数
     * */
    public Integer insertCspuTotalInfo(CspuTotalInfoVo vo);

    /**
     * cspu相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param voList cspu相关全量BO数据List
     * @return 插入条数
     * */
    public Integer insertCspuTotalInfoList(List<CspuTotalInfoVo> voList);

    /**
     * 批量插入CspuInfo
     * 
     * @param cspuInfos 插入cspuInfo列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CspuInfo> cspuInfoList);

    /**
     * 批量更新CspuInfo
     * 
     * @param cspuInfos 更新cspuInfo列表
     * @return 更新条数
     */
    public Integer updateRecords(List<CspuInfo> cspuInfoList);

    /**
     * 批量删除CspuInfo
     * 
     * @param cspuInfoIds 删除CspuInfoId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> cspuInfoIdList);

    /**
     * 批量查询CspuInfo
     * 
     * @param cspuInfoIds 查询CspuInfo Id 列表
     * @return CspuInfo列表
     */
    public List<CspuInfo> getRecords(List<Long> cspuInfoIdList);
}
