package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmPropertySearchResponse {

    @Protobuf(order = 1)
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf(order = 2)
    private String message; // 返回信息

    @Protobuf(fieldType = FieldType.OBJECT, order = 3, required = false)
    private List<CmPropertyDto> dtoList; // 返回数据List

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CmPropertyDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CmPropertyDto> dtoList) {
        this.dtoList = dtoList;
    }
}
