/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CategoryPropertyBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 叶子类目id
     */
    private Integer cId;

    /**
     * 1:叶节点 0:非叶节点
     */
    private Byte isleaf;

    /**
     * 属性项的名称
     */
    private String name;

    /**
     * 属性项的字典id
     */
    private Long pid;

    /**
     * 1:必填 0:非必填
     */
    private Byte isRequired;

    /**
     * 1:自定义 0：非自定义
     */
    private Byte isSelfdefine;

    /**
     * (0:STRING,1:KEY,2:ITEM,3:DOUBLE,4:LONG,5:ENUM,6:ARRAY,7:EAN...)筛选项的ENUM是单选，ARRAY是多选
     */
    private Byte type;

    /**
     * 属性值校验规则(用在通用属性上，Mall商家上传时候需要校验)
     */
    private String valueRange;

    /**
     * 属性类型（0:item通用属性，1:KEY关键属性，2:NON_KEY非关键属性，3:SALE销售属性）
     */
    private Byte propType;

    /**
     * 位置信息，发布商品的时候决定哪个先显示
     */
    private Integer sequence;

    /**
     * 是否有效
     */
    private Byte active;

    /**
     * 增加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 自增id
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 自增id
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 叶子类目id
     * 
     * @param cId the value for c_id
     */
    public void setCId(Integer cId) {
        this.cId = cId;
    }

    /**
     * 叶子类目id
     * 
     * @return cId the value for c_id
     */
    public Integer getCId() {
        return this.cId;
    }

    /**
     * 1:叶节点 0:非叶节点
     * 
     * @param isleaf the value for isleaf
     */
    public void setIsleaf(Byte isleaf) {
        this.isleaf = isleaf;
    }

    /**
     * 1:叶节点 0:非叶节点
     * 
     * @return isleaf the value for isleaf
     */
    public Byte getIsleaf() {
        return this.isleaf;
    }

    /**
     * 属性项的名称
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 属性项的名称
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 属性项的字典id
     * 
     * @param pid the value for pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 属性项的字典id
     * 
     * @return pid the value for pid
     */
    public Long getPid() {
        return this.pid;
    }

    /**
     * 1:必填 0:非必填
     * 
     * @param isRequired the value for is_required
     */
    public void setIsRequired(Byte isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * 1:必填 0:非必填
     * 
     * @return isRequired the value for is_required
     */
    public Byte getIsRequired() {
        return this.isRequired;
    }

    /**
     * 1:自定义 0：非自定义
     * 
     * @param isSelfdefine the value for is_selfdefine
     */
    public void setIsSelfdefine(Byte isSelfdefine) {
        this.isSelfdefine = isSelfdefine;
    }

    /**
     * 1:自定义 0：非自定义
     * 
     * @return isSelfdefine the value for is_selfdefine
     */
    public Byte getIsSelfdefine() {
        return this.isSelfdefine;
    }

    /**
     * (0:STRING,1:KEY,2:ITEM,3:DOUBLE,4:LONG,5:ENUM,6:ARRAY,7:EAN...)筛选项的ENUM是单选，ARRAY是多选
     * 
     * @param type the value for type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * (0:STRING,1:KEY,2:ITEM,3:DOUBLE,4:LONG,5:ENUM,6:ARRAY,7:EAN...)筛选项的ENUM是单选，ARRAY是多选
     * 
     * @return type the value for type
     */
    public Byte getType() {
        return this.type;
    }

    /**
     * 属性值校验规则(用在通用属性上，Mall商家上传时候需要校验)
     * 
     * @param valueRange the value for value_range
     */
    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    /**
     * 属性值校验规则(用在通用属性上，Mall商家上传时候需要校验)
     * 
     * @return valueRange the value for value_range
     */
    public String getValueRange() {
        return this.valueRange;
    }

    /**
     * 属性类型（0:item通用属性，1:KEY关键属性，2:NON_KEY非关键属性，3:SALE销售属性）
     * 
     * @param propType the value for prop_type
     */
    public void setPropType(Byte propType) {
        this.propType = propType;
    }

    /**
     * 属性类型（0:item通用属性，1:KEY关键属性，2:NON_KEY非关键属性，3:SALE销售属性）
     * 
     * @return propType the value for prop_type
     */
    public Byte getPropType() {
        return this.propType;
    }

    /**
     * 位置信息，发布商品的时候决定哪个先显示
     * 
     * @param sequence the value for sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 位置信息，发布商品的时候决定哪个先显示
     * 
     * @return sequence the value for sequence
     */
    public Integer getSequence() {
        return this.sequence;
    }

    /**
     * 是否有效
     * 
     * @param active the value for active
     */
    public void setActive(Byte active) {
        this.active = active;
    }

    /**
     * 是否有效
     * 
     * @return active the value for active
     */
    public Byte getActive() {
        return this.active;
    }

    /**
     * 增加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 增加时间
     * 
     * @return addtime the value for addtime
     */
    public Date getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public Date getUpdatetime() {
        return this.updatetime;
    }
}
