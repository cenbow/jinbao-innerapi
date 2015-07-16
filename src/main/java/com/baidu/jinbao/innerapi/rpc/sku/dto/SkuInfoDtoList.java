package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuInfoDto> dtoList;

    public List<SkuInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
