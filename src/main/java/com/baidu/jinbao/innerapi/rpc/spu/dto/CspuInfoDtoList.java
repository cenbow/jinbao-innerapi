package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CspuInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CspuInfoDto> dtoList;

    public List<CspuInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CspuInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
