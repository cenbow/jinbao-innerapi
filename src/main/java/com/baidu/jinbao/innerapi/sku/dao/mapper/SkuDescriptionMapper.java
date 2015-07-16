package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuDescriptionMapper extends GenericMapper<SkuDescription, Long> {

    /**
     * 批量插入SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescriptionList 更新SkuDescription列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<SkuDescription> skuDescriptionList);

    /**
     * 批量插入SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescriptionList 更新SkuDescription列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<SkuDescription> skuDescriptionList);

    /**
     * 批量删除SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuDescription列表
     */
    public List<SkuDescription> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 根据SkuId更新SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescription 更新SkuDescription
     * @return 更新条数
     */
    public Integer updateBySkuId(@Param("splitNumber") Integer splitNumber, 
            @Param("item") SkuDescription skuDescription);
}