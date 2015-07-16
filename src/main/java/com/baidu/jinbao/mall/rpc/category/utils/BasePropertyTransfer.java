package com.baidu.jinbao.mall.rpc.category.utils;

import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDto;

public class BasePropertyTransfer {

    public static BasePropertyDto transBoToDto(BaseProperty bo) {
        if (bo == null) {
            return null;
        }
        BasePropertyDto dto = new BasePropertyDto();
        dto.setId(bo.getId());
        dto.setEnName(bo.getEnName());
        dto.setPropertyName(bo.getPropertyName());
        return dto;

    }
}
