package com.baidu.jinbao.mall.rpc.category.service;

import com.baidu.jinbao.innerapi.rpc.base.EmptyRequest;
import com.baidu.jinbao.mall.rpc.category.dto.CategoryDtoListResponse;
import com.baidu.jinbao.mall.rpc.category.dto.CategoryId;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MallCategoryRpcService {
    /**
     * 查询所有类目信息
     * 
     * @return 返回Category的全表信息
     * */
    @ProtobufRPC(serviceName = "mallCategoryRpcService", methodName = "getAllCategoryInfo", onceTalkTimeout = 30000)
    public CategoryDtoListResponse getAllCategoryInfo(EmptyRequest emptyRequest);

    /**
     * 某一类目的所有下一级子类目信息
     * 
     * @param categoryId category表主键Id
     * @return category表对应id记录
     * */
    @ProtobufRPC(serviceName = "mallCategoryRpcService", methodName = "getCategoryInfo", onceTalkTimeout = 30000)
    public CategoryDtoListResponse getCategoryInfo(CategoryId categoryId);

}
