/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.jinbaocommon.bo;

import java.util.Date;

public class MerchantInfoBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     */
    private Long ucid;

    /**
     * 商家名称
     */
    private String name;

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
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     * 
     * @param ucid the value for ucid
     */
    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     * 
     * @return ucid the value for ucid
     */
    public Long getUcid() {
        return this.ucid;
    }

    /**
     * 商家名称
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商家名称
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
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
