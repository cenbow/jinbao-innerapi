package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CmPropertyDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cmCid;

    @Protobuf(order = 3)
    private Integer dicPid;

    @Protobuf(order = 4)
    private Integer basePid;

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

    public Integer getCmCid() {
        return cmCid;
    }

    public void setCmCid(Integer cmCid) {
        this.cmCid = cmCid;
    }

    public Integer getDicPid() {
        return dicPid;
    }

    public void setDicPid(Integer dicPid) {
        this.dicPid = dicPid;
    }

    public Integer getBasePid() {
        return basePid;
    }

    public void setBasePid(Integer basePid) {
        this.basePid = basePid;
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
