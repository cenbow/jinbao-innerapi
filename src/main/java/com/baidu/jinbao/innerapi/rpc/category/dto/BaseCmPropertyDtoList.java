package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * BaseCmProperty Dto List
 * 
 * @author cgd
 * @date 2015年6月11日 下午4:35:36
 */
public class BaseCmPropertyDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<BaseCmPropertyDto> dtoList;

    public List<BaseCmPropertyDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<BaseCmPropertyDto> dtoList) {
        this.dtoList = dtoList;
    }

}
