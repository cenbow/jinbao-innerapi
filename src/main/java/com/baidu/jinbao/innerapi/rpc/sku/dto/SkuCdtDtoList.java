package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCdtDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuCdtDto> dtoList;

    public List<SkuCdtDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuCdtDto> dtoList) {
        this.dtoList = dtoList;
    }

}
