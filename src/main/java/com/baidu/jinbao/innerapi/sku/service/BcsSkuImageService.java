package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface BcsSkuImageService extends GenericMapperService<BcsSkuImage, Long> {
    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param bckSkuImageList 插入BcsSkuImage列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList);
    
    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param bckSkuImageList 插入BcsSkuImage列表
     * @return 插入条数
     */
    public Integer insertRecordsForCallback(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList);

    /**
     * 更新BcsSkuImage
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param bckSkuImageList 更新BcsSkuImage列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList);

    /**
     * 批量删除BcsSkuImage
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询BcsSkuImage
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return CmPropertyValue列表
     */
    public List<BcsSkuImage> getRecords(String splitDbInfo, SkuQueryCondition condition);

}
