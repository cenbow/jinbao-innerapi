/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class MallSkuInfoBase implements java.io.Serializable {
    
    private static final long serialVersionUID = -5511080318257786422L;

    /**
     * 自增id
     */
    private Long skuid;

    /**
     * 0:NORMAL,1:GIFT（赠品）
     */
    private Byte skuType;

    /**
     * 销售属性（pid：vid：是否有小图）（多个之间；号分隔，是否有小图：1有，0没有）
     */
    private String sellAttribute;

    /**
     * 属性1：属性值:是否自定义：是否有小图；属性2:属性值:是否自定义：是否有小图(图片显示在item页，这边只存销售属性/规格部分，是否有小图：1有，0没有)
     */
    private String propertyValues;

    /**
     * 条形码（商家录入，运营平台能更改）
     */
    private String upc;

    /**
     * 商品内部id
     */
    private Long itemid;

    /**
     * 是否被删除,1：是，0：否
     */
    private Byte deleted;

    /**
     * 重量
     */
    private Object weight;

    /**
     * 体积
     */
    private Object volume;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 商品在商家ERP系统中的id
     */
    private String outerid;

    /**
     * 自增id
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    /**
     * 自增id
     * 
     * @return skuid the value for skuid
     */
    public Long getSkuid() {
        return this.skuid;
    }

    /**
     * 0:NORMAL,1:GIFT（赠品）
     * 
     * @param skuType the value for sku_type
     */
    public void setSkuType(Byte skuType) {
        this.skuType = skuType;
    }

    /**
     * 0:NORMAL,1:GIFT（赠品）
     * 
     * @return skuType the value for sku_type
     */
    public Byte getSkuType() {
        return this.skuType;
    }

    /**
     * 销售属性（pid：vid：是否有小图）（多个之间；号分隔，是否有小图：1有，0没有）
     * 
     * @param sellAttribute the value for sell_attribute
     */
    public void setSellAttribute(String sellAttribute) {
        this.sellAttribute = sellAttribute;
    }

    /**
     * 销售属性（pid：vid：是否有小图）（多个之间；号分隔，是否有小图：1有，0没有）
     * 
     * @return sellAttribute the value for sell_attribute
     */
    public String getSellAttribute() {
        return this.sellAttribute;
    }

    /**
     * 属性1：属性值:是否自定义：是否有小图；属性2:属性值:是否自定义：是否有小图(图片显示在item页，这边只存销售属性/规格部分，是否有小图：1有，0没有)
     * 
     * @param propertyValues the value for property_values
     */
    public void setPropertyValues(String propertyValues) {
        this.propertyValues = propertyValues;
    }

    /**
     * 属性1：属性值:是否自定义：是否有小图；属性2:属性值:是否自定义：是否有小图(图片显示在item页，这边只存销售属性/规格部分，是否有小图：1有，0没有)
     * 
     * @return propertyValues the value for property_values
     */
    public String getPropertyValues() {
        return this.propertyValues;
    }

    /**
     * 条形码（商家录入，运营平台能更改）
     * 
     * @param upc the value for upc
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * 条形码（商家录入，运营平台能更改）
     * 
     * @return upc the value for upc
     */
    public String getUpc() {
        return this.upc;
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
     * 是否被删除,1：是，0：否
     * 
     * @param deleted the value for deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 是否被删除,1：是，0：否
     * 
     * @return deleted the value for deleted
     */
    public Byte getDeleted() {
        return this.deleted;
    }

    /**
     * 重量
     * 
     * @param weight the value for weight
     */
    public void setWeight(Object weight) {
        this.weight = weight;
    }

    /**
     * 重量
     * 
     * @return weight the value for weight
     */
    public Object getWeight() {
        return this.weight;
    }

    /**
     * 体积
     * 
     * @param volume the value for volume
     */
    public void setVolume(Object volume) {
        this.volume = volume;
    }

    /**
     * 体积
     * 
     * @return volume the value for volume
     */
    public Object getVolume() {
        return this.volume;
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
     * 商品在商家ERP系统中的id
     * 
     * @param outerid the value for outerid
     */
    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    /**
     * 商品在商家ERP系统中的id
     * 
     * @return outerid the value for outerid
     */
    public String getOuterid() {
        return this.outerid;
    }
}
