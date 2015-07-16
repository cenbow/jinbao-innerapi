package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BaseValDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BaseValDto> dtoList;

    public List<BaseValDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BaseValDto> dtoList) {
        this.dtoList = dtoList;
    }

}
