package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MerchantRouterDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<MerchantRouterDto> dtoList;

    public List<MerchantRouterDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<MerchantRouterDto> dtoList) {
        this.dtoList = dtoList;
    }

}
