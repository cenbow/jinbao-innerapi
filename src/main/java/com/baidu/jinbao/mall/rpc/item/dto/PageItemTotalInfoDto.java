package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PageItemTotalInfoDto {
    
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private ItemInfoDto itemInfoDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 2, required = false)
    private ItemAttributeDto itemAttributeDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 3, required = false)
    private ItemCdtDto itemCdtDto;

    public ItemInfoDto getItemInfoDto() {
        return itemInfoDto;
    }

    public void setItemInfoDto(ItemInfoDto itemInfoDto) {
        this.itemInfoDto = itemInfoDto;
    }

    public ItemAttributeDto getItemAttributeDto() {
        return itemAttributeDto;
    }

    public void setItemAttributeDto(ItemAttributeDto itemAttributeDto) {
        this.itemAttributeDto = itemAttributeDto;
    }

    public ItemCdtDto getItemCdtDto() {
        return itemCdtDto;
    }

    public void setItemCdtDto(ItemCdtDto itemCdtDto) {
        this.itemCdtDto = itemCdtDto;
    }
}