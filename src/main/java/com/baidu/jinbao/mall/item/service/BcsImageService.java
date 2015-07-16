package com.baidu.jinbao.mall.item.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.BcsImage;

/**
 * BcsImage接口
 * 
 * @author duzeyu
 * @date 2015-7-5 下午4:20
 */
public interface BcsImageService extends GenericMapperService<BcsImage, Long> {
    /**
     * 插入BcsImage
     * 
     * @param BcsImage 插入单条
     * @return 插入id
     */
    public Long insertRecord(BcsImage bcsImage);

    /**
     * 插入BcsImage
     * 
     * @param BcsImage 插入多条
     * @return 插入id
     */
    public Integer insertRecords(List<BcsImage> bcsImageList);

    /**
     * 更新BcsImage
     * 
     * @param BcsImage 更新单条
     * @return 更新的条数
     */
    public Integer updateRecord(BcsImage bcsImage);

    /**
     * 删除BcsImage
     * 
     * @param id 删除单条id
     * @return 删除的id
     */
    public Long deleteRecord(Long id);

    /**
     * 
     * @param 查询单条 id
     * @return BcsImage
     */
    public BcsImage getRecord(Long id);

    /**
     * 插入BcsImage
     * 
     * @param BcsImage 更新多条
     * @return 更新数目
     */
    public Integer updateRecords(List<BcsImage> bcsImageList);

    /**
     * 批量查询
     * 
     * @param itemIdList,SkuIdList
     * @return BcsImage vo list
     */
    public List<BcsImage> getRecordsBySkuIdOrItemId(List<Long> itemIdList, List<Long> skuIdList);
    
    /**
     * 批量删除BcsImage
     * 
     * @param idList，主键idList
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> idList);
}
