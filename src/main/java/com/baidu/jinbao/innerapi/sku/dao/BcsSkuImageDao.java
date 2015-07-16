package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface BcsSkuImageDao extends GenericMapperDao<BcsSkuImage, Long> {

    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param bcsSkuImageList 更新BcsSkuImage列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList);

    /**
     * 批量插入BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param bcsSkuImageList 更新BcsSkuImage列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList);

    /**
     * 批量删除BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取BcsSkuImage
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuComment列表
     */
    public List<BcsSkuImage> batchSelect(Integer splitNumber, SkuQueryCondition condition);

}
