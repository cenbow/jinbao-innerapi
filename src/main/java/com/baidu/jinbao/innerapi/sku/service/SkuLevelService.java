package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuLevelService extends GenericMapperService<SkuLevel, Long> {
    /**
     * 批量插入SkuLevel
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuLevelList 插入SkuLevel列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuLevel> skuLevelList);

    /**
     * 批量更新SkuLevel
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuLevelList 更新SkuLevel列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuLevel> skuLevelList);

    /**
     * 批量删除SkuLevel
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuLevel
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuLevel列表
     */
    public List<SkuLevel> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuLevel, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuLevelList 插入SkuLevel列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuLevel> skuLevelList);
    
    /**
     * 根据SkuId更新SkuLevel
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuInfo 更新SkuLevel
     * @return 更新条数
     */
    public Integer updateBySkuId(String splitDbInfo, SkuLevel skuLevel);
}
