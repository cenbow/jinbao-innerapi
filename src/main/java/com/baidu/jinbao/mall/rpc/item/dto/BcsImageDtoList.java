package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BcsImageDtoList {

    @Protobuf(fieldType = FieldType.OBJECT)
    private List<BcsImageDto> dtoList;

    public List<BcsImageDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BcsImageDto> dtoList) {
        this.dtoList = dtoList;
    }

}
