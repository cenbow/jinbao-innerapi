package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemAttributeDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品内部id
     */
    @Protobuf(order = 2)
    private Long itemid;

    /**
     * 属性id:属性值id；（存定义成id的属性）
     */
    @Protobuf(order = 3)
    private String propertyId;

    /**
     * 属性1：属性值:是否自定义：image_url；属性2:属性值:是否自定义：image_url(图片显示在item页，这边存的是非销售属性，是否自定义，用于表示属性项是否自定义的，在百度Mall商家平台中，可以自定义属性项)
     */
    @Protobuf(order = 4)
    private String propertyValues;

    /**
     * 对property_id+property_values做hash，用于重复判断
     */
    @Protobuf(order = 5)
    private String propertyMd5;

    /**
     * 添加时间
     */
    @Protobuf(order = 6)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 7)
    private String updatetime;

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
     * 商品内部id
     * 
     * @param itemid the value for itemid
     */
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    /**
     * 商品内部id
     * 
     * @return itemid the value for itemid
     */
    public Long getItemid() {
        return this.itemid;
    }

    /**
     * 属性id:属性值id；（存定义成id的属性）
     * 
     * @param propertyId the value for property_id
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 属性id:属性值id；（存定义成id的属性）
     * 
     * @return propertyId the value for property_id
     */
    public String getPropertyId() {
        return this.propertyId;
    }

    /**
     * 属性1：属性值:是否自定义：image_url；属性2:属性值:是否自定义：image_url(图片显示在item页，这边存的是非销售属性，是否自定义，用于表示属性项是否自定义的，在百度Mall商家平台中，可以自定义属性项)
     * 
     * @param propertyValues the value for property_values
     */
    public void setPropertyValues(String propertyValues) {
        this.propertyValues = propertyValues;
    }

    /**
     * 属性1：属性值:是否自定义：image_url；属性2:属性值:是否自定义：image_url(图片显示在item页，这边存的是非销售属性，是否自定义，用于表示属性项是否自定义的，在百度Mall商家平台中，可以自定义属性项)
     * 
     * @return propertyValues the value for property_values
     */
    public String getPropertyValues() {
        return this.propertyValues;
    }

    /**
     * 对property_id+property_values做hash，用于重复判断
     * 
     * @param propertyMd5 the value for property_md5
     */
    public void setPropertyMd5(String propertyMd5) {
        this.propertyMd5 = propertyMd5;
    }

    /**
     * 对property_id+property_values做hash，用于重复判断
     * 
     * @return propertyMd5 the value for property_md5
     */
    public String getPropertyMd5() {
        return this.propertyMd5;
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
}
