package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuAttributeDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuAttributeDto> dtoList;

    public List<SkuAttributeDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuAttributeDto> dtoList) {
        this.dtoList = dtoList;
    }

}
