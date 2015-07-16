package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCommentDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCommentSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuCommentRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuCommentSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCommentRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuCommentSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuComment 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCommentRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuCommentDtoList data);

    /**
     * SkuComment 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCommentRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuCommentDtoList data);

    /**
     * SkuComment 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCommentRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}