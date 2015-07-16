package com.baidu.jinbao.innerapi.spu.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;

public interface SpuCommentService extends GenericMapperService<SpuComment, Long> {

    /**
     * 批量插入SpuComment
     * 
     * @param spuComments 插入spuComment列表
     * @return 插入条数
     */
    public Integer insertRecords(List<SpuComment> spuComments);

    /**
     * 批量更新SpuComment
     * 
     * @param spuComments 更新spuComment列表
     * @return 更新条数
     */
    public Integer updateRecords(List<SpuComment> spuComments);

    /**
     * 批量删除SpuComment
     * 
     * @param spuCommentIds 删除SpuCommentId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> spuCommentIds);

    /**
     * 批量查询SpuComment
     * 
     * @param spuCommentIds 查询SpuComment Id 列表
     * @return SpuComment列表
     */
    public List<SpuComment> getRecords(List<Long> spuCommentIds);
}
