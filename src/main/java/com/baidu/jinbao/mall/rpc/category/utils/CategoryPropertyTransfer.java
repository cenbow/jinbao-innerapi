package com.baidu.jinbao.mall.rpc.category.utils;

import java.text.SimpleDateFormat;

import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDto;

public class CategoryPropertyTransfer {

    public static CategoryPropertyDto transBoToDto(CategoryProperty bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CategoryPropertyDto dto = new CategoryPropertyDto();
        dto.setId(bo.getId());
        if (bo.getActive() != null) {
            dto.setActive(bo.getActive() & 0xff);
        }
        if (bo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(bo.getAddtime()));
        }
        dto.setcId(bo.getCId());
        if (bo.getIsleaf() != null) {
            dto.setIsleaf(bo.getIsleaf() & 0xff);
        }
        if (bo.getIsRequired() != null) {
            dto.setIsRequired(bo.getIsRequired() & 0xff);
        }
        if (bo.getIsSelfdefine() != null) {
            dto.setIsSelfdefine(bo.getIsSelfdefine() & 0xff);
        }
        dto.setName(bo.getName());
        dto.setPid(bo.getPid());
        if (bo.getPropType() != null) {
            dto.setPropType(bo.getPropType() & 0xff);
        }
        dto.setSequence(bo.getSequence());
        if (bo.getType() != null) {
            dto.setType(bo.getType() & 0xff);
        }
        dto.setValueRange(bo.getValueRange());
        return dto;

    }
}
