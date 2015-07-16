package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class MallSkuPpsBase implements java.io.Serializable {

    private static final long serialVersionUID = 4534073648657701098L;

    /**
     * 自增id
     */
    private Long id;

    /**
     * 店铺id
     */
    private Integer shopid;

    /**
     * 商家id
     */
    private Long merchantid;

    /**
     * skuid
     */
    private Long skuid;

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     */
    private Integer regionid;

    /**
     * ALL:0，MOBILE:1，WEB:2
     */
    private Byte pcMobile;

    /**
     * 原始价格
     */
    private Object price;

    /**
     * 折扣价
     */
    private Object discountPrice;

    /**
     * 闪购价
     */
    private Object flashPrice;

    /**
     * 点券价
     */
    private Object ticketPrice;

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     */
    private Byte promotionType;

    /**
     * 库存值
     */
    private Integer stock;

    /**
     * 是否支持货到付款,1：支持，0不支持
     */
    private Byte postPay;

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     */
    private Date mUpdateTime;

    /**
     * 预警库存
     */
    private Integer inventoryWarning;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 对sku_id+region_id+pc_mobile做MD5，用于重复判断
     */
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
    public void setPcMobile(Byte pcMobile) {
        this.pcMobile = pcMobile;
    }

    /**
     * ALL:0，MOBILE:1，WEB:2
     * 
     * @return pcMobile the value for pc_mobile
     */
    public Byte getPcMobile() {
        return this.pcMobile;
    }

    /**
     * 原始价格
     * 
     * @param price the value for price
     */
    public void setPrice(Object price) {
        this.price = price;
    }

    /**
     * 原始价格
     * 
     * @return price the value for price
     */
    public Object getPrice() {
        return this.price;
    }

    /**
     * 折扣价
     * 
     * @param discountPrice the value for discount_price
     */
    public void setDiscountPrice(Object discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 折扣价
     * 
     * @return discountPrice the value for discount_price
     */
    public Object getDiscountPrice() {
        return this.discountPrice;
    }

    /**
     * 闪购价
     * 
     * @param flashPrice the value for flash_price
     */
    public void setFlashPrice(Object flashPrice) {
        this.flashPrice = flashPrice;
    }

    /**
     * 闪购价
     * 
     * @return flashPrice the value for flash_price
     */
    public Object getFlashPrice() {
        return this.flashPrice;
    }

    /**
     * 点券价
     * 
     * @param ticketPrice the value for ticket_price
     */
    public void setTicketPrice(Object ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * 点券价
     * 
     * @return ticketPrice the value for ticket_price
     */
    public Object getTicketPrice() {
        return this.ticketPrice;
    }

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     * 
     * @param promotionType the value for promotion_type
     */
    public void setPromotionType(Byte promotionType) {
        this.promotionType = promotionType;
    }

    /**
     * NONE:0,PROMOTION:1,FLASH:2,ALL:3
     * 
     * @return promotionType the value for promotion_type
     */
    public Byte getPromotionType() {
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
    public void setPostPay(Byte postPay) {
        this.postPay = postPay;
    }

    /**
     * 是否支持货到付款,1：支持，0不支持
     * 
     * @return postPay the value for post_pay
     */
    public Byte getPostPay() {
        return this.postPay;
    }

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     * 
     * @param mUpdateTime the value for m_update_time
     */
    public void setMUpdateTime(Date mUpdateTime) {
        this.mUpdateTime = mUpdateTime;
    }

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     * 
     * @return mUpdateTime the value for m_update_time
     */
    public Date getMUpdateTime() {
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
