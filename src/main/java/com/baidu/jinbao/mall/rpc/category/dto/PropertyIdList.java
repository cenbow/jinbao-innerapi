package com.baidu.jinbao.mall.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PropertyIdList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = true)
    private List<PropertyId> propertyIdList;

    public List<PropertyId> getPropertyIdList() {
        return propertyIdList;
    }

    public void setPropertyIdList(List<PropertyId> propertyIdList) {
        this.propertyIdList = propertyIdList;
    }
}
