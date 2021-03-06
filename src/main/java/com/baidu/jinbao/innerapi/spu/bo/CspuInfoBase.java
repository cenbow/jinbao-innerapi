/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.spu.bo;

import java.util.Date;

public class CspuInfoBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long cspuid;

    /**
     * 后台类目ID
     */
    private Long cid;

    /**
     * 对应的spu（去掉销售属性后的部分）
     */
    private Long spuid;

    /**
     * cspu名称:比如 诺基亚n97 16G 黑色
     */
    private String name;

    /**
     * Cspu别名，可以多个，分号分开
     */
    private String alias;

    /**
     * 品牌：比如诺基亚
     */
    private Long brand;

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     */
    private String productModel;

    /**
     * 产品条形码
     */
    private String productUpc;

    /**
     * 销售属性pv对（归一化的id对），比如：容量:100ml;颜色:黑色
     */
    private String saleAttribute;

    /**
     * 一般属性pv对（归一化的id），比如：风格：雪纺；
     */
    private String attribute;

    /**
     * 原始网站归一化前销售属性pv对
     */
    private String saleAttributeOrig;

    /**
     * 始网站归一化前一般属性pv对
     */
    private String attributeOrig;

    /**
     * 扩展属性字段，比如以后我们想扩展竞品、搭配商品
     */
    private String extendAttribute;

    /**
     * 包装清单，例如：鼠标X1,用户文档X1,AA电池X2
     */
    private String packList;

    /**
     * 官方价格
     */
    private Object price;

    /**
     * 对应产品在官网的详情介绍页
     */
    private String url;

    /**
     * 产品详情图文信息
     */
    private byte[] bigField;

    /**
     * 0：正常;1：已删除，会移出表
     */
    private Byte deleted;

    /**
     * 是否有效,1有效，0无效
     */
    private Byte active;

    /**
     * 等级，用于对相应挂靠sku，采取不同更新策略
     */
    private Long level;

    /**
     * 来源 0 MACHINE，1 MANUAL
     */
    private Byte cspuFrom;

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
     * @param cspuid the value for cspuid
     */
    public void setCspuid(Long cspuid) {
        this.cspuid = cspuid;
    }

    /**
     * 自增id
     * 
     * @return cspuid the value for cspuid
     */
    public Long getCspuid() {
        return this.cspuid;
    }

    /**
     * 后台类目ID
     * 
     * @param cid the value for cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 后台类目ID
     * 
     * @return cid the value for cid
     */
    public Long getCid() {
        return this.cid;
    }

    /**
     * 对应的spu（去掉销售属性后的部分）
     * 
     * @param spuid the value for spuid
     */
    public void setSpuid(Long spuid) {
        this.spuid = spuid;
    }

    /**
     * 对应的spu（去掉销售属性后的部分）
     * 
     * @return spuid the value for spuid
     */
    public Long getSpuid() {
        return this.spuid;
    }

    /**
     * cspu名称:比如 诺基亚n97 16G 黑色
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * cspu名称:比如 诺基亚n97 16G 黑色
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Cspu别名，可以多个，分号分开
     * 
     * @param alias the value for alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Cspu别名，可以多个，分号分开
     * 
     * @return alias the value for alias
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 品牌：比如诺基亚
     * 
     * @param brand the value for brand
     */
    public void setBrand(Long brand) {
        this.brand = brand;
    }

    /**
     * 品牌：比如诺基亚
     * 
     * @return brand the value for brand
     */
    public Long getBrand() {
        return this.brand;
    }

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     * 
     * @param productModel the value for product_model
     */
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     * 
     * @return productModel the value for product_model
     */
    public String getProductModel() {
        return this.productModel;
    }

    /**
     * 产品条形码
     * 
     * @param productUpc the value for product_upc
     */
    public void setProductUpc(String productUpc) {
        this.productUpc = productUpc;
    }

    /**
     * 产品条形码
     * 
     * @return productUpc the value for product_upc
     */
    public String getProductUpc() {
        return this.productUpc;
    }

    /**
     * 销售属性pv对（归一化的id对），比如：容量:100ml;颜色:黑色
     * 
     * @param saleAttribute the value for sale_attribute
     */
    public void setSaleAttribute(String saleAttribute) {
        this.saleAttribute = saleAttribute;
    }

    /**
     * 销售属性pv对（归一化的id对），比如：容量:100ml;颜色:黑色
     * 
     * @return saleAttribute the value for sale_attribute
     */
    public String getSaleAttribute() {
        return this.saleAttribute;
    }

    /**
     * 一般属性pv对（归一化的id），比如：风格：雪纺；
     * 
     * @param attribute the value for attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 一般属性pv对（归一化的id），比如：风格：雪纺；
     * 
     * @return attribute the value for attribute
     */
    public String getAttribute() {
        return this.attribute;
    }

    /**
     * 原始网站归一化前销售属性pv对
     * 
     * @param saleAttributeOrig the value for sale_attribute_orig
     */
    public void setSaleAttributeOrig(String saleAttributeOrig) {
        this.saleAttributeOrig = saleAttributeOrig;
    }

    /**
     * 原始网站归一化前销售属性pv对
     * 
     * @return saleAttributeOrig the value for sale_attribute_orig
     */
    public String getSaleAttributeOrig() {
        return this.saleAttributeOrig;
    }

    /**
     * 始网站归一化前一般属性pv对
     * 
     * @param attributeOrig the value for attribute_orig
     */
    public void setAttributeOrig(String attributeOrig) {
        this.attributeOrig = attributeOrig;
    }

    /**
     * 始网站归一化前一般属性pv对
     * 
     * @return attributeOrig the value for attribute_orig
     */
    public String getAttributeOrig() {
        return this.attributeOrig;
    }

    /**
     * 扩展属性字段，比如以后我们想扩展竞品、搭配商品
     * 
     * @param extendAttribute the value for extend_attribute
     */
    public void setExtendAttribute(String extendAttribute) {
        this.extendAttribute = extendAttribute;
    }

    /**
     * 扩展属性字段，比如以后我们想扩展竞品、搭配商品
     * 
     * @return extendAttribute the value for extend_attribute
     */
    public String getExtendAttribute() {
        return this.extendAttribute;
    }

    /**
     * 包装清单，例如：鼠标X1,用户文档X1,AA电池X2
     * 
     * @param packList the value for pack_list
     */
    public void setPackList(String packList) {
        this.packList = packList;
    }

    /**
     * 包装清单，例如：鼠标X1,用户文档X1,AA电池X2
     * 
     * @return packList the value for pack_list
     */
    public String getPackList() {
        return this.packList;
    }

    /**
     * 官方价格
     * 
     * @param price the value for price
     */
    public void setPrice(Object price) {
        this.price = price;
    }

    /**
     * 官方价格
     * 
     * @return price the value for price
     */
    public Object getPrice() {
        return this.price;
    }

    /**
     * 对应产品在官网的详情介绍页
     * 
     * @param url the value for url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 对应产品在官网的详情介绍页
     * 
     * @return url the value for url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 产品详情图文信息
     * 
     * @param bigField the value for big_field
     */
    public void setBigField(byte[] bigField) {
        this.bigField = bigField;
    }

    /**
     * 产品详情图文信息
     * 
     * @return bigField the value for big_field
     */
    public byte[] getBigField() {
        return this.bigField;
    }

    /**
     * 0：正常;1：已删除，会移出表
     * 
     * @param deleted the value for deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 0：正常;1：已删除，会移出表
     * 
     * @return deleted the value for deleted
     */
    public Byte getDeleted() {
        return this.deleted;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @param active the value for active
     */
    public void setActive(Byte active) {
        this.active = active;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @return active the value for active
     */
    public Byte getActive() {
        return this.active;
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
     * 来源 0 MACHINE，1 MANUAL
     * 
     * @param from the value for from
     */
    public void setCspuFrom(Byte cspuFrom) {
        this.cspuFrom = cspuFrom;
    }

    /**
     * 来源 0 MACHINE，1 MANUAL
     * 
     * @return from the value for from
     */
    public Byte getCspuFrom() {
        return this.cspuFrom;
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
