package com.baidu.jinbao.innerapi.rpc.base;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * RPC调用中更改的返回结果数据类型
 * 
 * @author cgd
 * @date 2015年6月11日 下午4:32:19
 */
public class ModifyResponse {

    @Protobuf(order = 1)
    private Integer status; // 标志位 0:成功，1：失败

    @Protobuf(order = 2)
    private String message; // 描述信息

    @Protobuf(order = 3)
    private Integer successNum; // 更改成功记录数

    @Protobuf(order = 4)
    private Integer failedNum; // 更改失败记录数

    // -------------------------------------------------
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

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(Integer failedNum) {
        this.failedNum = failedNum;
    }

}
