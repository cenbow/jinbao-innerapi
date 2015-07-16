package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SpuInfoRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SpuInfoSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuInfoRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SpuInfoSearchResponse getRecords(SpuCondition condition);

    /**
     * SpuInfo 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuInfoRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SpuInfoDtoList data);

    /**
     * SpuInfo 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuInfoRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SpuInfoDtoList data);

    /**
     * SpuInfo 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "spuInfoRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}