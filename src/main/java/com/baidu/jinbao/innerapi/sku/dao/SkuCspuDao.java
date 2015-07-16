package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCspuDao extends GenericMapperDao<SkuCspu, Long> {

    /**
     * 批量插入SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList 更新SkuCspu列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuCspu> skuCspuList);

    /**
     * 批量插入SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList 更新SkuCspu列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuCspu> skuCspuList);

    /**
     * 批量删除SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuCspu
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuCspu列表
     */
    public List<SkuCspu> batchSelect(Integer splitNumber, SkuQueryCondition condition);
    
    /**
     * 批量更新SkuCspu,按照skuid，type 更新
     * 
     * @param splitNumber 分表序号
     * @param skuCspuList，
     * @return 更新条数
     */
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuCspu> skuCspuList);
}
