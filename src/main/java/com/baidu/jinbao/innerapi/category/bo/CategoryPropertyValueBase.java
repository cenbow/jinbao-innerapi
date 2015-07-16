/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class CategoryPropertyValueBase implements java.io.Serializable {

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
     * 属性项的字典id
     */
    private Long pid;

    /**
     * 属性值字典id
     */
    private Long vid;

    /**
     * 属性值别名（明文），可以存多个，以分号分开
     */
    private String alias;

    /**
     * 运营订属性值对应图片，一般是颜色
     */
    private String imageUrl;

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
     * 属性值字典id
     * 
     * @param vid the value for vid
     */
    public void setVid(Long vid) {
        this.vid = vid;
    }

    /**
     * 属性值字典id
     * 
     * @return vid the value for vid
     */
    public Long getVid() {
        return this.vid;
    }

    /**
     * 属性值别名（明文），可以存多个，以分号分开
     * 
     * @param alias the value for alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 属性值别名（明文），可以存多个，以分号分开
     * 
     * @return alias the value for alias
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 运营订属性值对应图片，一般是颜色
     * 
     * @param imageUrl the value for image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 运营订属性值对应图片，一般是颜色
     * 
     * @return imageUrl the value for image_url
     */
    public String getImageUrl() {
        return this.imageUrl;
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
