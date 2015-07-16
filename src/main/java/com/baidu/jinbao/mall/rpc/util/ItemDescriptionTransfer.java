package com.baidu.jinbao.mall.rpc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.rpc.item.dto.ItemDescriptionDto;

public class ItemDescriptionTransfer {

    public static ItemDescription transferToBo(ItemDescriptionDto dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemDescription bo = new ItemDescription();
        if (dto.getId() != null) {
            bo.setId(dto.getId());
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getMerchantid() != null) {
            bo.setMerchantid(dto.getMerchantid());
        }
        if (dto.getShopid() != null) {
            bo.setShopid(dto.getShopid());
        }
        if (dto.getItemDescOri() != null) {
            bo.setItemDescOri(dto.getItemDescOri());
        }
        if (dto.getPdMd5() != null) {
            bo.setPdMd5(dto.getPdMd5());
        }
        if (dto.getItemDesc() != null) {
            bo.setItemDesc(dto.getItemDesc());
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        return bo;
    }

    public static ItemDescriptionDto transferToDto(ItemDescription bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemDescriptionDto dto = new ItemDescriptionDto();
        dto.setId(bo.getId());
        dto.setItemid(bo.getItemid());
        dto.setMerchantid(bo.getMerchantid());
        dto.setShopid(bo.getShopid());
        dto.setItemDescOri(bo.getItemDescOri());
        dto.setPdMd5(bo.getPdMd5());
        dto.setItemDesc(bo.getItemDesc());
        dto.setAddtime(sdf.format((bo.getAddtime())));
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        return dto;
    }
}
