package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuAttributeService extends GenericMapperService<SkuAttribute, Long> {
    /**
     * 批量插入SkuAttribute
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuAttributeList 插入SkuAttribute列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList);

    /**
     * 批量更新SkuAttribute
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuAttributeList 更新SkuAttribute列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList);

    /**
     * 批量删除skuAttribute
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuAttribute
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuAttribute列表
     */
    public List<SkuAttribute> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuAttribute, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuAttributeList 插入SkuAttribute列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuAttribute> skuAttributeList);
    
    /**
     * 根据SkuId更新SkuAttribute
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuAttribute 更新SkuAttribute
     * @return 更新条数
     */
    public Integer updateBySkuId(String splitDbInfo, SkuAttribute skuAttribute);
}
