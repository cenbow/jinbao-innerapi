package com.baidu.jinbao.innerapi.rpc.sku.common;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCondition {
    @Protobuf(fieldType = FieldType.INT64, order = 1, required = false)
    private List<Long> idList; // 主键ID List
    
    @Protobuf(fieldType = FieldType.INT64, order = 2, required = false)
    private List<Long> skuInnerIdList; // sku表主键ID List
    
    @Protobuf(fieldType = FieldType.STRING, order = 3, required = true)
    private List<String> skuIdList; // sku id List(商家ID+outerId)
    
    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getSkuInnerIdList() {
        return skuInnerIdList;
    }

    public void setSkuInnerIdList(List<Long> skuInnerIdList) {
        this.skuInnerIdList = skuInnerIdList;
    }

    public List<String> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<String> skuIdList) {
        this.skuIdList = skuIdList;
    }

}
