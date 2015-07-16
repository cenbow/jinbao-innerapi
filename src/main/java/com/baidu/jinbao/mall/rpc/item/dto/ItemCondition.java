package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemCondition {

    @Protobuf
    private Long itemId;

    @Protobuf
    private Integer mechantStatus;

    @Protobuf(fieldType = FieldType.STRING)
    private List<String> fieldList;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getMechantStatus() {
        return mechantStatus;
    }

    public void setMechantStatus(Integer mechantStatus) {
        this.mechantStatus = mechantStatus;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

}
