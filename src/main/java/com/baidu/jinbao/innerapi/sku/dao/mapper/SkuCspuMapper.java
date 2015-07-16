package com.baidu.jinbao.innerapi.sku.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCspuMapper extends GenericMapper<SkuCspu, Long> {

    /**
     * 批量插入SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList 更新SkuCspu列表
     * @return 插入条数
     */
    public Integer batchInsert(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuCspu> skuCspuList);

    /**
     * 批量插入SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList 更新SkuCspu列表
     * @return 更新条数
     */
    public Integer batchUpdate(@Param("splitNumber") Integer splitNumber, @Param("list") List<SkuCspu> skuCspuList);

    /**
     * 批量删除SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);

    /**
     * 批量获取SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuCspu列表
     */
    public List<SkuCspu> batchSelect(@Param("splitNumber") Integer splitNumber,
            @Param("condition") SkuQueryCondition condition);
    
    /**
     * 批量更新SkuCspu,按照skuid，type 更新
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList，
     * @return 更新条数
     */
    public Integer batchUpdateByKey(@Param("splitNumber") Integer splitNumber, 
            @Param("list") List<SkuCspu> skuCspuList);
}