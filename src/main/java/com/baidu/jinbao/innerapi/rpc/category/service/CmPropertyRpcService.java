package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertySearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CmPropertyRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CmPropertySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CmPropertySearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CmProperty 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CmPropertyDtoList data);
    
    /**
     * CmProperty 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CmPropertyDtoList data);
    
    /**
     * CmProperty 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
