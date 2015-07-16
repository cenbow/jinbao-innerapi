package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmPropertySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

/**
 * BaseCmPropertyService Rpc Services
 * 
 * @author cgd
 * @date 2015年6月10日 下午8:48:08
 */
public interface BaseCmPropertyRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return BaseCmPropertySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmPropertyRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BaseCmPropertySearchResponse getRecords(CategoryCondition condition);

    /**
     * BaseCmProperty 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmPropertyRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BaseCmPropertyDtoList data);

    /**
     * BaseCmProperty 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmPropertyRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BaseCmPropertyDtoList data);

    /**
     * BaseCmProperty 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "baseCmPropertyRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);

}