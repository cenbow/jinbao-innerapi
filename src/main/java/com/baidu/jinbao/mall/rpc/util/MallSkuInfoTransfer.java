package com.baidu.jinbao.mall.rpc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuInfoDto;

public class MallSkuInfoTransfer {
    public static MallSkuInfo transferToBo(MallSkuInfoDto dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        MallSkuInfo bo = new MallSkuInfo();
        if (dto.getSkuid() != null) {
            bo.setSkuid(dto.getSkuid());
        }
        if (dto.getSkuType() != null) {
            bo.setSkuType(dto.getSkuType().byteValue());
        }
        if (dto.getSellAttribute() != null) {
            bo.setSellAttribute(dto.getSellAttribute());
        }
        if (dto.getPropertyValues() != null) {
            bo.setPropertyValues(dto.getPropertyValues());
        }
        if (dto.getUpc() != null) {
            bo.setUpc(dto.getUpc());
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getDeleted() != null) {
            bo.setDeleted(dto.getDeleted().byteValue());
        }
        if (dto.getWeight() != null) {
            bo.setWeight(dto.getWeight());
        }
        if (dto.getVolume() != null) {
            bo.setVolume(dto.getVolume());
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        if (dto.getOuterid() != null) {
            bo.setOuterid(dto.getOuterid());
        }
        return bo;
    }

    public static MallSkuInfoDto transferToDto(MallSkuInfo bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        MallSkuInfoDto dto = new MallSkuInfoDto();
        dto.setSkuid(bo.getSkuid());
        dto.setSkuType(Integer.valueOf(bo.getSkuType()));
        dto.setSellAttribute(bo.getSellAttribute());
        dto.setPropertyValues(bo.getPropertyValues());
        dto.setUpc(bo.getUpc());
        dto.setItemid(bo.getItemid());
        dto.setDeleted(Integer.valueOf(bo.getDeleted()));
        dto.setWeight(Float.valueOf(bo.getWeight().toString()));
        dto.setVolume(Float.valueOf(bo.getVolume().toString()));
        dto.setAddtime(sdf.format((bo.getAddtime())));
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        dto.setOuterid(bo.getOuterid());
        return dto;
    }
}
