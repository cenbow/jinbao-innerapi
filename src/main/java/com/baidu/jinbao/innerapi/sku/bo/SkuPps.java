package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuPps extends SkuPpsBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getSkuInnerid() != null) {
            ret.append(this.getSkuInnerid());
        }
        if (this.getMerchantid() != null) {
            ret.append(this.getMerchantid());
        }
        if (this.getPromotionInfo() != null) {
            ret.append(this.getPromotionInfo());
        }
        if (this.getPromotionPriceMachine() != null) {
            ret.append(this.getPromotionPriceMachine());
        }
        if (this.getPromotionPriceManual() != null) {
            ret.append(this.getPromotionPriceManual());
        }
        if (this.getOriginalprice() != null) {
            ret.append(this.getOriginalprice());
        }
        if (this.getDiscountprice() != null) {
            ret.append(this.getDiscountprice());
        }
        if (this.getStock() != null) {
            ret.append(this.getStock());
        }
        if (this.getPostPay() != null) {
            ret.append(this.getPostPay());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuPps skuPps) {
        if (skuPps == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuPps.getSkuInnerid());
        }
        if (this.getMerchantid() == null) {
            this.setMerchantid(skuPps.getMerchantid());
        }
        if (this.getPromotionInfo() == null) {
            this.setPromotionInfo(skuPps.getPromotionInfo());
        }
        if (this.getPromotionPriceMachine() == null) {
            this.setPromotionPriceMachine(skuPps.getPromotionPriceMachine());
        }
        if (this.getPromotionPriceManual() == null) {
            this.setPromotionPriceManual(skuPps.getPromotionPriceManual());
        }
        if (this.getOriginalprice() == null) {
            this.setOriginalprice(skuPps.getOriginalprice());
        }
        if (this.getDiscountprice() == null) {
            this.setDiscountprice(skuPps.getDiscountprice());
        }
        if (this.getStock() == null) {
            this.setStock(skuPps.getStock());
        }
        if (this.getPostPay() == null) {
            this.setPostPay(skuPps.getPostPay());
        }
    }
    
    public void setDefaultValue() {
        if (this.getPromotionInfo() == null) {
            this.setPromotionInfo("");
        }
        if (this.getPromotionPriceMachine() == null) {
            this.setPromotionPriceMachine(0);
        }
        if (this.getPromotionPriceManual() == null) {
            this.setPromotionPriceManual(0);
        }
        if (this.getOriginalprice() == null) {
            this.setOriginalprice(0.00);
        }
        if (this.getDiscountprice() == null) {
            this.setDiscountprice(0.00);
        }
        if (this.getStock() == null) {
            this.setStock(new Integer(0).byteValue());
        }
        if (this.getPostPay() == null) {
            this.setPostPay(new Integer(0).byteValue());
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getMerchantid() == null) || (this.getRegionid() == null)
                || (this.getTerminal() == null) || (this.getMUpdateTime() == null)) {
            return false;
        }
        return true;
    }
}