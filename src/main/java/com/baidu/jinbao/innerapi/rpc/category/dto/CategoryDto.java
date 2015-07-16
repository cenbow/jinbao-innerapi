package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryDto {

    @Protobuf(order = 1)
    private Long categoryid;

    @Protobuf(order = 2)
    private Long parentid;

    @Protobuf(order = 3)
    private String name;

    @Protobuf(order = 4)
    private Integer isleaf;

    @Protobuf(order = 5)
    private Integer pos;

    @Protobuf(order = 6)
    private String addtime;

    @Protobuf(order = 7)
    private String updatetime;

    @Protobuf(order = 8)
    private Integer deleted;

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Integer isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
