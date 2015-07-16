package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCdtDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCdtSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuCdtRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuCdtSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCdtRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuCdtSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuCdt 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCdtRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuCdtDtoList data);

    /**
     * SkuCdt 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCdtRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuCdtDtoList data);

    /**
     * SkuCdt 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuCdtRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);
}