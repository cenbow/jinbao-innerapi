package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCspuDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCspuSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuCspuRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuCspuSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCspuRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuCspuSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuCspu 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCspuRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuCspuDtoList data);

    /**
     * SkuCspu 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCspuRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuCspuDtoList data);

    /**
     * SkuCspu 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCspuRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}