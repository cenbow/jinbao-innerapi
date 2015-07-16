package com.baidu.jinbao.mall.item.vo;

import java.util.List;

public class ItemTotalInfoInsertVo {

    private Long itemId; // 插入info的itemId

    private List<Long> skuIdList; // 插入info的skuIdList

    private List<Long> imageIdList; // 插入info的imageIdList

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<Long> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<Long> skuIdList) {
        this.skuIdList = skuIdList;
    }

    public List<Long> getImageIdList() {
        return imageIdList;
    }

    public void setImageIdList(List<Long> imageIdList) {
        this.imageIdList = imageIdList;
    }

}
