package com.baidu.jinbao.mall.rpc.item.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageCondition;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDto;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDtoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageInsertResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface BcsImageRpcService {
    /**
     * BcsImage数据更新
     * 
     * @param data BcsImage数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "bcsImageRpcService", methodName = "updateBcsImageInfoList", onceTalkTimeout = 30000)
    public ModifyResponse updateBcsImageInfoList(BcsImageDtoList data);

    /**
     * BcsImage数据插入
     * 
     * @param data BcsImage数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "bcsImageRpcService", methodName = "insertBcsImageInfo", onceTalkTimeout = 30000)
    public BcsImageInsertResponse insertBcsImageInfo(BcsImageDto data);

    /**
     * BcsImage数据删除
     * 
     * @param condition BcsImage查询条件
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "bcsImageRpcService", methodName = "deleteBcsImageInfoList", onceTalkTimeout = 30000)
    public ModifyResponse deleteBcsImageInfoList(BcsImageCondition condition);

    /**
     * BcsImage数据批量查询
     * 
     * @param condition BcsImage查询条件
     * @return BcsImageDtoList
     */
    @ProtobufRPC(serviceName = "bcsImageRpcService", methodName = "getBcsImageRecords", onceTalkTimeout = 30000)
    public BcsImageDtoListResponse getBcsImageRecords(BcsImageCondition condtion);
}
