package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class MallSkuInfo extends MallSkuInfoBase {

    private static final long serialVersionUID = -1908125637584875679L;

    public MallSkuInfo checkSkuId() {
        if (this.getSkuid() == null) {
            throw new RuntimeException("SkuId id is null");
        }
        return this;
    }

    public MallSkuInfo checkSellAttribute() {
        if (this.getSellAttribute() == null) {
            throw new RuntimeException("SellAttribute id is null");
        }
        return this;
    }

    public MallSkuInfo checkPropertyValues() {
        if (this.getPropertyValues() == null) {
            throw new RuntimeException("PropertyValues id is null");
        }
        return this;
    }

    public MallSkuInfo checkUpc() {
        if (this.getUpc() == null) {
            throw new RuntimeException("Upc id is null");
        }
        return this;
    }

    public MallSkuInfo checkItemId() {
        if (this.getItemid() == null) {
            throw new RuntimeException("ItemId id is null");
        }
        return this;
    }

    public MallSkuInfo checkDefault() {
        if (this.getSkuType() == null) {
            this.setSkuType((byte) 0);
        }
        if (this.getUpc() == null) {
            this.setUpc("0");
        }
        if (this.getDeleted() == null) {
            this.setDeleted((byte) 0);
        }
        if (this.getWeight() == null) {
            this.setWeight("0");
        }
        if (this.getVolume() == null) {
            this.setVolume("0");
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getOuterid() == null) {
            this.setOuterid("");
        }
        return this;
    }

    public MallSkuInfo checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}