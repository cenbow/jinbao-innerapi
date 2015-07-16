package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuInfoMapper extends GenericMapper<SkuInfo, Long> {
    /**
     * 插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 插入SkuInfo
     * @return 插入条数
     */
    public Integer insertWithSplitNumber(@Param("splitNumber") Integer splitNumber, @Param("skuInfo") SkuInfo skuInfo);

    /**
     * 批量插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 插入SkuInfo列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuInfo> skuInfoList);

    /**
     * 批量插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 更新SkuInfo列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuInfo> skuInfoList);

    /**
     * 批量删除SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuInfo列表
     */
    public List<SkuInfo> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 按sku_hashkey批量查询SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return SkuInfo列表
     */
    public List<SkuInfo> batchSelectBySkuHashkey(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<Long> skuHashKeyList);

    /**
     * 根据SkuId更新SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfo 更新SkuInfo
     * @return 更新条数
     */
    public Integer updateBySkuId(@Param("splitNumber") Integer splitNumber, @Param("item") SkuInfo skuInfo);
}