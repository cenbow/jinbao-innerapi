package com.baidu.jinbao.innerapi.sku.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

public interface SkuCommentService extends GenericMapperService<SkuComment, Long> {
    /**
     * 批量插入SkuComment
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCommentList 插入SkuComment列表
     * @return 插入条数
     */
    public Integer insertRecords(String splitDbInfo, List<SkuComment> skuCommentList);

    /**
     * 批量更新SkuComment
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCommentList 更新SkuComment列表
     * @return 更新条数
     */
    public Integer updateRecords(String splitDbInfo, List<SkuComment> skuCommentList);

    /**
     * 批量删除SkuComment
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 删除条件
     * @return 删除条数
     */
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition);

    /**
     * 批量查询SkuComment
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param SkuCondition 查询条件
     * @return SkuComment列表
     */
    public List<SkuComment> getRecords(String splitDbInfo, SkuQueryCondition condition);
    
    /**
     * 批量插入SkuComment, 直接插入不做逻辑处理
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuCommentList 插入SkuComment列表
     * @return 插入条数
     */
    public Integer directInsertRecords(String splitDbInfo, List<SkuComment> skuCommentList);
    
    /**
     * 插入SkuComment或者更新SkuCommnet,主键skuid
     * 
     * @param splitDbInfo 分片信息（eg: db0_0，表示db0连接，0分片）
     * @param skuComment 插入SkuComment
     * @return 插入或更新条数
     */
    public Integer updateInsertRecord(String splitDbInfo, SkuComment skuComment);
}
