package com.baidu.jinbao.innerapi.spu.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;

public interface CspuCommentService extends GenericMapperService<CspuComment, Long> {

    /**
     * 批量插入CspuComment
     * 
     * @param cspuCommentis 插入cspuComment列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CspuComment> cspuCommentList);

    /**
     * 批量更新CspuComment
     * 
     * @param cspuCommentis 更新cspuComment列表
     * @return 更新条数
     */
    public Integer updateRecords(List<CspuComment> cspuCommentList);

    /**
     * 批量删除CspuComment
     * 
     * @param cspuCommentIds 删除CspuCommentId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> cspuCommentIdList);

    /**
     * 批量查询CspuComment
     * 
     * @param cspuCommentIds 查询CspuComment Id 列表
     * @return CspuComment列表
     */
    public List<CspuComment> getRecords(List<Long> cspuCommentIdList);
}
