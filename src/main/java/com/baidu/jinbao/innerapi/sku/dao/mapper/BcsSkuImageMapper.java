package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface BcsSkuImageMapper extends GenericMapper<BcsSkuImage, Long> {

    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param bcsSkuImageList 更新BcsSkuImage列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<BcsSkuImage> bcsSkuImageList);

    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param bcsSkuImageList 更新BcsSkuImage列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<BcsSkuImage> bcsSkuImageList);

    /**
     * 批量删除BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的BcsSkuImage列表
     */
    public List<BcsSkuImage> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

}