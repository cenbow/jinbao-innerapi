package com.baidu.jinbao.innerapi.spu.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;

public interface SpuCommentMapper extends GenericMapper<SpuComment, Long> {
    /**
     * 批量插入SpuComment
     * 
     * @param spuComments 插入spuComment列表
     * @return 插入条数
     */
    public Integer batchInsert(List<SpuComment> spuComments);

    /**
     * 批量更新SpuComment
     * 
     * @param spuComments 更新spuComment列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<SpuComment> spuComments);

    /**
     * 批量删除SpuComment
     * 
     * @param spuCommentIds 删除SpuCommentId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> spuCommentIds);

    /**
     * 批量查询SpuComment
     * 
     * @param spuCommentIds 查询SpuComment Id 列表
     * @return SpuComment列表
     */
    public List<SpuComment> batchSelect(List<Long> spuCommentIds);
}
