/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.spu.bo;

import java.util.Date;

public class BcsCspuImageBase implements java.io.Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * Spuid
     */
    private Long cspuid;

    /**
     * 图片第三方地址
     */
    private String imageUrl;

    /**
     * 图片本地地址
     */
    private String imageBcsUrl;

    /**
     * 图片下载状态，0：done：已下载到本地:1：downing：正在下载:2：error：下载出错:3：undo：未开始。DEFAULT undo
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 错误信息
     */
    private String errormessage;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 内容MD5
     */
    private String contentMd5;

    /**
     * 图片序号
     */
    private Integer sequence;

    /**
     * 缩略图json串，一堆url和尺寸
     */
    private String gipsImage;

    /**
     * 图片类型：主图，俯视图、侧视45度图等
     */
    private Byte type;

    /**
     * id
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * id
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Spuid
     * 
     * @param cspuid the value for cspuid
     */
    public void setCspuid(Long cspuid) {
        this.cspuid = cspuid;
    }

    /**
     * Spuid
     * 
     * @return cspuid the value for cspuid
     */
    public Long getCspuid() {
        return this.cspuid;
    }

    /**
     * 图片第三方地址
     * 
     * @param imageUrl the value for image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 图片第三方地址
     * 
     * @return imageUrl the value for image_url
     */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /**
     * 图片本地地址
     * 
     * @param imageBcsUrl the value for image_bcs_url
     */
    public void setImageBcsUrl(String imageBcsUrl) {
        this.imageBcsUrl = imageBcsUrl;
    }

    /**
     * 图片本地地址
     * 
     * @return imageBcsUrl the value for image_bcs_url
     */
    public String getImageBcsUrl() {
        return this.imageBcsUrl;
    }

    /**
     * 图片下载状态，0：done：已下载到本地:1：downing：正在下载:2：error：下载出错:3：undo：未开始。DEFAULT undo
     * 
     * @param status the value for status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 图片下载状态，0：done：已下载到本地:1：downing：正在下载:2：error：下载出错:3：undo：未开始。DEFAULT undo
     * 
     * @return status the value for status
     */
    public Byte getStatus() {
        return this.status;
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

    /**
     * 错误信息
     * 
     * @param errormessage the value for errormessage
     */
    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    /**
     * 错误信息
     * 
     * @return errormessage the value for errormessage
     */
    public String getErrormessage() {
        return this.errormessage;
    }

    /**
     * 图片宽度
     * 
     * @param width the value for width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 图片宽度
     * 
     * @return width the value for width
     */
    public Integer getWidth() {
        return this.width;
    }

    /**
     * 图片高度
     * 
     * @param height the value for height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 图片高度
     * 
     * @return height the value for height
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * 内容MD5
     * 
     * @param contentMd5 the value for content_md5
     */
    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    /**
     * 内容MD5
     * 
     * @return contentMd5 the value for content_md5
     */
    public String getContentMd5() {
        return this.contentMd5;
    }

    /**
     * 图片序号
     * 
     * @param sequence the value for sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 图片序号
     * 
     * @return sequence the value for sequence
     */
    public Integer getSequence() {
        return this.sequence;
    }

    /**
     * 缩略图json串，一堆url和尺寸
     * 
     * @param gipsImage the value for gips_image
     */
    public void setGipsImage(String gipsImage) {
        this.gipsImage = gipsImage;
    }

    /**
     * 缩略图json串，一堆url和尺寸
     * 
     * @return gipsImage the value for gips_image
     */
    public String getGipsImage() {
        return this.gipsImage;
    }

    /**
     * 图片类型：主图，俯视图、侧视45度图等
     * 
     * @param type the value for image_type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 图片类型：主图，俯视图、侧视45度图等
     * 
     * @return type the value for image_type
     */
    public Byte getType() {
        return this.type;
    }
}
