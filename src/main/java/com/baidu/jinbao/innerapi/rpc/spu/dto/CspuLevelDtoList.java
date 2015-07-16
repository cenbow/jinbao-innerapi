package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CspuLevelDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CspuLevelDto> dtoList;

    public List<CspuLevelDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CspuLevelDto> dtoList) {
        this.dtoList = dtoList;
    }

}
