package com.baidu.jinbao.innerapi.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmCategoryDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmCategorySearchResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface CmCategoryRpcService {
    /**
     * 获取指定查询条件下的所有记录
     * 
     * @param condition 查询条件
     * @return CmCategorySearchResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmCategoryRpcService", methodName = "getRecords", onceTalkTimeout = 30000)
    public CmCategorySearchResponse getRecords(CategoryCondition condition);
    
    /**
     * CmCategory 数据批量插入
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmCategoryRpcService", methodName = "insertRecords", onceTalkTimeout = 30000)
    public ModifyResponse insertRecords(CmCategoryDtoList data);
    
    /**
     * CmCategory 数据批量更新
     * 
     * @param dtoList 数据
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmCategoryRpcService", methodName = "updateRecords", onceTalkTimeout = 30000)
    public ModifyResponse updateRecords(CmCategoryDtoList data);
    
    /**
     * CmCategory 数据批量删除
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "cmCategoryRpcService", methodName = "deleteRecords", onceTalkTimeout = 30000)
    public ModifyResponse deleteRecords(CategoryCondition condition);
}
