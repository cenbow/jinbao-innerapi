package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.RegionMapDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.RegionMapSearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface RegionMapRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return RegionMapSearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "regionMapRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public RegionMapSearchResponse getRecords(JinbaoCommonCondition condition);
    
    /**
     * RegionMap 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "regionMapRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(RegionMapDtoList data);
    
    /**
     * RegionMap 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "regionMapRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(RegionMapDtoList data);
    
    /**
     * RegionMap 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "regionMapRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(JinbaoCommonCondition condition);

}
