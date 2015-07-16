package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmPropertyValueDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cid;

    @Protobuf(order = 3)
    private Integer cmPid;

    @Protobuf(order = 4)
    private Integer dicVid;

    @Protobuf(order = 5)
    private Integer pos;

    @Protobuf(order = 6)
    private Integer active;

    @Protobuf(order = 7)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCmPid() {
        return cmPid;
    }

    public void setCmPid(Integer cmPid) {
        this.cmPid = cmPid;
    }

    public Integer getDicVid() {
        return dicVid;
    }

    public void setDicVid(Integer dicVid) {
        this.dicVid = dicVid;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
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
