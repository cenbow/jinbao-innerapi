package com.baidu.jinbao.innerapi.rpc.spu.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BcsCspuImageRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuInfoSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsCspuImageRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public BcsCspuImageSearchResponse getRecords(SpuCondition condition);

    /**
     * BcsCspuImage 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsCspuImageRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(BcsCspuImageDtoList data);

    /**
     * BcsCspuImage 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsCspuImageRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(BcsCspuImageDtoList data);

    /**
     * BcsCspuImage 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "bcsCspuImageRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SpuCondition condition);

}