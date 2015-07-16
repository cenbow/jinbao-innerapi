package com.baidu.jinbao.mall.item.vo;

import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public class PageItemTotalInfoVo {
    
    private ItemInfo itemInfo;

    private ItemAttribute itemAttribute;

    private ItemCdt itemCdt;

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public ItemAttribute getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(ItemAttribute itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    public ItemCdt getItemCdt() {
        return itemCdt;
    }

    public void setItemCdt(ItemCdt itemCdt) {
        this.itemCdt = itemCdt;
    }
}