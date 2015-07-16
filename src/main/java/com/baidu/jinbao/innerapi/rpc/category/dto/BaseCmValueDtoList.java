package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BaseCmValueDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BaseCmValueDto> dtoList;

    public List<BaseCmValueDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BaseCmValueDto> dtoList) {
        this.dtoList = dtoList;
    }


}
