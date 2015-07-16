package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BaseValRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return BaseValSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseValRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BaseValSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * BaseVal 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseValRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BaseValDtoList data);
    
    /**
     * BaseVal 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseValRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BaseValDtoList data);
    
    /**
     * BaseVal 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseValRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);

}
