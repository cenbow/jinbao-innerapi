package com.baidu.jinbao.innerapi.rpc.sku.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuTotalInfoDto;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuTotalInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface SkuInfoRpcService {

    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return SkuInfoSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public SkuInfoSearchResponse getRecords(SkuCondition condition);

    /**
     * SkuInfo 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(SkuInfoDtoList data);

    /**
     * SkuInfo 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(SkuInfoDtoList data);

    /**
     * SkuInfo 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(SkuCondition condition);

    /**
     * SkuInfo 相关全量数据插入
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "insertSkuTotalInfo", onceTalkTimeout = 30000)
    public ModifyResponse insertSkuTotalInfo(SkuTotalInfoDto data);

    /**
     * SkuInfo 相关全量数据插入
     * 
     * @param data 全量列表数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "insertSkuTotalInfoList", onceTalkTimeout = 30000)
    public ModifyResponse insertSkuTotalInfoList(SkuTotalInfoDtoList data);
    
    /**
     * SkuInfo 相关全量数据更新插入
     * 
     * @param data 全量列表数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "skuInfoRpcService", methodName = "updateInsert", onceTalkTimeout = 30000)
    public ModifyResponse updateInsert(SkuTotalInfoDto data);
}