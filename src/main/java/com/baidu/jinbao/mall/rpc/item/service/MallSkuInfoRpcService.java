package com.baidu.jinbao.mall.rpc.item.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemConditionList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuInfoDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.SkuTotalInsertResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MallSkuInfoRpcService {
    /**
     * Sku 相关全量数据更新
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallSkuInfoRpcService", methodName = "updateSkuTotalInfo", onceTalkTimeout = 30000)
    public ModifyResponse updateSkuTotalInfo(MallSkuTotalInfoDto data);
    
    /**
     * Sku 相关全量数据插入
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallSkuInfoRpcService", methodName = "insertSkuTotalInfo", onceTalkTimeout = 30000)
    public SkuTotalInsertResponse insertSkuTotalInfo(MallSkuTotalInfoDto data);
    
    /**
     * 根据ietmid list查询sku相关表信息
     * @param data
     * @return
     */
    @ProtobufRPC(serviceName = "mallSkuInfoRpcService", methodName = "getSkuTotalInfoList", onceTalkTimeout = 30000)
    public MallSkuTotalInfoListResponse getSkuTotalInfoList(ItemConditionList data);
    
    /**
     * Sku 相关全量数据更新
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallSkuInfoRpcService", methodName = "updateSkuInfoRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateSkuInfoRecords(MallSkuInfoDtoList data);
}
