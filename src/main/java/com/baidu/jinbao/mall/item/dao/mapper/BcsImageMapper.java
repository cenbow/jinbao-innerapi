package com.baidu.jinbao.mall.item.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.BcsImage;

public interface BcsImageMapper extends GenericMapper<BcsImage, Long> {
    /**
     * 插入BcsImage
     * 
     * @param 插入BcsImage
     * @return 插入条数
     */
    public int insertOne(BcsImage bcsImage);

    /**
     * 更新BcsImage
     * 
     * @param 更新BcsImage
     * @return 更新条数
     */
    public int update(BcsImage bcsImage);

    /**
     * 删除BcsImage
     * 
     * @param 删除增主键Id
     * @return 删除条数
     */
    public int delete(Long id);

    /**
     * 查询BcsImage
     * 
     * @param 查询自增主键Id
     * @return 查询的BcsImage结果
     */
    public BcsImage select(Long id);

    /**
     * 插入BcsImage
     * 
     * @param BcsImage 插入list
     * @return 插入数目
     */
    public int insertRecords(List<BcsImage> bcsImageList);

    /**
     * 更新BcsImage
     * 
     * @param BcsImage 更新list
     * @return 更新数目
     */
    public int updateRecords(List<BcsImage> bcsImageList);

    /**
     * 批量插入
     * 
     * @param itemIdList
     * @param skuIdList
     * @returnList<BcsImage>
     */
    public List<BcsImage> getRecordsBySkuIdOrItemId(@Param("map") Map<String, Object> map);
    
    /**
     * 批量删除BcsImage
     * 
     * @param 删除增主键Id列表
     * @return 删除条数
     */
    public Integer batchDelete(@Param("list") List<Long> idList);
}