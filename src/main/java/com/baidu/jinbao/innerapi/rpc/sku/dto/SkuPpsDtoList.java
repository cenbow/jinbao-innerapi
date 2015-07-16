package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuPpsDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuPpsDto> dtoList;

    public List<SkuPpsDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuPpsDto> dtoList) {
        this.dtoList = dtoList;
    }

}
