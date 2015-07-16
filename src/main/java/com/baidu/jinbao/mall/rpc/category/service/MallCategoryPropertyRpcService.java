package com.baidu.jinbao.mall.rpc.category.service;

import com.baidu.jinbao.mall.rpc.category.dto.PropertyIdList;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyInfoResponse;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyValueInfoResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface MallCategoryPropertyRpcService {
    /**
     * 查询某一类目的属性项信息
     * 
     * @return 返回Category的全表信息
     * */
    @ProtobufRPC(serviceName = "mallCategoryPropertyRpcService",
            methodName = "getPropertyInfo",
            onceTalkTimeout = 30000)
    public PropertyInfoResponse getPropertyInfo(PropertyIdList propertyIdList);

    /**
     * 查询指定类目下的某一个属性项对应的属性值信息
     * 
     * @param categoryId category表主键Id
     * @return category表对应id记录
     * */
    @ProtobufRPC(serviceName = "mallCategoryPropertyRpcService",
            methodName = "getPropertyValueInfo",
            onceTalkTimeout = 30000)
    public PropertyValueInfoResponse getPropertyValueInfo(PropertyIdList propertyIdList);

}
