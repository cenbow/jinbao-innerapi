package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuPpsService extends GenericMapperService<SkuPps, Long> {
    /**
     * 批量插入SkuPps
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuPpsList 插入SkuPps列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuPps> skuPpsList);

    /**
     * 批量更新SkuPps
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuPpsList 更新SkuPps列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuPps> skuPpsList);

    /**
     * 批量删除skuPps
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuPps
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuPps列表
     */
    public List<SkuPps> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuPps,直接插入不做处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuPpsList 插入SkuPps列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuPps> skuPpsList);
    
    /**
     * 插入SkuPps或者更新SkuPps,skuid, regionid, terminal作为唯一标志
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuPps 插入SkuPps
     * @return 插入或更新条数
     */
    public Integer updateInsertRecord(String splitDbInfo, SkuPps skuPps);
}
