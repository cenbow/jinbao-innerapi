package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RegionMapDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<RegionMapDto> dtoList;

    public List<RegionMapDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<RegionMapDto> dtoList) {
        this.dtoList = dtoList;
    }

}
