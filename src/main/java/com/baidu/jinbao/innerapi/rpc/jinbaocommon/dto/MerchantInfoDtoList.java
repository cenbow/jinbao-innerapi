package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MerchantInfoDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private List<MerchantInfoDto> dtoList;

    public List<MerchantInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<MerchantInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
