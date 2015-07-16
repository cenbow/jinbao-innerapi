/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CmCategoryBase implements java.io.Serializable {

    /**
     * 自增id（前台类目id）
     */
    private Long id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 父类目id
     */
    private Integer parentid;

    /**
     * 1:叶节点 0:非叶节点
     */
    private Byte isleaf;

    /**
     * 排序位置
     */
    private Integer pos;

    /**
     * 是否有效
     */
    private Byte active;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 自增id（前台类目id）
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 自增id（前台类目id）
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 类目名称
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 类目名称
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 父类目id
     * 
     * @param parentid the value for parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 父类目id
     * 
     * @return parentid the value for parentid
     */
    public Integer getParentid() {
        return this.parentid;
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
     * 排序位置
     * 
     * @param pos the value for pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * 排序位置
     * 
     * @return pos the value for pos
     */
    public Integer getPos() {
        return this.pos;
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
