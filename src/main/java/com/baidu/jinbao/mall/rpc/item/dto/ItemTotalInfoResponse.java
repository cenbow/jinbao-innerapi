package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemTotalInfoResponse {
    @Protobuf(order = 1)
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf(order = 2)
    private String message; // 返回信息

    @Protobuf(fieldType = FieldType.OBJECT, order = 3, required = false)
    private ItemTotalInfoDto dto; // 返回数据List

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

    public ItemTotalInfoDto getDto() {
        return dto;
    }

    public void setDto(ItemTotalInfoDto dto) {
        this.dto = dto;
    }
}
