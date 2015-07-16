package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmPropertyValueDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CmPropertyValueDto> dtoList;

    public List<CmPropertyValueDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CmPropertyValueDto> dtoList) {
        this.dtoList = dtoList;
    }


}
