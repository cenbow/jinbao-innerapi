package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BasePropertyDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BasePropertyDto> dtoList;

    public List<BasePropertyDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BasePropertyDto> dtoList) {
        this.dtoList = dtoList;
    }

}
