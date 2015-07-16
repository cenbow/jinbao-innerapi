package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuLevelDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuLevelDto> dtoList;

    public List<SkuLevelDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuLevelDto> dtoList) {
        this.dtoList = dtoList;
    }

}
