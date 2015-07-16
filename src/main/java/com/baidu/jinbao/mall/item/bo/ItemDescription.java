package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemDescription extends ItemDescriptionBase {

    private static final long serialVersionUID = -2924301325061734683L;

    public ItemDescription checkMerchantId() {
        if (this.getMerchantid() == null) {
            throw new RuntimeException("merchantid not found!");
        }
        return this;
    }

    public ItemDescription checkShopId() {
        if (this.getShopid() == null) {
            throw new RuntimeException("ShopId not found!");
        }
        return this;
    }

    public ItemDescription checkItemId() {
        if (this.getItemid() == null) {
            throw new RuntimeException("ItemId not found!");
        }
        return this;
    }

    public ItemDescription checkItemDescOri() {
        if (this.getItemDescOri() == null) {
            throw new RuntimeException("ItemDescOri not found!");
        }
        return this;
    }

    public ItemDescription checkItemDesc() {
        if (this.getItemDesc() == null) {
            throw new RuntimeException("ItemDesc not found!");
        }
        return this;
    }

    public ItemDescription checkDefault() {
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
    public ItemDescription checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}