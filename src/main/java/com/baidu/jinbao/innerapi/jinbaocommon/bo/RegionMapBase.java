/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.jinbaocommon.bo;

import java.util.Date;

public class RegionMapBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 城市id
     */
    private Integer cityid;

    /**
     * 城市名称
     */
    private String cityname;

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     */
    private Integer regionid;

    /**
     * 地域名称
     */
    private String regionname;

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
     * 城市id
     * 
     * @param cityid the value for cityid
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * 城市id
     * 
     * @return cityid the value for cityid
     */
    public Integer getCityid() {
        return this.cityid;
    }

    /**
     * 城市名称
     * 
     * @param cityname the value for cityname
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    /**
     * 城市名称
     * 
     * @return cityname the value for cityname
     */
    public String getCityname() {
        return this.cityname;
    }

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     * 
     * @param regionid the value for regionid
     */
    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     * 
     * @return regionid the value for regionid
     */
    public Integer getRegionid() {
        return this.regionid;
    }

    /**
     * 地域名称
     * 
     * @param regionname the value for regionname
     */
    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    /**
     * 地域名称
     * 
     * @return regionname the value for regionname
     */
    public String getRegionname() {
        return this.regionname;
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
