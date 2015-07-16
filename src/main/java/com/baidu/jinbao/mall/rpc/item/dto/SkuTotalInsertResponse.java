package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuTotalInsertResponse {
    @Protobuf(order = 1)
    private Integer status; // 标志位 0:成功，1：失败

    @Protobuf(order = 2)
    private String message; // 描述信息

    @Protobuf(order = 3)
    private Integer successNum; // 更改成功记录数

    @Protobuf(order = 4)
    private Integer failedNum; // 更改失败记录数

    @Protobuf(order = 5)
    private Long skuId; // 插入skuInfo的skuId

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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}

