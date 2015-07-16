package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * BcsImageInsertResponse
 * 
 * @author cgd
 * @date 2015年7月3日 下午3:54:38
 */
public class BcsImageInsertResponse {

    @Protobuf
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf
    private String message; // 返回信息

    @Protobuf
    private Long bcsImageId; // 插入记录对应的主键ID

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

    public Long getBcsImageId() {
        return bcsImageId;
    }

    public void setBcsImageId(Long bcsImageId) {
        this.bcsImageId = bcsImageId;
    }

}
