package com.baidu.jinbao.mall.rpc.category.utils;

import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDto;

public class BaseValTransfer {

    public static BaseValDto transBoToDto(BaseVal bo) {
        if (bo == null) {
            return null;
        }
        BaseValDto dto = new BaseValDto();
        dto.setId(bo.getId());
        dto.setValue(bo.getValue());
        return dto;

    }
}
