package com.baidu.jinbao.innerapi.rpc.category.service;


import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryMapDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryMapSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CategoryMapRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CategoryMapSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryMapRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CategoryMapSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CategoryMap 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryMapRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CategoryMapDtoList data);
    
    /**
     * CategoryMap 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryMapRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CategoryMapDtoList data);
    
    /**
     * CategoryMap 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "categoryMapRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
