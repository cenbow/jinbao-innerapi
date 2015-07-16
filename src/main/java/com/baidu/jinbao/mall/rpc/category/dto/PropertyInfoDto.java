package com.baidu.jinbao.mall.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDto;

/**
 * PropertyInfo
 * 
 * @author cgd
 * @date 2015年7月2日 下午5:43:15
 */
public class PropertyInfoDto {

    @Protobuf(fieldType = FieldType.OBJECT)
    private CategoryPropertyDto categoryPropertyDto;

    @Protobuf(fieldType = FieldType.OBJECT)
    private BasePropertyDto basePropertyDto;

    public CategoryPropertyDto getCategoryPropertyDto() {
        return categoryPropertyDto;
    }

    public void setCategoryPropertyDto(CategoryPropertyDto categoryPropertyDto) {
        this.categoryPropertyDto = categoryPropertyDto;
    }

    public BasePropertyDto getBasePropertyDto() {
        return basePropertyDto;
    }

    public void setBasePropertyDto(BasePropertyDto basePropertyDto) {
        this.basePropertyDto = basePropertyDto;
    }

}
