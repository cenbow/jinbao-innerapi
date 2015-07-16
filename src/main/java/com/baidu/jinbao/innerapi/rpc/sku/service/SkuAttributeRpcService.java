package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuAttributeRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuAttributeSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuAttributeRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuAttributeSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuAttribute 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuAttributeRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuAttributeDtoList data);

    /**
     * SkuAttribute 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuAttributeRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuAttributeDtoList data);

    /**
     * SkuAttribute 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuAttributeRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}