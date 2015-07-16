package com.baidu.jinbao.innerapi.spu.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;

public interface CspuCommentDao extends GenericMapperDao<CspuComment, Long> {
    /**
     * 批量插入CspuComment
     * 
     * @param cspuCommentis 插入cspuComment列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CspuComment> cspuCommentis);

    /**
     * 批量更新CspuComment
     * 
     * @param cspuCommentis 更新cspuComment列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<CspuComment> cspuCommentis);

    /**
     * 批量删除CspuComment
     * 
     * @param cspuCommentIds 删除CspuCommentId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> cspuCommentIds);

    /**
     * 批量查询CspuComment
     * 
     * @param cspuCommentIds 查询CspuComment Id 列表
     * @return CspuComment列表
     */
    public List<CspuComment> batchSelect(List<Long> cspuCommentIds);
}
