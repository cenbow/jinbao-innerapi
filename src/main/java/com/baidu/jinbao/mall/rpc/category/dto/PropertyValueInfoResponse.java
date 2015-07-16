package com.baidu.jinbao.mall.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * CategoryProperty Response
 * 
 * @author cgd
 * @date 2015年7月2日 下午5:31:57
 */
public class PropertyValueInfoResponse {

    @Protobuf
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf
    private String message; // 返回信息

    @Protobuf(fieldType = FieldType.OBJECT, required = false)
    private List<PropertyValueInfoDto> propertyValueInfoList;

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

    public List<PropertyValueInfoDto> getPropertyValueInfoList() {
        return propertyValueInfoList;
    }

    public void setPropertyValueInfoList(List<PropertyValueInfoDto> propertyValueInfoList) {
        this.propertyValueInfoList = propertyValueInfoList;
    }

}
