package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MallSkuPpsDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 店铺id
     */
    @Protobuf(order = 2)
    private Integer shopid;

    /**
     * 商家id
     */
    @Protobuf(order = 3)
    private Long merchantid;

    /**
     * skuid
     */
    @Protobuf(order = 4)
    private Long skuid;

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     */
    @Protobuf(order = 5)
    private Integer regionid;

    /**
     * ALL:0，MOBILE:1，WEB:2
     */
    @Protobuf(order = 6)
    private Integer pcMobile;

    /**
     * 原始价格
     */
    @Protobuf(order = 7)
    private Float price;

    /**
     * 折扣价
     */
    @Protobuf(order = 8)
    private Float discountPrice;

    /**
     * 闪购价
     */
    @Protobuf(order = 9)
    private Float flashPrice;

    /**
     * 点券价
     */
    @Protobuf(order = 10)
    private Float ticketPrice;

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     */
    @Protobuf(order = 11)
    private Integer promotionType;

    /**
     * 库存值
     */
    @Protobuf(order = 12)
    private Integer stock;

    /**
     * 是否支持货到付款,1：支持，0不支持
     */
    @Protobuf(order = 13)
    private Integer postPay;

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     */
    @Protobuf(order = 14)
    private String mUpdateTime;

    /**
     * 预警库存
     */
    @Protobuf(order = 15)
    private Integer inventoryWarning;

    /**
     * 添加时间
     */
    @Protobuf(order = 16)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 17)
    private String updatetime;

    /**
     * 对sku_id+region_id+pc_mobile做MD5，用于重复判断
     */
    @Protobuf(order = 18)
    private String ppsMd5;

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
     * 店铺id
     * 
     * @param shopid the value for shopid
     */
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    /**
     * 店铺id
     * 
     * @return shopid the value for shopid
     */
    public Integer getShopid() {
        return this.shopid;
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
     * skuid
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    /**
     * skuid
     * 
     * @return skuid the value for skuid
     */
    public Long getSkuid() {
        return this.skuid;
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
     * ALL:0，MOBILE:1，WEB:2
     * 
     * @param pcMobile the value for pc_mobile
     */
    public void setPcMobile(Integer pcMobile) {
        this.pcMobile = pcMobile;
    }

    /**
     * ALL:0，MOBILE:1，WEB:2
     * 
     * @return pcMobile the value for pc_mobile
     */
    public Integer getPcMobile() {
        return this.pcMobile;
    }

    

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Float getFlashPrice() {
        return flashPrice;
    }

    public void setFlashPrice(Float flashPrice) {
        this.flashPrice = flashPrice;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getmUpdateTime() {
        return mUpdateTime;
    }

    public void setmUpdateTime(String mUpdateTime) {
        this.mUpdateTime = mUpdateTime;
    }

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     * 
     * @param promotionType the value for promotion_type
     */
    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     * 
     * @return promotionType the value for promotion_type
     */
    public Integer getPromotionType() {
        return this.promotionType;
    }

    /**
     * 库存值
     * 
     * @param stock the value for stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 库存值
     * 
     * @return stock the value for stock
     */
    public Integer getStock() {
        return this.stock;
    }

    /**
     * 是否支持货到付款,1：支持，0不支持
     * 
     * @param postPay the value for post_pay
     */
    public void setPostPay(Integer postPay) {
        this.postPay = postPay;
    }

    /**
     * 是否支持货到付款,1：支持，0不支持
     * 
     * @return postPay the value for post_pay
     */
    public Integer getPostPay() {
        return this.postPay;
    }

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     * 
     * @param mUpdateTime the value for m_update_time
     */
    public void setMUpdateTime(String mUpdateTime) {
        this.mUpdateTime = mUpdateTime;
    }

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     * 
     * @return mUpdateTime the value for m_update_time
     */
    public String getMUpdateTime() {
        return this.mUpdateTime;
    }

    /**
     * 预警库存
     * 
     * @param inventoryWarning the value for inventory_warning
     */
    public void setInventoryWarning(Integer inventoryWarning) {
        this.inventoryWarning = inventoryWarning;
    }

    /**
     * 预警库存
     * 
     * @return inventoryWarning the value for inventory_warning
     */
    public Integer getInventoryWarning() {
        return this.inventoryWarning;
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
     * 对sku_id+region_id+pc_mobile做MD5，用于重复判断
     * 
     * @param ppsMd5 the value for pps_md5
     */
    public void setPpsMd5(String ppsMd5) {
        this.ppsMd5 = ppsMd5;
    }

    /**
     * 对sku_id+region_id+pc_mobile做MD5，用于重复判断
     * 
     * @return ppsMd5 the value for pps_md5
     */
    public String getPpsMd5() {
        return this.ppsMd5;
    }
}
