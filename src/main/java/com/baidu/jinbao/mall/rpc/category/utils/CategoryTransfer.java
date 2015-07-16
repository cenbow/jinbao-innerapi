package com.baidu.jinbao.mall.rpc.category.utils;

import java.text.SimpleDateFormat;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDto;

public class CategoryTransfer {

    public static CategoryDto transBoToDto(Category bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CategoryDto dto = new CategoryDto();
        dto.setCategoryid(bo.getCategoryid());
        dto.setParentid(bo.getParentid());
        if (bo.getIsleaf() != null) {
            dto.setIsleaf(bo.getIsleaf() & 0xff);
        }
        dto.setName(bo.getName());
        dto.setPos(bo.getPos());
        if (bo.getDeleted() != null) {
            dto.setDeleted(bo.getDeleted() & 0xff);
        }
        if (bo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(bo.getUpdatetime()));
        }
        if (bo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(bo.getAddtime()));
        }
        return dto;
    }
}
