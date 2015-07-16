package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CspuCommentDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CspuCommentDto> dtoList;

    public List<CspuCommentDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CspuCommentDto> dtoList) {
        this.dtoList = dtoList;
    }

}
