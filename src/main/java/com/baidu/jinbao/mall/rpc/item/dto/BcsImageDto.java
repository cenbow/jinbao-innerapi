package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BcsImageDto {
    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品ID
     */
    @Protobuf(order = 2)
    private Long itemid;

    /**
     * 0代表是item级别的图片，其他情况表示是sku上的图片
     */
    @Protobuf(order = 3)
    private Long skuid;

    /**
     * 图片第三方地址
     */
    @Protobuf(order = 4)
    private String imageurl;

    /**
     * 图片本地地址
     */
    @Protobuf(order = 5)
    private String imagebcsurl;

    /**
     * 图片下载状态，0:done：已下载到本地，1:downing：正在下载，2:error：下载出错，3:undo：未开始。
     */
    @Protobuf(order = 6)
    private Integer bcsStatus;

    /**
     * 添加时间
     */
    @Protobuf(order = 7)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 8)
    private String updatetime;

    /**
     * 错误信息
     */
    @Protobuf(order = 9)
    private String errormessage;

    /**
     * 图片宽度
     */
    @Protobuf(order = 10)
    private Integer width;

    /**
     * 图片高度
     */
    @Protobuf(order = 11)
    private Integer height;

    /**
     * 内容MD5
     */
    @Protobuf(order = 12)
    private String contentMd5;

    /**
     * 图片序号
     */
    @Protobuf(order = 13)
    private Integer sequence;

    /**
     * 缩略图json串，一堆url和尺寸
     */
    @Protobuf(order = 14)
    private String gipsImage;

    /**
     * 图片类型：主图:0，俯视图:1、侧视45度图:2，小图：3
     */
    @Protobuf(order = 15)
    private Integer picType;

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
     * 商品ID
     * 
     * @param itemid the value for itemid
     */
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    /**
     * 商品ID
     * 
     * @return itemid the value for itemid
     */
    public Long getItemid() {
        return this.itemid;
    }

    /**
     * 0代表是item级别的图片，其他情况表示是sku上的图片
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    /**
     * 0代表是item级别的图片，其他情况表示是sku上的图片
     * 
     * @return skuid the value for skuid
     */
    public Long getSkuid() {
        return this.skuid;
    }

    /**
     * 图片第三方地址
     * 
     * @param imageurl the value for imageUrl
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * 图片第三方地址
     * 
     * @return imageurl the value for imageUrl
     */
    public String getImageurl() {
        return this.imageurl;
    }

    /**
     * 图片本地地址
     * 
     * @param imagebcsurl the value for imageBCSUrl
     */
    public void setImagebcsurl(String imagebcsurl) {
        this.imagebcsurl = imagebcsurl;
    }

    /**
     * 图片本地地址
     * 
     * @return imagebcsurl the value for imageBCSUrl
     */
    public String getImagebcsurl() {
        return this.imagebcsurl;
    }

    /**
     * 图片下载状态，0:done：已下载到本地，1:downing：正在下载，2:error：下载出错，3:undo：未开始。
     * 
     * @param bcsStatus the value for bcs_status
     */
    public void setBcsStatus(Integer bcsStatus) {
        this.bcsStatus = bcsStatus;
    }

    /**
     * 图片下载状态，0:done：已下载到本地，1:downing：正在下载，2:error：下载出错，3:undo：未开始。
     * 
     * @return bcsStatus the value for bcs_status
     */
    public Integer getBcsStatus() {
        return this.bcsStatus;
    }

    /**
     * 添加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
     * 
     * @return addtime the value for addtime
     */
    public String getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public String getUpdatetime() {
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
     * 图片类型：主图:0，俯视图:1、侧视45度图:2，小图：3
     * 
     * @param picType the value for pic_type
     */
    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    /**
     * 图片类型：主图:0，俯视图:1、侧视45度图:2，小图：3
     * 
     * @return picType the value for pic_type
     */
    public Integer getPicType() {
        return this.picType;
    }
}
