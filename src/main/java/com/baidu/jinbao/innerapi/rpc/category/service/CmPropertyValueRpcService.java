package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyValueSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CmPropertyValueRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CmPropertyValueSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyValueRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CmPropertyValueSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CmPropertyValue 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyValueRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CmPropertyValueDtoList data);
    
    /**
     * CmPropertyValue 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyValueRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CmPropertyValueDtoList data);
    
    /**
     * CmPropertyValue 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmPropertyValueRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
