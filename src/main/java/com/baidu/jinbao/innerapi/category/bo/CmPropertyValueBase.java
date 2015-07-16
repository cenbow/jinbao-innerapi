/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CmPropertyValueBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前台类目id（TODO，确认是否需要，原微购没有）
     */
    private Integer cid;

    /**
     * 前台属性项ID
     */
    private Integer cmPid;

    /**
     * 前台属性值字典ID
     */
    private Integer dicVid;

    /**
     * 位置
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
     * 前台类目id（TODO，确认是否需要，原微购没有）
     * 
     * @param cid the value for cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 前台类目id（TODO，确认是否需要，原微购没有）
     * 
     * @return cid the value for cid
     */
    public Integer getCid() {
        return this.cid;
    }

    /**
     * 前台属性项ID
     * 
     * @param cmPid the value for cm_pid
     */
    public void setCmPid(Integer cmPid) {
        this.cmPid = cmPid;
    }

    /**
     * 前台属性项ID
     * 
     * @return cmPid the value for cm_pid
     */
    public Integer getCmPid() {
        return this.cmPid;
    }

    /**
     * 前台属性值字典ID
     * 
     * @param dicVid the value for dic_vid
     */
    public void setDicVid(Integer dicVid) {
        this.dicVid = dicVid;
    }

    /**
     * 前台属性值字典ID
     * 
     * @return dicVid the value for dic_vid
     */
    public Integer getDicVid() {
        return this.dicVid;
    }

    /**
     * 位置
     * 
     * @param pos the value for pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * 位置
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
