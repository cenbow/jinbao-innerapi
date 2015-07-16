package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuLevelDao extends GenericMapperDao<SkuLevel, Long> {

    /**
     * 批量插入SkuLevel
     * 
     * @param splitNumber 分表序号
     * @param skuLevelList 更新SkuLevel列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuLevel> skuLevelList);

    /**
     * 批量插入SkuLevel
     * 
     * @param splitNumber 分表序号
     * @param skuLevelList 更新SkuLevel列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuLevel> skuLevelList);

    /**
     * 批量删除SkuLevel
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuLevel
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuLevel列表
     */
    public List<SkuLevel> batchSelect(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 根据SkuId更新SkuLevel
     * 
     * @param splitNumber 分表序号
     * @param skuInfo 更新SkuLevel
     * @return 更新条数
     */
    public Integer updateBySkuId(Integer splitNumber, SkuLevel skuLevel);
}
