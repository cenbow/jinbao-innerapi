package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemTotalInfoDto {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private ItemInfoDto itemInfoDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 2, required = false)
    private ItemAttributeDto itemAttributeDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 3, required = false)
    private ItemDescriptionDto itemDescriptionDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 4, required = false)
    private List<ItemCdtDto> itemCdtDtoList;

    @Protobuf(fieldType = FieldType.OBJECT, order = 5, required = false)
    private List<MallSkuTotalInfoDto> mallSkuTotalInfoDtoList;

    @Protobuf(fieldType = FieldType.OBJECT, order = 6, required = false)
    private List<BcsImageDto> bcsImageDtoList;

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

    public List<ItemCdtDto> getItemCdtDtoList() {
        return itemCdtDtoList;
    }

    public void setItemCdtDtoList(List<ItemCdtDto> itemCdtDtoList) {
        this.itemCdtDtoList = itemCdtDtoList;
    }

    public ItemDescriptionDto getItemDescriptionDto() {
        return itemDescriptionDto;
    }

    public void setItemDescriptionDto(ItemDescriptionDto itemDescriptionDto) {
        this.itemDescriptionDto = itemDescriptionDto;
    }

    public List<BcsImageDto> getBcsImageDtoList() {
        return bcsImageDtoList;
    }

    public void setBcsImageDtoList(List<BcsImageDto> bcsImageDtoList) {
        this.bcsImageDtoList = bcsImageDtoList;
    }

    public List<MallSkuTotalInfoDto> getMallSkuTotalInfoDtoList() {
        return mallSkuTotalInfoDtoList;
    }

    public void setMallSkuTotalInfoDtoList(List<MallSkuTotalInfoDto> mallSkuTotalInfoDtoList) {
        this.mallSkuTotalInfoDtoList = mallSkuTotalInfoDtoList;
    }
}