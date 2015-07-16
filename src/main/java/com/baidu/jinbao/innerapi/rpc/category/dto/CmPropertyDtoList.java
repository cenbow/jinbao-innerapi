package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmPropertyDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CmPropertyDto> dtoList;

    public List<CmPropertyDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CmPropertyDto> dtoList) {
        this.dtoList = dtoList;
    }
}
