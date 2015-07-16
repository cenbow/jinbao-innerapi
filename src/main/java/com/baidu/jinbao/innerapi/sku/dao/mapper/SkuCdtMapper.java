package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCdtMapper extends GenericMapper<SkuCdt, Long> {

    /**
     * 批量插入SkuCdt
     * 
     * @param splitNumber 分表序号
     * @param skuCdtList 更新SkuCdt列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuCdt> skuCdtList);

    /**
     * 批量插入SkuCdt
     * 
     * @param splitNumber 分表序号
     * @param skuCdtList 更新SkuCdt列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuCdt> skuCdtList);

    /**
     * 批量删除SkuCdt
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取SkuCdt
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuCdt列表
     */
    public List<SkuCdt> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);
    
    /**
     * 批量更新SkuCdt,按照skuid，classificationtype 更新
     * 
     * @param splitNumber 分表序号
     * @param skuCdtList，
     * @return 更新条数
     */
    public Integer batchUpdateByKey(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuCdt> skuCdtList);
}