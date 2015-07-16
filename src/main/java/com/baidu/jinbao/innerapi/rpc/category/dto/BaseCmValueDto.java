package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * CategoryMap Dto
 * 
 * @author hbf
 * @date 2015年6月15日
 */
public class BaseCmValueDto {

    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
