package com.baidu.jinbao.mall.rpc.category.utils;

import java.text.SimpleDateFormat;

import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDto;

public class CategoryPropertyValueTransfer {

    public static CategoryPropertyValueDto transBoToDto(CategoryPropertyValue bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CategoryPropertyValueDto dto = new CategoryPropertyValueDto();
        dto.setAlias(bo.getAlias());
        dto.setcId(bo.getCId());
        dto.setId(bo.getId());
        dto.setImageUrl(bo.getImageUrl());
        if (bo.getIsleaf() != null) {
            dto.setIsleaf(bo.getIsleaf() & 0xff);
        }
        dto.setPid(bo.getPid());
        dto.setVid(bo.getVid());
        if (bo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(bo.getAddtime()));
        }
        if (bo.getActive() != null) {
            dto.setActive(bo.getActive() & 0xff);
        }
        if (bo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(bo.getUpdatetime()));
        }
        return dto;
    }
}
