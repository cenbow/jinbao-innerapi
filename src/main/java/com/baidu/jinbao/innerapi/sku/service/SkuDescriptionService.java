package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuDescriptionService extends GenericMapperService<SkuDescription, Long> {
    /**
     * 批量插入SkuDescription
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuDescriptionList 插入SkuDescription列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuDescription> skuDescriptionList);

    /**
     * 批量更新SkuDescription
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuDescriptionList 更新SkuDescription列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuDescription> kuDescription);

    /**
     * 批量删除sSkuDescription
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuDescription
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuDescription列表
     */
    public List<SkuDescription> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuDescription, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuDescriptionList 插入SkuDescription列表
     * @return 插入条数
     */
    Integer directInsertRecords(String splitDbInfo, List<SkuDescription> skuDescriptionList);
    
    /**
     * 根据SkuId更新SkuDescription
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuDescription 更新SkuDescription
     * @return 更新条数
     */
    public Integer updateBySkuId(String splitDbInfo, SkuDescription skuDescription);
}
