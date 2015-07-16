package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CspuInfoRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CspuInfoSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuInfoRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CspuInfoSearchResponse getRecords(SpuCondition condition);

    /**
     * CspuInfo 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuInfoRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CspuInfoDtoList data);

    /**
     * CspuInfo 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuInfoRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CspuInfoDtoList data);

    /**
     * CspuInfo 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuInfoRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}