package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantRouterDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantRouterSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MerchantRouterRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return MerchantRouterSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantRouterRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public MerchantRouterSearchResponse getRecords(JinbaoCommonCondition condition);
    
    /**
     * MerchantRouter 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantRouterRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(MerchantRouterDtoList data);
    
    /**
     * MerchantRouter 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantRouterRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(MerchantRouterDtoList data);
    
    /**
     * MerchantRouter 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantRouterRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(JinbaoCommonCondition condition);

}
