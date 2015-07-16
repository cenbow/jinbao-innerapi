package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCspuDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuCspuDto> dtoList;

    public List<SkuCspuDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuCspuDto> dtoList) {
        this.dtoList = dtoList;
    }

}
