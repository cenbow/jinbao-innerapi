package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuAttributeMapper extends GenericMapper<SkuAttribute, Long> {

    /**
     * 批量插入SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttributeList 更新SkuAttribute列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<SkuAttribute> skuAttributeList);

    /**
     * 批量插入SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttributeList 更新SkuAttribute列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber,
            @Param("list") List<SkuAttribute> skuAttributeList);

    /**
     * 批量删除SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuAttribute列表
     */
    public List<SkuAttribute> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 根据SkuId更新SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttribute 更新SkuAttribute
     * @return 更新条数
     */
    public Integer updateBySkuId(@Param("splitNumber") Integer splitNumber, @Param("item") SkuAttribute skuAttribute);
}