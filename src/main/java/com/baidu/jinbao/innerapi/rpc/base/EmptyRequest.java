package com.baidu.jinbao.innerapi.rpc.base;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * EmptyRequest
 * 
 * @author cgd
 * @date 2015年7月3日 上午11:34:22
 */
public class EmptyRequest {
    @Protobuf
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
