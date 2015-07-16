package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CspuLevelRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CspuLevelSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuLevelRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CspuLevelSearchResponse getRecords(SpuCondition condition);

    /**
     * CspuLevel 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuLevelRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CspuLevelDtoList data);

    /**
     * CspuLevel 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuLevelRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CspuLevelDtoList data);

    /**
     * CspuLevel 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cspuLevelRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}