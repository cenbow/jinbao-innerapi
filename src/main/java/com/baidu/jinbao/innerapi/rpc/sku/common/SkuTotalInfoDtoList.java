package com.baidu.jinbao.innerapi.rpc.sku.common;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuTotalInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuTotalInfoDto> dtoList;

    public List<SkuTotalInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuTotalInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
