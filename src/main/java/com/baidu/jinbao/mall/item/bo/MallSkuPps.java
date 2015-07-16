package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class MallSkuPps extends MallSkuPpsBase {

    private static final long serialVersionUID = 5578433353500279000L;

    public MallSkuPps checkShopId() {
        if (this.getShopid() == null) {
            throw new RuntimeException("ShopId id is null");
        }
        return this;
    }

    public MallSkuPps checkMerchantId() {
        if (this.getMerchantid() == null) {
            throw new RuntimeException("MerchantId id is null");
        }
        return this;
    }

    public MallSkuPps checkSkuId() {
        if (this.getSkuid() == null) {
            throw new RuntimeException("SkuId id is null");
        }
        return this;
    }

    public MallSkuPps checkPrice() {
        if (this.getPrice() == null) {
            throw new RuntimeException("Price id is null");
        }
        return this;
    }

    public MallSkuPps checkDiscountPrice() {
        if (this.getDiscountPrice() == null) {
            throw new RuntimeException("DiscountPrice id is null");
        }
        return this;
    }

    public MallSkuPps checkStock() {
        if (this.getStock() == null) {
            throw new RuntimeException("Stock id is null");
        }
        return this;
    }

    public MallSkuPps checkPcMobile() {
        if (this.getPcMobile() == null) {
            throw new RuntimeException("pcmobile is null");
        }
        return this;
    }

    public MallSkuPps checkRegionId() {
        if (this.getRegionid() == null) {
            throw new RuntimeException("Stock id is null");
        }
        return this;
    }

    public MallSkuPps checkDefault() {
        if (this.getRegionid() == null) {
            this.setRegionid(Integer.valueOf(0));
        }
        if (this.getPcMobile() == null) {
            this.setPcMobile((byte) 0);
        }
        if (this.getFlashPrice() == null) {
            this.setFlashPrice("0");
        }
        if (this.getTicketPrice() == null) {
            this.setTicketPrice("0");
        }
        if (this.getPromotionType() == null) {
            this.setPromotionType((byte) 0);
        }
        if (this.getPostPay() == null) {
            this.setPostPay((byte) 0);
        }
        if (this.getMUpdateTime() == null) {
            this.setMUpdateTime(new Date());
        }
        if (this.getInventoryWarning() == null) {
            this.setInventoryWarning(Integer.valueOf(0));
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }

    public MallSkuPps checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}