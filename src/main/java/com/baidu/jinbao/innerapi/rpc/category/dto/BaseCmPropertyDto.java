package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * BaseCmProperty Dto
 * 
 * @author cgd
 * @date 2015年6月10日 下午8:28:06
 */
public class BaseCmPropertyDto {

    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private String name;

    // -------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
