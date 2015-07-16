package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CspuCommentRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CspuCommentSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuCommentRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CspuCommentSearchResponse getRecords(SpuCondition condition);

    /**
     * CspuComment 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuCommentRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CspuCommentDtoList data);

    /**
     * CspuComment 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuCommentRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CspuCommentDtoList data);

    /**
     * CspuComment 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuCommentRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}