package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCspuService extends GenericMapperService<SkuCspu, Long> {
    /**
     * 批量插入SkuCspu
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCspuList 插入SkuCspu列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuCspu> skuCspuList);

    /**
     * 批量更新SkuCspu
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCspuList 更新SkuCspu列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuCspu> skuCspuList);

    /**
     * 批量删除SkuCspu
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuCspu
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuCspu列表
     */
    public List<SkuCspu> getRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量插入SkuCspu, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCspuList 插入SkuCspu列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuCspu> skuCspuList);
    
    /**
     * 插入或更新SkuCspu
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCspu 插入更新SkuCspu
     * @return 插入更新条数
     */
    public Integer updateInsertRecord(String splitDbInfo, SkuCspu skuCspu);
}
