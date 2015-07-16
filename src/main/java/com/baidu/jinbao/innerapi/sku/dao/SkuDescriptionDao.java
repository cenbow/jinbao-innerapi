package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuDescriptionDao extends GenericMapperDao<SkuDescription, Long> {

    /**
     * 批量插入SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescriptionList 更新SkuDescription列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuDescription> skuDescriptionList);

    /**
     * 批量插入SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescriptionList 更新SkuDescription列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuDescription> skuDescriptionList);

    /**
     * 批量删除SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuDescription列表
     */
    public List<SkuDescription> batchSelect(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 根据SkuId更新SkuDescription
     * 
     * @param splitNumber 分表序号
     * @param skuDescription 更新SkuDescription
     * @return 更新条数
     */
    public Integer updateBySkuId(Integer splitNumber, SkuDescription skuDescription);
}
