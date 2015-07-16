package com.baidu.jinbao.mall.rpc.util;

import java.text.SimpleDateFormat;

import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.rpc.item.dto.ItemInfoDto;

public class ItemInfoTransfer {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static ItemInfo transferToBo(ItemInfoDto dto) throws Exception {
        ItemInfo bo = new ItemInfo();

        bo.setItemid(dto.getItemid());
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getBrandOri() != null) {
            bo.setBrandOri(dto.getBrandOri());
        }

        if (dto.getCategoryOri() != null) {
            bo.setCategoryOri(dto.getBrandOri());
        }
        if (dto.getDataversion() != null) {
            bo.setDataversion(dto.getDataversion());
        }
        if (dto.getDeleted() != null) {
            bo.setDeleted(Byte.valueOf(dto.getDeleted().toString()));
        }
        if (dto.getEndtime() != null) {
            bo.setEndtime(sdf.parse(dto.getEndtime()));
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getManualStatus() != null) {
            bo.setManualStatus(Byte.valueOf(dto.getManualStatus().toString()));
        }
        if (dto.getMerchantid() != null) {
            bo.setMerchantid(dto.getMerchantid());
        }
        if (dto.getMerchantStatus() != null) {
            bo.setMerchantStatus(Byte.valueOf(dto.getMerchantStatus().toString()));
        }
        if (dto.getOuterid() != null) {
            bo.setOuterid(dto.getOuterid());
        }
        if (dto.getShopcategory() != null) {
            bo.setShopcategory(dto.getShopcategory());
        }
        if (dto.getShopid() != null) {
            bo.setShopid(dto.getShopid());
        }
        if (dto.getStarttime() != null) {
            bo.setStarttime(sdf.parse(dto.getStarttime()));
        }
        if (dto.getSubtitle() != null) {
            bo.setSubtitle(dto.getSubtitle());
        }
        if (dto.getTitle() != null) {
            bo.setTitle(dto.getTitle());
        }
        if (dto.getUcid() != null) {
            bo.setUcid(dto.getUcid());
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        if (dto.getUrl() != null) {
            bo.setUrl(dto.getUrl());
        }
        if (dto.getWareBigSmallModel() != null) {
            bo.setWareBigSmallModel(dto.getWareBigSmallModel());
        }
        if (dto.getWarePackType() != null) {
            bo.setWarePackType(dto.getWarePackType());
        }
        return bo;
    }

    public static ItemInfoDto transferToDto(ItemInfo bo) throws Exception {
        ItemInfoDto dto = new ItemInfoDto();
        dto.setAddtime(sdf.format(bo.getAddtime()));
        dto.setBrandOri(bo.getBrandOri());
        dto.setCategoryOri(bo.getCategoryOri());
        dto.setDataversion(bo.getDataversion());
        dto.setDeleted(bo.getDeleted() & 0xff);
        dto.setEndtime(sdf.format(bo.getEndtime()));
        dto.setItemid(bo.getItemid());
        dto.setManualStatus(bo.getManualStatus() & 0xff);
        dto.setMerchantid(bo.getMerchantid());
        dto.setMerchantStatus(bo.getMerchantStatus() & 0xff);
        dto.setOuterid(bo.getOuterid());
        dto.setShopcategory(bo.getShopcategory());
        dto.setShopid(bo.getShopid());
        dto.setStarttime(sdf.format(bo.getStarttime()));
        dto.setSubtitle(bo.getSubtitle());
        dto.setTitle(bo.getTitle());
        dto.setUcid(bo.getUcid());
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        dto.setUrl(bo.getUrl());
        dto.setWareBigSmallModel(bo.getWareBigSmallModel());
        dto.setWarePackType(bo.getWarePackType());

        return dto;
    }
}
