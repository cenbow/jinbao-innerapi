package com.baidu.jinbao.innerapi.sku.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCommentDao extends GenericMapperDao<SkuComment, Long> {

    /**
     * 批量插入SkuComment
     * 
     * @param splitNumber 分表序号
     * @param skuCommentList 更新SkuComment列表
     * @return 插入条数
     */
    public Integer batchInsert(Integer splitNumber, List<SkuComment> skuCommentList);

    /**
     * 批量插入SkuComment
     * 
     * @param splitNumber 分表序号
     * @param skuCommentList 更新SkuComment列表
     * @return 更新条数
     */
    public Integer batchUpdate(Integer splitNumber, List<SkuComment> skuCommentList);

    /**
     * 批量删除SkuComment
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 删除条件
     * @return 删除条数
     */

    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition);

    /**
     * 批量获取SkuComment
     * 
     * @param splitNumber 分表序号
     * @param SkuCondition 查询条件
     * @return 获取到的SkuComment列表
     */
    public List<SkuComment> batchSelect(Integer splitNumber, SkuQueryCondition condition);
    
    /**
     * 批量更新SkuComment,按照skuid更新
     * 
     * @param splitNumber 分表序号
     * @param skuCommentList，
     * @return 更新条数
     */
    public Integer batchUpdateByKey(Integer splitNumber, List<SkuComment> skuCommentList);
}
