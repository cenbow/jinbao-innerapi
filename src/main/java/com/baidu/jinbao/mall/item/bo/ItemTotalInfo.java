package com.baidu.jinbao.mall.item.bo;

import java.util.List;

public class ItemTotalInfo {
    private ItemInfo itemInfo;
    private List<MallSkuTotalInfo> mallSkuTotalInfoList;
    private ItemAttribute itemAttribute;

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

    public List<MallSkuTotalInfo> getMallSkuTotalInfoList() {
        return mallSkuTotalInfoList;
    }

    public void setMallSkuTotalInfoList(List<MallSkuTotalInfo> mallSkuTotalInfoList) {
        this.mallSkuTotalInfoList = mallSkuTotalInfoList;
    }

    public ItemAttribute getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(ItemAttribute itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    public List<ItemCdt> getItemCdtList() {
        return itemCdtList;
    }

    public void setItemCdtList(List<ItemCdt> itemCdtList) {
        this.itemCdtList = itemCdtList;
    }

    private List<ItemCdt> itemCdtList;

}
