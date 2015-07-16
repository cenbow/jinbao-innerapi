package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PropertyValueMapDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cmVid;

    @Protobuf(order = 3)
    private Integer baseVid;

    @Protobuf(order = 4)
    private Integer active;

    @Protobuf(order = 5)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCmVid() {
        return cmVid;
    }

    public void setCmVid(Integer cmVid) {
        this.cmVid = cmVid;
    }

    public Integer getBaseVid() {
        return baseVid;
    }

    public void setBaseVid(Integer baseVid) {
        this.baseVid = baseVid;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }


}
