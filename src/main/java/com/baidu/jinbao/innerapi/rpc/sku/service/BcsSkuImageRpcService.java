package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BcsSkuImageRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return BcsSkuImageSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsSkuImageRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BcsSkuImageSearchResponse getRecords(SkuCondition condition);

    /**
     * BcsSkuImage 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsSkuImageRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BcsSkuImageDtoList data);

    /**
     * BcsSkuImage 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsSkuImageRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BcsSkuImageDtoList data);

    /**
     * BcsSkuImage 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsSkuImageRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);

}