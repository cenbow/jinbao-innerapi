package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemTotalInsertResponse {
    @Protobuf(order = 1)
    private Integer status; // 标志位 0:成功，1：失败

    @Protobuf(order = 2)
    private String message; // 描述信息

    @Protobuf(order = 3)
    private Integer successNum; // 更改成功记录数

    @Protobuf(order = 4)
    private Integer failedNum; // 更改失败记录数

    @Protobuf(order = 5)
    private Long itemId; // 插入info的itemId

    @Protobuf(fieldType = FieldType.INT64, order = 6)
    private List<Long> skuIdList; // 插入info的skuIdList

    @Protobuf(fieldType = FieldType.INT64, order = 7)
    private List<Long> imageIdList; // 插入info的imageIdList

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

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(Integer failedNum) {
        this.failedNum = failedNum;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<Long> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<Long> skuIdList) {
        this.skuIdList = skuIdList;
    }

    public List<Long> getImageIdList() {
        return imageIdList;
    }

    public void setImageIdList(List<Long> imageIdList) {
        this.imageIdList = imageIdList;
    }
}

