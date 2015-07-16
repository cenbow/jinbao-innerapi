package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SpuCommentDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SpuCommentDto> dtoList;

    public List<SpuCommentDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SpuCommentDto> dtoList) {
        this.dtoList = dtoList;
    }

}
