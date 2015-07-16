package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PropertyValueMapDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<PropertyValueMapDto> dtoList;

    public List<PropertyValueMapDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<PropertyValueMapDto> dtoList) {
        this.dtoList = dtoList;
    }
}
