package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCdtService extends GenericMapperService<SkuCdt, Long> {
    /**
     * 批量插入SkuCdt
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCdtList 插入SkuCdt列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuCdt> skuCdtList);

    /**
     * 批量更新SkuCdt
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCdtList 更新SkuCdt列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuCdt> skuCdtList);

    /**
     * 批量删除skuCdt
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuCdt
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuCdt列表
     */
    public List<SkuCdt> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuCdt, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCdtList 插入SkuCdt列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuCdt> skuCdtList);

    /**
     * 插入或更新SkuCdt
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCdt 插入更新SkuCdt
     * @return 插入更新条数
     */
    public Integer updateInsertRecord(String splitDbInfo, SkuCdt skuCdt);

}
