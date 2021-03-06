package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuDescriptionDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuDescriptionSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuDescriptionRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuDescriptionSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuDescriptionRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuDescriptionSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuDescription 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuDescriptionRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuDescriptionDtoList data);

    /**
     * SkuDescription 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuDescriptionRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuDescriptionDtoList data);

    /**
     * SkuDescription 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuDescriptionRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}