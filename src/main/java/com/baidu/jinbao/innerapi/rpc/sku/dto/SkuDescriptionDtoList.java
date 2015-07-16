package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuDescriptionDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuDescriptionDto> dtoList;

    public List<SkuDescriptionDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuDescriptionDto> dtoList) {
        this.dtoList = dtoList;
    }

}
