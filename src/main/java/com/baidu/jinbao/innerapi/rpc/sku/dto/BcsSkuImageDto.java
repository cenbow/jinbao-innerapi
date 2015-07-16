package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class BcsSkuImageDto {

    /**
     * id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品shard内部id，自增id
     */
    @Protobuf(order = 2)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 3)
    private String skuid;

    /**
     * 图片第三方地址
     */
    @Protobuf(order = 4)
    private String imageUrl;

    /**
     * 图片本地地址
     */
    @Protobuf(order = 5)
    private String imageBcsUrl;

    /**
     * 图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地
     */
    @Protobuf(order = 6)
    private Integer status;

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
     * 图片类型：主图，俯视图、侧视45度图等
     */
    @Protobuf(order = 15)
    private Integer type;

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
     * 商品shard内部id，自增id
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * 商品shard内部id，自增id
     * 
     * @return skuInnerid the value for sku_innerid
     */
    public Long getSkuInnerid() {
        return this.skuInnerid;
    }

    /**
     * 商品全局唯一标识id
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    /**
     * 商品全局唯一标识id
     * 
     * @return skuid the value for skuid
     */
    public String getSkuid() {
        return this.skuid;
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
     * 图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地
     * 
     * @param imageStatus the value for image_status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 图片下载状态：0未开始，1下载出错，2正在下载，3下载到本地
     * 
     * @return imageStatus the value for image_status
     */
    public Integer getStatus() {
        return this.status;
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
     * 图片类型：主图，俯视图、侧视45度图等
     * 
     * @param imageType the value for image_type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 图片类型：主图，俯视图、侧视45度图等
     * 
     * @return imageType the value for image_type
     */
    public Integer getType() {
        return this.type;
    }
}
