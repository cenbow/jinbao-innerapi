package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SpuCommentRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SpuCommentSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuCommentRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SpuCommentSearchResponse getRecords(SpuCondition condition);

    /**
     * SpuComment 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuCommentRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SpuCommentDtoList data);

    /**
     * SpuComment 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuCommentRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SpuCommentDtoList data);

    /**
     * SpuComment 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuCommentRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}