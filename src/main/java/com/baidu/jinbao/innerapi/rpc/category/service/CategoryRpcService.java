package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategorySearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CategoryRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CategorySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CategorySearchResponse getRecords(CategoryCondition condition);
    
    /**
     * Category 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CategoryDtoList data);
    
    /**
     * Category 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CategoryDtoList data);
    
    /**
     * Category 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
