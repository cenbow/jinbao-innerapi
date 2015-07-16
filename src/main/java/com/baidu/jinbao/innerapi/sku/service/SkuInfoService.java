package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jinbao.innerapi.sku.vo.SkuTotalInfoVo;

public interface SkuInfoService extends GenericMapperService<SkuInfo, Long> {
    /**
     * 插入SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuInfoList 插入SkuInfo
     * @return 插入条数
     */
    public Integer insertRecord(String splitDbInfo, SkuInfo skuInfo);

    /**
     * sku相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param vo sku相关全量BO数据
     * @return 插入条数
     * */
    public Integer insertSkuTotalInfo(String splitDbInfo, SkuTotalInfoVo vo);

    /**
     * sku相关全量数据insert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param voList sku相关全量BO数据List
     * @return 插入条数
     * */
    public Integer insertSkuTotalInfoList(String splitDbInfo, List<SkuTotalInfoVo> voList);

    /**
     * sku相关全量数据updateInsert
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param voList sku相关全量BO数据List
     * @return 插入条数
     * */
    public Integer updateInsert(String splitDbInfo, SkuTotalInfoVo vo);

    /**
     * 批量插入SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuInfoList 插入SkuInfo列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuInfo> skuInfoList);

    /**
     * 批量更新SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuInfoList 更新SkuInfo列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuInfo> skuInfoList);

    /**
     * 批量删除SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuInfo列表
     */
    public List<SkuInfo> getRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 按sku_hashkey批量查询SkuInfo
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuInfo列表
     */
    public List<SkuInfo> getRecordsBySkuHashkey(String splitDbInfo, List<Long> skuHashKeyList);

    /**
     * 批量更新SkuInfo签名
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuIdList 更新SkuId列表
     * @param signanatureList 更新的签名列表
     * @param tableId 被修改需要更新签名的表
     * @return 更新条数
     */
    public Integer updateSignature(String splitDbInfo, List<String> skuIdList, List<String> signatureList, int tableId);
}
