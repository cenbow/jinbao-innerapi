package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantInfoSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MerchantInfoRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return MerchantInfoSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantInfoRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public MerchantInfoSearchResponse getRecords(JinbaoCommonCondition condition);
    
    /**
     * MerchantInfo 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantInfoRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(MerchantInfoDtoList data);
    
    /**
     * MerchantInfo 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantInfoRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(MerchantInfoDtoList data);
    
    /**
     * MerchantInfo 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "merchantInfoRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(JinbaoCommonCondition condition);

}
