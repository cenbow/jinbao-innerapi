package com.baidu.jinbao.mall.rpc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.rpc.item.dto.ItemAttributeDto;

public class ItemAttributeTransfer {

    public static ItemAttribute transferToBo(ItemAttributeDto dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemAttribute bo = new ItemAttribute();
        if (dto.getId() != null) {
            bo.setId(dto.getId());
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getPropertyId() != null) {
            bo.setPropertyId(dto.getPropertyId());
        }
        if (dto.getPropertyValues() != null) {
            bo.setPropertyValues(dto.getPropertyValues());
        }
        if (dto.getPropertyMd5() != null) {
            bo.setPropertyMd5(dto.getPropertyMd5());
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        return bo;
    }

    public static ItemAttributeDto transferToDto(ItemAttribute bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemAttributeDto dto = new ItemAttributeDto();
        dto.setId(bo.getId());
        dto.setItemid(bo.getItemid());
        dto.setPropertyId(bo.getPropertyId());
        dto.setPropertyValues(bo.getPropertyValues());
        dto.setPropertyMd5(bo.getPropertyMd5());
        dto.setAddtime(sdf.format((bo.getAddtime())));
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        return dto;
    }
}
