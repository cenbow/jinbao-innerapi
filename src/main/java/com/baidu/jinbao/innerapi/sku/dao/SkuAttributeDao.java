package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuAttributeDao extends GenericMapperDao<SkuAttribute, Long> {

    /**
     * 批量插入SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttributeList 更新SkuAttribute列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuAttribute> skuAttributeList);

    /**
     * 批量更新SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttributeList 更新SkuAttribute列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuAttribute> skuAttributeList);

    /**
     * 批量删除SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuAttribute列表
     */
    public List<SkuAttribute> batchSelect(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 根据SkuId更新SkuAttribute
     * 
     * @param splitNumber 分表序号
     * @param skuAttribute 更新SkuAttribute
     * @return 更新条数
     */
    public Integer updateBySkuId(Integer splitNumber, SkuAttribute skuAttribute);
}
