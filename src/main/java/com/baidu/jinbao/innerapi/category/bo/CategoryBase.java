/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CategoryBase implements java.io.Serializable {

    private static final long serialVersionUID = 3279026222820712293L;

    /**
     * 自增类目id
     */
    private Long categoryid;

    /**
     * 父类目id(root节点的父节点id为0)
     */
    private Long parentid;

    /**
     * 表示名称
     */
    private String name;

    /**
     * 1:叶节点 0:非叶节点
     */
    private Byte isleaf;

    /**
     * 优先级(Mall商家上传时候，确定类目排序)
     */
    private Integer pos;

    /**
     * 增加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否被删除，0：没有删除，1：删除
     */
    private Byte deleted;

    /**
     * 自增类目id
     * 
     * @param categoryid the value for categoryid
     */
    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * 自增类目id
     * 
     * @return categoryid the value for categoryid
     */
    public Long getCategoryid() {
        return this.categoryid;
    }

    /**
     * 父类目id(root节点的父节点id为0)
     * 
     * @param parentid the value for parentid
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * 父类目id(root节点的父节点id为0)
     * 
     * @return parentid the value for parentid
     */
    public Long getParentid() {
        return this.parentid;
    }

    /**
     * 表示名称
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 表示名称
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
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
     * 优先级(Mall商家上传时候，确定类目排序)
     * 
     * @param pos the value for pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * 优先级(Mall商家上传时候，确定类目排序)
     * 
     * @return pos the value for pos
     */
    public Integer getPos() {
        return this.pos;
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

    /**
     * 是否被删除，0：没有删除，1：删除
     * 
     * @param deleted the value for deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 是否被删除，0：没有删除，1：删除
     * 
     * @return deleted the value for deleted
     */
    public Byte getDeleted() {
        return this.deleted;
    }
}
