package com.baidu.jinbao.mall.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDto;

/**
 * PropertyValueInfo
 * 
 * @author cgd
 * @date 2015年7月3日 上午9:55:44
 */
public class PropertyValueInfoDto {

    @Protobuf(fieldType = FieldType.OBJECT)
    private CategoryPropertyValueDto categoryPropertyValueDto;

    @Protobuf(fieldType = FieldType.OBJECT)
    private BaseValDto baseValDto;

    public CategoryPropertyValueDto getCategoryPropertyValueDto() {
        return categoryPropertyValueDto;
    }

    public void setCategoryPropertyValueDto(CategoryPropertyValueDto categoryPropertyValueDto) {
        this.categoryPropertyValueDto = categoryPropertyValueDto;
    }

    public BaseValDto getBaseValDto() {
        return baseValDto;
    }

    public void setBaseValDto(BaseValDto baseValDto) {
        this.baseValDto = baseValDto;
    }

}
