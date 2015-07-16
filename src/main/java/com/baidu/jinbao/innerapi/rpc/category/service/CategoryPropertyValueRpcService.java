package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CategoryPropertyValueRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CategoryPropertyValueSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyValueRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CategoryPropertyValueSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CategoryPropertyValue 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyValueRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CategoryPropertyValueDtoList data);
    
    /**
     * CategoryPropertyValue 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyValueRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CategoryPropertyValueDtoList data);
    
    /**
     * CategoryPropertyValue 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryPropertyValueRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
