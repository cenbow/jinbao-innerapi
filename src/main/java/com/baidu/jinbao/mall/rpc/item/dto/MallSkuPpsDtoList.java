package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MallSkuPpsDtoList {
    @Protobuf(fieldType = FieldType.OBJECT, required = false)
    private List<MallSkuPpsDto> mallSkuPpsDtoList;

    public List<MallSkuPpsDto> getMallSkuPpsDtoList() {
        return mallSkuPpsDtoList;
    }

    public void setMallSkuPpsDtoList(List<MallSkuPpsDto> mallSkuPpsDtoList) {
        this.mallSkuPpsDtoList = mallSkuPpsDtoList;
    }
}
