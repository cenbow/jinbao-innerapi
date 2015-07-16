package com.baidu.jinbao.mall.rpc.item.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDtoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.SkuPpsConditionList;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MallSkuPpsRpcService {
    /**
     * SkuPps Sku_pps 的批量更新
     * 
     * @param data MallSkuPps数据列表
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallSkuPpsRpcService", methodName = "updateSkuPpsInfoList", onceTalkTimeout = 30000)
    public ModifyResponse updateSkuPpsInfoList(MallSkuPpsDtoList data);

    /**
     * 根据 skuid,regionid,device组合的list返回查询结果
     * 
     * @param SkuPpsConditionList
     * @return MallSkuPpsDtoListResponse
     */
    @ProtobufRPC(serviceName = "mallSkuPpsRpcService", methodName = "getSkuPpsInfoList", onceTalkTimeout = 30000)
    public MallSkuPpsDtoListResponse getSkuPpsInfoList(SkuPpsConditionList data);

}
