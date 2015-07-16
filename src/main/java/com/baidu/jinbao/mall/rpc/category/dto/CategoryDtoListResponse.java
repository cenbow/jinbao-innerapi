package com.baidu.jinbao.mall.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDto;

/**
 * CategoryDtoListResponse
 * 
 * @author cgd
 * @date 2015年7月2日 下午5:08:22
 */
public class CategoryDtoListResponse {

    @Protobuf
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf
    private String message; // 返回信息

    @Protobuf(fieldType = FieldType.OBJECT)
    private List<CategoryDto> categoryDtoList; // 返回数据List

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

}
