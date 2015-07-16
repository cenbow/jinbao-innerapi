package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * CategoryMap Dto
 * 
 * @author hbf
 * @date 2015年6月12日
 */
public class CategoryMapDto {

    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cmCid;

    @Protobuf(order = 3)
    private Integer baseCid;

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

    public Integer getCmCid() {
        return cmCid;
    }

    public void setCmCid(Integer cmCid) {
        this.cmCid = cmCid;
    }

    public Integer getBaseCid() {
        return baseCid;
    }

    public void setBaseCid(Integer baseCid) {
        this.baseCid = baseCid;
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
