package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuPpsBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 商家id
     */
    private Long merchantid;

    /**
     * 商品shard内部id，用于表关联
     */
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    private String skuid;

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     */
    private Integer regionid;

    /**
     * 0:MOBILE,1:WEB(移动端还是web端等)
     */
    private Byte terminal;

    /**
     * 促销信息
     */
    private String promotionInfo;

    /**
     * 百度惠：使用促销模型算出来的价格
     */
    private Object promotionPriceMachine;

    /**
     * 百度惠：人工修改的促销价格
     */
    private Object promotionPriceManual;

    /**
     * 原始价格
     */
    private Object originalprice;

    /**
     * 折扣价
     */
    private Object discountprice;

    /**
     * 库存值状态（1:有货，0：无货）
     */
    private Byte stock;

    /**
     * 是否支持货到付款（默认不支持）
     */
    private Byte postPay;

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     */
    private Date mUpdateTime;

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
     * 商品shard内部id，用于表关联
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * 商品shard内部id，用于表关联
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
     * 0:MOBILE,1:WEB(移动端还是web端等)
     * 
     * @param terminal the value for terminal
     */
    public void setTerminal(Byte terminal) {
        this.terminal = terminal;
    }

    /**
     * 0:MOBILE,1:WEB(移动端还是web端等)
     * 
     * @return terminal the value for terminal
     */
    public Byte getTerminal() {
        return this.terminal;
    }

    /**
     * 促销信息
     * 
     * @param promotionInfo the value for promotion_info
     */
    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    /**
     * 促销信息
     * 
     * @return promotionInfo the value for promotion_info
     */
    public String getPromotionInfo() {
        return this.promotionInfo;
    }

    /**
     * 百度惠：使用促销模型算出来的价格
     * 
     * @param promotionPriceMachine the value for promotion_price_machine
     */
    public void setPromotionPriceMachine(Object promotionPriceMachine) {
        this.promotionPriceMachine = promotionPriceMachine;
    }

    /**
     * 百度惠：使用促销模型算出来的价格
     * 
     * @return promotionPriceMachine the value for promotion_price_machine
     */
    public Object getPromotionPriceMachine() {
        return this.promotionPriceMachine;
    }

    /**
     * 百度惠：人工修改的促销价格
     * 
     * @param promotionPriceManual the value for promotion_price_manual
     */
    public void setPromotionPriceManual(Object promotionPriceManual) {
        this.promotionPriceManual = promotionPriceManual;
    }

    /**
     * 百度惠：人工修改的促销价格
     * 
     * @return promotionPriceManual the value for promotion_price_manual
     */
    public Object getPromotionPriceManual() {
        return this.promotionPriceManual;
    }

    /**
     * 原始价格
     * 
     * @param originalprice the value for originalprice
     */
    public void setOriginalprice(Object originalprice) {
        this.originalprice = originalprice;
    }

    /**
     * 原始价格
     * 
     * @return originalprice the value for originalprice
     */
    public Object getOriginalprice() {
        return this.originalprice;
    }

    /**
     * 折扣价
     * 
     * @param discountprice the value for discountprice
     */
    public void setDiscountprice(Object discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * 折扣价
     * 
     * @return discountprice the value for discountprice
     */
    public Object getDiscountprice() {
        return this.discountprice;
    }

    /**
     * 库存值状态（1:有货，0：无货）
     * 
     * @param stock the value for stock
     */
    public void setStock(Byte stock) {
        this.stock = stock;
    }

    /**
     * 库存值状态（1:有货，0：无货）
     * 
     * @return stock the value for stock
     */
    public Byte getStock() {
        return this.stock;
    }

    /**
     * 是否支持货到付款（默认不支持）
     * 
     * @param postPay the value for post_pay
     */
    public void setPostPay(Byte postPay) {
        this.postPay = postPay;
    }

    /**
     * 是否支持货到付款（默认不支持）
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
