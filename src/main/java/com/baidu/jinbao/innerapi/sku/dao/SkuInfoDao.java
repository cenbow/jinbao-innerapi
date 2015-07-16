package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuInfoDao extends GenericMapperDao<SkuInfo, Long> {
    /**
     * 插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 插入SkuInfo
     * @return 插入条数
     */
    public Integer insertWithSplitNumber(Integer splitNumber, SkuInfo skuInfo);

    /**
     * 批量插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 更新SkuInfo列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuInfo> skuInfoList);

    /**
     * 批量插入SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfoList 更新SkuInfo列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuInfo> skuInfoList);

    /**
     * 批量删除SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuInfo列表
     */
    public List<SkuInfo> batchSelect(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 按sku_hashkey批量查询SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return SkuInfo列表
     */
    public List<SkuInfo> batchSelectBySkuHashkey(Integer splitNumber, List<Long> skuHashKeyList);

    /**
     * 根据SkuId更新SkuInfo
     * 
     * @param splitNumber 分表序号
     * @param skuInfo 更新SkuInfo
     * @return 更新条数
     */
    public Integer updateBySkuId(Integer splitNumber, SkuInfo skuInfo);
}
