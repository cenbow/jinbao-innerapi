package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.PropertyValueMapDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.PropertyValueMapSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface PropertyValueMapRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return PropertyValueMapSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "propertyValueMapRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public PropertyValueMapSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * PropertyValueMap 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "propertyValueMapRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(PropertyValueMapDtoList data);
    
    /**
     * PropertyValueMap 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "propertyValueMapRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(PropertyValueMapDtoList data);
    
    /**
     * PropertyValueMap 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "propertyValueMapRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
