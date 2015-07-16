package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuPpsDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuPpsSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuPpsRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuPpsSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuPpsRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuPpsSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuPps 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuPpsRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuPpsDtoList data);

    /**
     * SkuPps 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuPpsRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuPpsDtoList data);

    /**
     * SkuPps 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuPpsRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}