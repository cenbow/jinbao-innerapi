package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuPpsDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商家id
     */
    @Protobuf(order = 2)
    private Long merchantid;

    /**
     * 商品shard内部id，用于表关联
     */
    @Protobuf(order = 3)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 4)
    private String skuid;

    /**
     * 地址id，可以为仓库id，目前采用国标id，全国为0
     */
    @Protobuf(order = 5)
    private Integer regionid;

    /**
     * 0:MOBILE,1:WEB(移动端还是web端等)
     */
    @Protobuf(order = 6)
    private Integer terminal;

    /**
     * 促销信息
     */
    @Protobuf(order = 7)
    private Float promotionInfo;

    /**
     * 百度惠：使用促销模型算出来的价格
     */
    @Protobuf(order = 8)
    private Float promotionPriceMachine;

    /**
     * 百度惠：人工修改的促销价格
     */
    @Protobuf(order = 9)
    private Float promotionPriceManual;

    /**
     * 原始价格
     */
    @Protobuf(order = 10)
    private Float originalprice;

    /**
     * 折扣价
     */
    @Protobuf(order = 11)
    private Float discountprice;

    /**
     * 库存值状态（1:有货，0：无货）
     */
    @Protobuf(order = 12)
    private Integer stock;

    /**
     * 是否支持货到付款（默认不支持）
     */
    @Protobuf(order = 13)
    private Integer postPay;

    /**
     * 商家的库存价格更新时间，用于比较时间进行更新
     */
    @Protobuf(order = 14)
    private String mUpdateTime;

    /**
     * 添加时间
     */
    @Protobuf(order = 15)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 16)
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
    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    /**
     * 0:MOBILE,1:WEB(移动端还是web端等)
     * 
     * @return terminal the value for terminal
     */
    public Integer getTerminal() {
        return this.terminal;
    }

    /**
     * 促销信息
     * 
     * @param promotionInfo the value for promotion_info
     */
    public void setPromotionInfo(Float promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    /**
     * 促销信息
     * 
     * @return promotionInfo the value for promotion_info
     */
    public Float getPromotionInfo() {
        return this.promotionInfo;
    }

    /**
     * 百度惠：使用促销模型算出来的价格
     * 
     * @param promotionPriceMachine the value for promotion_price_machine
     */
    public void setPromotionPriceMachine(Float promotionPriceMachine) {
        this.promotionPriceMachine = promotionPriceMachine;
    }

    /**
     * 百度惠：使用促销模型算出来的价格
     * 
     * @return promotionPriceMachine the value for promotion_price_machine
     */
    public Float getPromotionPriceMachine() {
        return this.promotionPriceMachine;
    }

    /**
     * 百度惠：人工修改的促销价格
     * 
     * @param promotionPriceManual the value for promotion_price_manual
     */
    public void setPromotionPriceManual(Float promotionPriceManual) {
        this.promotionPriceManual = promotionPriceManual;
    }

    /**
     * 百度惠：人工修改的促销价格
     * 
     * @return promotionPriceManual the value for promotion_price_manual
     */
    public Float getPromotionPriceManual() {
        return this.promotionPriceManual;
    }

    /**
     * 原始价格
     * 
     * @param originalprice the value for originalprice
     */
    public void setOriginalprice(Float originalprice) {
        this.originalprice = originalprice;
    }

    /**
     * 原始价格
     * 
     * @return originalprice the value for originalprice
     */
    public Float getOriginalprice() {
        return this.originalprice;
    }

    /**
     * 折扣价
     * 
     * @param discountprice the value for discountprice
     */
    public void setDiscountprice(Float discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * 折扣价
     * 
     * @return discountprice the value for discountprice
     */
    public Float getDiscountprice() {
        return this.discountprice;
    }

    /**
     * 库存值状态（1:有货，0：无货）
     * 
     * @param stock the value for stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 库存值状态（1:有货，0：无货）
     * 
     * @return stock the value for stock
     */
    public Integer getStock() {
        return this.stock;
    }

    /**
     * 是否支持货到付款（默认不支持）
     * 
     * @param postPay the value for post_pay
     */
    public void setPostPay(Integer postPay) {
        this.postPay = postPay;
    }

    /**
     * 是否支持货到付款（默认不支持）
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
