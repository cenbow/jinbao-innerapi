/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.spu.bo;

import java.util.Date;

public class CspuLevelBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * cspuid
     */
    private Long cspuid;

    /**
     * 等级，用于对相应挂靠sku，采取不同更新策略
     */
    private Long level;

    /**
     * 添加时间
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
     * cspuid
     * 
     * @param cspuid the value for cspuid
     */
    public void setCspuid(Long cspuid) {
        this.cspuid = cspuid;
    }

    /**
     * cspuid
     * 
     * @return cspuid the value for cspuid
     */
    public Long getCspuid() {
        return this.cspuid;
    }

    /**
     * 等级，用于对相应挂靠sku，采取不同更新策略
     * 
     * @param level the value for level
     */
    public void setLevel(Long level) {
        this.level = level;
    }

    /**
     * 等级，用于对相应挂靠sku，采取不同更新策略
     * 
     * @return level the value for level
     */
    public Long getLevel() {
        return this.level;
    }

    /**
     * 添加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
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
