package com.baidu.jinbao.mall.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * PropertyId
 * 
 * @author cgd
 * @date 2015年7月2日 下午5:10:03
 */
public class PropertyId {

    @Protobuf
    private Long cid; 

    @Protobuf
    private Long pid; 

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

}
