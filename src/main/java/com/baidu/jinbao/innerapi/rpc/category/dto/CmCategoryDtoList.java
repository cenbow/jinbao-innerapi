package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmCategoryDtoList {
    
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<CmCategoryDto> dtoList;

    public List<CmCategoryDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CmCategoryDto> dtoList) {
        this.dtoList = dtoList;
    }

}
