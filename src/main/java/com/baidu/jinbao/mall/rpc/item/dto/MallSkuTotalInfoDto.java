package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MallSkuTotalInfoDto {

    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = false)
    private MallSkuInfoDto mallSkuInfoDto;

    @Protobuf(fieldType = FieldType.OBJECT, order = 2, required = false)
    private List<MallSkuPpsDto> mallSkuPpsDtoList;

    public MallSkuInfoDto getMallSkuInfoDto() {
        return mallSkuInfoDto;
    }

    public void setMallSkuInfoDto(MallSkuInfoDto mallSkuInfoDto) {
        this.mallSkuInfoDto = mallSkuInfoDto;
    }

    public List<MallSkuPpsDto> getMallSkuPpsDtoList() {
        return mallSkuPpsDtoList;
    }

    public void setMallSkuPpsDtoList(List<MallSkuPpsDto> mallSkuPpsDtoList) {
        this.mallSkuPpsDtoList = mallSkuPpsDtoList;
    }

}
