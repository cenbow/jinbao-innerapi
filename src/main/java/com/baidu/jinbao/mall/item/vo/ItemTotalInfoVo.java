package com.baidu.jinbao.mall.item.vo;

import java.util.List;

import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public class ItemTotalInfoVo {
    
    private ItemInfo itemInfo;

    private ItemAttribute itemAttribute;

    private ItemDescription itemDescription;

    private List<ItemCdt> itemCdtList;

    private List<MallSkuTotalInfoVo> mallSkuTotalInfoList;

    private List<BcsImage> bcsImageList;

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

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

    public List<ItemCdt> getItemCdtList() {
        return itemCdtList;
    }

    public void setItemCdtList(List<ItemCdt> itemCdtList) {
        this.itemCdtList = itemCdtList;
    }

    public List<MallSkuTotalInfoVo> getMallSkuTotalInfoList() {
        return mallSkuTotalInfoList;
    }

    public void setMallSkuTotalInfoList(List<MallSkuTotalInfoVo> mallSkuTotalInfoList) {
        this.mallSkuTotalInfoList = mallSkuTotalInfoList;
    }

    public List<BcsImage> getBcsImageList() {
        return bcsImageList;
    }

    public void setBcsImageList(List<BcsImage> bcsImageList) {
        this.bcsImageList = bcsImageList;
    }
}
