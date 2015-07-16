package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MallSkuInfoDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long skuid;

    /**
     * 0:NORMAL,1:GIFT（赠品）
     */
    @Protobuf(order = 2)
    private Integer skuType;

    /**
     * 销售属性（pid：vid：是否有小图）（多个之间；号分隔，是否有小图：1有，0没有）
     */
    @Protobuf(order = 3)
    private String sellAttribute;

    /**
     * 属性1：属性值:是否自定义：是否有小图；属性2:属性值:是否自定义：是否有小图(图片显示在item页，这边只存销售属性/规格部分，是否有小图：1有，0没有)
     */
    @Protobuf(order = 4)
    private String propertyValues;

    /**
     * 条形码（商家录入，运营平台能更改）
     */
    @Protobuf(order = 5)
    private String upc;

    /**
     * 商品内部id
     */
    @Protobuf(order = 6)
    private Long itemid;

    /**
     * 是否被删除,1：是，0：否
     */
    @Protobuf(order = 7)
    private Integer deleted;

    /**
     * 重量
     */
    @Protobuf(order = 8)
    private Float weight;

    /**
     * 体积
     */
    @Protobuf(order = 9)
    private Float volume;

    /**
     * 添加时间
     */
    @Protobuf(order = 10)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 11)
    private String updatetime;

    /**
     * 商品在商家系统中的id
     */
    @Protobuf(order = 12)
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
    public void setSkuType(Integer skuType) {
        this.skuType = skuType;
    }

    /**
     * 0:NORMAL,1:GIFT（赠品）
     * 
     * @return skuType the value for sku_type
     */
    public Integer getSkuType() {
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
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 是否被删除,1：是，0：否
     * 
     * @return deleted the value for deleted
     */
    public Integer getDeleted() {
        return this.deleted;
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
    
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
    
    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

}
