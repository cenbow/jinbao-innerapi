package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BasePropertyDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private String propertyName;

    @Protobuf(order = 3)
    private String enName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

}
