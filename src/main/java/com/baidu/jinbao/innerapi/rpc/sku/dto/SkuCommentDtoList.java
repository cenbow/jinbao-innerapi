package com.baidu.jinbao.innerapi.rpc.sku.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCommentDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SkuCommentDto> dtoList;

    public List<SkuCommentDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SkuCommentDto> dtoList) {
        this.dtoList = dtoList;
    }

}
