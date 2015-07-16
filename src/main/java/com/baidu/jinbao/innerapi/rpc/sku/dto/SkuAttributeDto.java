package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuAttributeDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * Shard内商品id
     */
    @Protobuf(order = 2)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 3)
    private String skuid;

    /**
     * 商家id
     */
    @Protobuf(order = 4)
    private Long merchantid;

    /**
     * 属性id:属性值id；(存策略归一化后的id)
     */
    @Protobuf(order = 5)
    private String propertyId;

    /**
     * 属性1：属性值；属性2:属性值 (存商家原始属性对)
     */
    @Protobuf(order = 6)
    private String propertyValues;

    /**
     * 对property_id+property_values做hash，用于重复判断
     */
    @Protobuf(order = 7)
    private String propertyHash;

    /**
     * 添加时间
     */
    @Protobuf(order = 8)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 9)
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
     * Shard内商品id
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * Shard内商品id
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
     * 商家id
     * 
     * @param merchantid the value for merchantid
     */
    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * 商家id
     * 
     * @return merchantid the value for merchantid
     */
    public Long getMerchantid() {
        return this.merchantid;
    }

    /**
     * 属性id:属性值id；(存策略归一化后的id)
     * 
     * @param propertyId the value for property_id
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 属性id:属性值id；(存策略归一化后的id)
     * 
     * @return propertyId the value for property_id
     */
    public String getPropertyId() {
        return this.propertyId;
    }

    /**
     * 属性1：属性值；属性2:属性值 (存商家原始属性对)
     * 
     * @param propertyValues the value for property_values
     */
    public void setPropertyValues(String propertyValues) {
        this.propertyValues = propertyValues;
    }

    /**
     * 属性1：属性值；属性2:属性值 (存商家原始属性对)
     * 
     * @return propertyValues the value for property_values
     */
    public String getPropertyValues() {
        return this.propertyValues;
    }

    /**
     * 对property_id+property_values做hash，用于重复判断
     * 
     * @param propertyHash the value for property_hash
     */
    public void setPropertyHash(String propertyHash) {
        this.propertyHash = propertyHash;
    }

    /**
     * 对property_id+property_values做hash，用于重复判断
     * 
     * @return propertyHash the value for property_hash
     */
    public String getPropertyHash() {
        return this.propertyHash;
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
