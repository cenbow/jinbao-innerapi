package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SpuInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<SpuInfoDto> dtoList;

    public List<SpuInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<SpuInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
