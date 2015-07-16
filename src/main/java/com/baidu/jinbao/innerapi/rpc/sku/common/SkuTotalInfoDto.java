package com.baidu.jinbao.innerapi.rpc.sku.common;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCdtDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCommentDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCspuDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuDescriptionDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuLevelDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuPpsDto;

public class SkuTotalInfoDto {
    @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = true)
    private SkuInfoDto skuInfoDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 2, required = false)
    private SkuAttributeDto skuAttributeDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 3, required = false)
    private SkuCdtDto skuCdtDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 4, required = false)
    private SkuDescriptionDto skuDescriptionDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 5, required = false)
    private SkuPpsDto skuPpsDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 6, required = false)
    private SkuLevelDto skuLevelDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 7, required = false)
    private SkuCommentDto skuCommentDto;
    @Protobuf(fieldType = FieldType.OBJECT, order = 8, required = false)
    private SkuCspuDto skuCspuDto;
    
    public SkuInfoDto getSkuInfoDto() {
        return skuInfoDto;
    }
    public void setSkuInfoDto(SkuInfoDto skuInfoDto) {
        this.skuInfoDto = skuInfoDto;
    }
    public SkuAttributeDto getSkuAttributeDto() {
        return skuAttributeDto;
    }
    public void setSkuAttributeDto(SkuAttributeDto skuAttributeDto) {
        this.skuAttributeDto = skuAttributeDto;
    }
    public SkuCdtDto getSkuCdtDto() {
        return skuCdtDto;
    }
    public void setSkuCdt(SkuCdtDto skuCdtDto) {
        this.skuCdtDto = skuCdtDto;
    }
    public SkuDescriptionDto getSkuDescriptionDto() {
        return skuDescriptionDto;
    }
    public void setSkuDescriptionDto(SkuDescriptionDto skuDescriptionDto) {
        this.skuDescriptionDto = skuDescriptionDto;
    }
    public SkuPpsDto getSkuPpsDto() {
        return skuPpsDto;
    }
    public void setSkuPpsDto(SkuPpsDto skuPpsDto) {
        this.skuPpsDto = skuPpsDto;
    }
    public SkuLevelDto getSkuLevelDto() {
        return skuLevelDto;
    }
    public void setSkuLevelDto(SkuLevelDto skuLevelDto) {
        this.skuLevelDto = skuLevelDto;
    }
    public SkuCommentDto getSkuCommentDto() {
        return skuCommentDto;
    }
    public void setSkuCommentDto(SkuCommentDto skuCommentDto) {
        this.skuCommentDto = skuCommentDto;
    }
    public SkuCspuDto getSkuCspuDto() {
        return skuCspuDto;
    }
    public void setSkuCspuDto(SkuCspuDto skuCspuDto) {
        this.skuCspuDto = skuCspuDto;
    }
}
