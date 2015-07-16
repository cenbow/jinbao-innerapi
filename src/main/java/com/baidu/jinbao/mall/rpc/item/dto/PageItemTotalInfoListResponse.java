package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PageItemTotalInfoListResponse {
    @Protobuf(order = 1)
    private Integer status; // 查询状态 0:成功，1:失败

    @Protobuf(order = 2)
    private String message; // 返回信息

    /**
     * 总数
     */
    @Protobuf(order = 3)
    private Long totalNum;
    /**
     * 每页数量
     */
    @Protobuf(order = 4)
    private Integer pageSize;

    /**
     * 页码
     */
    @Protobuf(order = 5)
    private Integer pageNumber;

    /**
     * 总页数
     */
    @Protobuf(order = 6)
    private Integer pages;

    @Protobuf(fieldType = FieldType.OBJECT, order = 7, required = false)
    private List<PageItemTotalInfoDto> dtoList; // 返回数据List

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

    public List<PageItemTotalInfoDto> getDtoList() {
        return dtoList;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setDtoList(List<PageItemTotalInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
