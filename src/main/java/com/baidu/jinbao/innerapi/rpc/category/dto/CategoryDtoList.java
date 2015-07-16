package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CategoryDto> dtoList;

    public List<CategoryDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CategoryDto> dtoList) {
        this.dtoList = dtoList;
    }


}
