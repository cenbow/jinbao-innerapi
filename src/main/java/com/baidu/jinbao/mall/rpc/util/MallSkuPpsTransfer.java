package com.baidu.jinbao.mall.rpc.util;

import java.text.SimpleDateFormat;

import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDto;

public class MallSkuPpsTransfer {
    public static MallSkuPps transferToBo(MallSkuPpsDto dto) throws Exception {
        MallSkuPps bo = new MallSkuPps();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (dto.getId() != null) {
            bo.setId(dto.getId());
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getDiscountPrice() != null) {
            bo.setDiscountPrice(dto.getDiscountPrice().toString());
        }
        if (dto.getFlashPrice() != null) {
            bo.setInventoryWarning(dto.getInventoryWarning());
        }
        if (dto.getMerchantid() != null) {
            bo.setMerchantid(dto.getMerchantid());
        }

        if (dto.getMUpdateTime() != null) {
            bo.setMUpdateTime(sdf.parse(dto.getMUpdateTime()));
        }
        if (dto.getPcMobile() != null) {
            bo.setPcMobile(dto.getPcMobile().byteValue());
        }
        if (dto.getPostPay() != null) {
            bo.setPostPay(dto.getPostPay().byteValue());
        }
        if (dto.getPpsMd5() != null) {
            bo.setPpsMd5(dto.getPpsMd5());
        }
        if (dto.getPrice() != null) {
            bo.setPrice(dto.getPrice().toString());
        }
        if (dto.getPromotionType() != null) {
            bo.setPromotionType(dto.getPromotionType().byteValue());
        }
        if (dto.getRegionid() != null) {
            bo.setRegionid(dto.getRegionid());
        }
        if (dto.getShopid() != null) {
            bo.setShopid(dto.getShopid());
        }
        if (dto.getSkuid() != null) {
            bo.setSkuid(dto.getSkuid());
        }
        if (dto.getStock() != null) {
            bo.setStock(dto.getStock());
        }
        if (dto.getTicketPrice() != null) {
            bo.setTicketPrice(dto.getTicketPrice().toString());
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }

        return bo;
    }

    public static MallSkuPpsDto transferToDto(MallSkuPps bo) throws Exception {
        MallSkuPpsDto dto = new MallSkuPpsDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        dto.setId(bo.getId());
        dto.setAddtime(sdf.format(bo.getAddtime()));
        dto.setDiscountPrice(Float.valueOf(bo.getDiscountPrice().toString()));
        dto.setInventoryWarning(bo.getInventoryWarning());
        dto.setMerchantid(bo.getMerchantid());

        dto.setMUpdateTime(sdf.format(bo.getMUpdateTime()));
        dto.setPcMobile(Integer.valueOf(bo.getPcMobile()));
        dto.setPostPay(Integer.valueOf(bo.getPostPay()));
        dto.setPpsMd5(bo.getPpsMd5());
        dto.setPrice(Float.valueOf(bo.getPrice().toString()));
        dto.setPromotionType(Integer.valueOf(bo.getPromotionType()));
        dto.setRegionid(bo.getRegionid());
        dto.setShopid(bo.getShopid());
        dto.setSkuid(bo.getSkuid());
        dto.setStock(bo.getStock());
        dto.setTicketPrice(Float.valueOf(bo.getTicketPrice().toString()));
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));

        return dto;
    }
}
