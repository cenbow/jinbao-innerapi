/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CategoryMapBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前端类目id
     */
    private Integer cmCid;

    /**
     * 后端类目id
     */
    private Integer baseCid;

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
     * 后端类目id
     * 
     * @param baseCid the value for base_cid
     */
    public void setBaseCid(Integer baseCid) {
        this.baseCid = baseCid;
    }

    /**
     * 后端类目id
     * 
     * @return baseCid the value for base_cid
     */
    public Integer getBaseCid() {
        return this.baseCid;
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
