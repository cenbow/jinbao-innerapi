package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryMapDtoList {
    
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CategoryMapDto> dtoList;

    public List<CategoryMapDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CategoryMapDto> dtoList) {
        this.dtoList = dtoList;
    }

}
