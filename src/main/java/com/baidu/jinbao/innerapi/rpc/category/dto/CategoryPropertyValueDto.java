package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryPropertyValueDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cId;

    @Protobuf(order = 3)
    private Integer isleaf;

    @Protobuf(order = 4)
    private Long pid;

    @Protobuf(order = 5)
    private Long vid;

    @Protobuf(order = 6)
    private String alias;

    @Protobuf(order = 7)
    private String imageUrl;

    @Protobuf(order = 8)
    private Integer active;

    @Protobuf(order = 9)
    private String addtime;

    @Protobuf(order = 10)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
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
