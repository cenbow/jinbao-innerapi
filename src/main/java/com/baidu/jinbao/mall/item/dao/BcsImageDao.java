package com.baidu.jinbao.mall.item.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.BcsImage;

public interface BcsImageDao extends GenericMapperDao<BcsImage, Long> {
    /**
     * 插入BcsImage
     * 
     * @param 插入BcsImage
     * @return 插入个数
     */
    public Long insertOne(BcsImage bcsImage);

    /**
     * 更新BcsImage
     * 
     * @param 更新BcsImage
     * @return 更新条数
     */
    public Integer update(BcsImage bcsImage);

    /**
     * 删除BcsImage
     * 
     * @param 删除增主键Id
     * @return 删除条数
     */
    public Integer delete(Long id);

    /**
     * 查询BcsImage
     * 
     * @param 查询自增主键Id
     * @return 查询的BcsImage结果
     */
    public BcsImage select(Long id);

    /**
     * 批量插入BcsImage
     * 
     * @param BcsImage list
     * @return 插入数目
     */
    public int insertRecords(List<BcsImage> bcsImageList);
    
    /**
     * 批量更新BcsImage
     * 
     * @param BcsImage list
     * @return 更新id
     */
    public int updateRecords(List<BcsImage> bcsImageList);
    
    /**
     * 批量查询
     * @param itemIdList
     * @param skuIdList
     * @return BcsImage List
     */
    public List<BcsImage> getRecordsBySkuIdOrItemId(List<Long> itemIdList, List<Long> skuIdList);
    
    /**
     * 批量删除BcsImage
     * 
     * @param 删除增主键Id列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> idList);
}
