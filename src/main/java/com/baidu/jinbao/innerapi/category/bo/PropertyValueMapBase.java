/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class PropertyValueMapBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前台属性值ID
     */
    private Integer cmVid;

    /**
     * 后台属性值ID
     */
    private Integer baseVid;

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
     * 前台属性值ID
     * 
     * @param cmVid the value for cm_vid
     */
    public void setCmVid(Integer cmVid) {
        this.cmVid = cmVid;
    }

    /**
     * 前台属性值ID
     * 
     * @return cmVid the value for cm_vid
     */
    public Integer getCmVid() {
        return this.cmVid;
    }

    /**
     * 后台属性值ID
     * 
     * @param baseVid the value for base_vid
     */
    public void setBaseVid(Integer baseVid) {
        this.baseVid = baseVid;
    }

    /**
     * 后台属性值ID
     * 
     * @return baseVid the value for base_vid
     */
    public Integer getBaseVid() {
        return this.baseVid;
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
