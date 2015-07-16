package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertySearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CategoryPropertyRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CategoryPropertySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CategoryPropertySearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CategoryProperty 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CategoryPropertyDtoList data);
    
    /**
     * CategoryProperty 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CategoryPropertyDtoList data);
    
    /**
     * CategoryProperty 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
