package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BcsCspuImageDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BcsCspuImageDto> dtoList;

    public List<BcsCspuImageDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BcsCspuImageDto> dtoList) {
        this.dtoList = dtoList;
    }

}
