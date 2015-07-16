package com.baidu.jinbao.innerapi.rpc.category.service;


import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmValueSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BaseCmValueRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return BaseCmValueSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmValueRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BaseCmValueSearchResponse getRecords(CategoryCondition condition);
    
    /**
     * BaseCmValue 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmValueRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BaseCmValueDtoList data);
    
    /**
     * BaseCmValue 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "BaseCmValueRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BaseCmValueDtoList data);
    
    /**
     * BaseCmValue 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmValueRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
