package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MerchantInfoDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Long ucid;

    @Protobuf(required = true, order = 3)
    private String name;

    @Protobuf(order = 4)
    private String addtime;

    @Protobuf(order = 5)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUcid() {
        return ucid;
    }

    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
