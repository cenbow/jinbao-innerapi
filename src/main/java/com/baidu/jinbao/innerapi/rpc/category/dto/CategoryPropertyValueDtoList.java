package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryPropertyValueDtoList {
    
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CategoryPropertyValueDto> dtoList;

    public List<CategoryPropertyValueDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CategoryPropertyValueDto> dtoList) {
        this.dtoList = dtoList;
    }

}
