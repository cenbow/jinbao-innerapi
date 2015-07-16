package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertySearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BasePropertyRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return BasePropertySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "basePropertyRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BasePropertySearchResponse getRecords(CategoryCondition condition);
    
    /**
     * BaseProperty 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "basePropertyRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BasePropertyDtoList data);
    
    /**
     * BaseProperty 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "basePropertyRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BasePropertyDtoList data);
    
    /**
     * BaseProperty 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "basePropertyRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
