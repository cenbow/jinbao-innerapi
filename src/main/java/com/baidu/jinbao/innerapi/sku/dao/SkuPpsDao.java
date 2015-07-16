package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuPpsDao extends GenericMapperDao<SkuPps, Long> {

    /**
     * 批量插入SkuPps
     * 
     * @param splitNumber 分表序号
     * @param skuPpsList 更新SkuPps列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuPps> skuPpsList);

    /**
     * 批量更新SkuPps
     * 
     * @param splitNumber 分表序号
     * @param skuPpsList 更新SkuPps列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuPps> skuPpsList);

    /**
     * 批量删除SkuPps
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuPps
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuPps列表
     */
    public List<SkuPps> batchSelect(Integer splitNumber, SkuQueryCondition condition);
    
    /**
     * 批量获取SkuPps
     * 
     * @param splitNumber 分表序号
     * @param skuPpsList，按照skuid，regionid，terminal 查询
     * @return 获取到的SkuPps列表
     */
    public List<SkuPps> batchSelectByKey(Integer splitNumber, List<SkuPps> skuPpsList);
    
    /**
     * 批量更新SkuPps
     * 
     * @param splitNumber 分表序号
     * @param skuPpsList，按照skuid，regionid，terminal 更新
     * @return 更新条数
     */
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuPps> skuPpsList);
}
