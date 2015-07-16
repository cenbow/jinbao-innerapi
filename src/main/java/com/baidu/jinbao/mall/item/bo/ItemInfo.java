package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemInfo extends ItemInfoBase {

    private static final long serialVersionUID = -1001127345833423448L;

    public ItemInfo checkItemInfoHashcode() {
        if (this.getItemInfoHashcode() == null) {
            throw new RuntimeException("ItemInfoHashcode is null");
        }
        return this;
    }

    public ItemInfo checkItemId() {
        if (this.getItemid() == null) {
            throw new RuntimeException("item id is null");
        }
        return this;
    }

    public ItemInfo checkMerchantId() {
        if (this.getMerchantid() == null) {
            throw new RuntimeException("Merchant Id is null");
        }
        return this;
    }

    public ItemInfo checkUcId() {
        if (this.getUcid() == null) {
            throw new RuntimeException("ucid Id is null");
        }
        return this;
    }

    public ItemInfo checkShopId() {
        if (this.getShopid() == null) {
            throw new RuntimeException("shop Id is null");
        }
        return this;
    }

    public ItemInfo checkTitle() {
        if (this.getTitle() == null) {
            throw new RuntimeException("title is null");
        }
        return this;
    }

    public ItemInfo checkMerchantStatus() {
        if (this.getManualStatus() == null) {
            throw new RuntimeException("merchant_status is null");
        }
        return this;
    }

    public ItemInfo checkManualStatus() {
        if (this.getManualStatus() == null) {
            throw new RuntimeException("manual_status is null");
        }
        return this;
    }

    public ItemInfo checkStartTime() {
        if (this.getStarttime() == null) {
            throw new RuntimeException("starttime is null");
        }
        return this;
    }

    public ItemInfo checkEndTime() {
        if (this.getEndtime() == null) {
            throw new RuntimeException("endtime is null");
        }
        return this;
    }

    public ItemInfo checkDefault() {
        if (this.getOuterid() == null) {
            this.setOuterid("");
        }
        if (this.getSubtitle() == null) {
            this.setSubtitle("");
        }
        if (this.getUrl() == null) {
            this.setUrl("");
        }
        if (this.getShopcategory() == null) {
            this.setShopcategory("");
        }
        if (this.getDataversion() == null) {
            this.setDataversion(0L);
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getWareBigSmallModel() == null) {
            this.setWareBigSmallModel(Integer.valueOf(0));
        }
        if (this.getWarePackType() == null) {
            this.setWarePackType(Integer.valueOf(1));
        }
        if (this.getDeleted() == null) {
            this.setDeleted((byte) 0);
        }
        if (this.getCategoryOri() == null) {
            this.setCategoryOri("");
        }
        if (this.getBrandOri() == null) {
            this.setBrandOri("");
        }
        return this;
    }

    public ItemInfo checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }

}