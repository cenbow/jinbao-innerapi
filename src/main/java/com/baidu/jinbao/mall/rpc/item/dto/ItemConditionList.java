package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemConditionList {

    @Protobuf(fieldType = FieldType.INT64)
    private List<Long> itemIdList;

    @Protobuf(fieldType = FieldType.STRING)
    private List<String> fieldList;

    public List<Long> getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List<Long> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

}
