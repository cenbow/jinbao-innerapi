package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BcsSkuImageDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BcsSkuImageDto> dtoList;

    public List<BcsSkuImageDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BcsSkuImageDto> dtoList) {
        this.dtoList = dtoList;
    }

}
