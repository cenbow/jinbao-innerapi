package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryPropertyDtoList {
    
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CategoryPropertyDto> dtoList;

    public List<CategoryPropertyDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CategoryPropertyDto> dtoList) {
        this.dtoList = dtoList;
    }

}
