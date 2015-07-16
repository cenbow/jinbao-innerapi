package com.baidu.jinbao.innerapi.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CategoryPropertyDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cId;

    @Protobuf(order = 3)
    private Integer isleaf;

    @Protobuf(order = 4)
    private String name;

    @Protobuf(order = 5)
    private Long pid;

    @Protobuf(order = 6)
    private Integer isRequired;

    @Protobuf(order = 7)
    private Integer isSelfdefine;

    @Protobuf(order = 8)
    private Integer type;

    @Protobuf(order = 9)
    private String valueRange;

    @Protobuf(order = 10)
    private Integer propType;

    @Protobuf(order = 11)
    private Integer sequence;

    @Protobuf(order = 12)
    private Integer active;

    @Protobuf(order = 13)
    private String addtime;

    @Protobuf(order = 14)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getIsSelfdefine() {
        return isSelfdefine;
    }

    public void setIsSelfdefine(Integer isSelfdefine) {
        this.isSelfdefine = isSelfdefine;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public Integer getPropType() {
        return propType;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
