/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CmPropertyBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前端类目id
     */
    private Integer cmCid;

    /**
     * 前台属性项ID
     */
    private Integer dicPid;

    /**
     * 后台属性项ID
     */
    private Integer basePid;

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
     * 前端类目id
     * 
     * @param cmCid the value for cm_cid
     */
    public void setCmCid(Integer cmCid) {
        this.cmCid = cmCid;
    }

    /**
     * 前端类目id
     * 
     * @return cmCid the value for cm_cid
     */
    public Integer getCmCid() {
        return this.cmCid;
    }

    /**
     * 前台属性项ID
     * 
     * @param dicPid the value for dic_pid
     */
    public void setDicPid(Integer dicPid) {
        this.dicPid = dicPid;
    }

    /**
     * 前台属性项ID
     * 
     * @return dicPid the value for dic_pid
     */
    public Integer getDicPid() {
        return this.dicPid;
    }

    /**
     * 后台属性项ID
     * 
     * @param basePid the value for base_pid
     */
    public void setBasePid(Integer basePid) {
        this.basePid = basePid;
    }

    /**
     * 后台属性项ID
     * 
     * @return basePid the value for base_pid
     */
    public Integer getBasePid() {
        return this.basePid;
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
