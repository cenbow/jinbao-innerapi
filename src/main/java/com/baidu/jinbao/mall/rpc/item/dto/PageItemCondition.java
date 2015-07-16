package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PageItemCondition {
    /**
     * shop_id list
     */
    @Protobuf(fieldType = FieldType.INT64, order = 1, required = true)
    private List<Long> shopIdList;
    
    /**
     * leafcategoryid list
     */
    @Protobuf(fieldType = FieldType.INT64, order = 2, required = false)
    private List<Long> leafcategoryidList;
    
    /**
     * manualStatus
     */
    @Protobuf(order = 3, required = false)
    private Integer manualStatus;
    
    /**
     * merchantStatus
     */
    @Protobuf(order = 4, required = false)
    private Integer merchantStatus;
    
    /**
     * 每页数量
     */
    @Protobuf(order = 5, required = true)
    private Integer pageSize;

    /**
     * 页码
     */
    @Protobuf(order = 6, required = true)
    private Integer pageNumber;

    /**
     * 排序方式: 0上架时间升序，1上架时间降序
     */
    @Protobuf(order = 7, required = true)
    private Integer rank;

    /**
     * 需要查询的field Id List
     */
    @Protobuf(fieldType = FieldType.STRING, order = 8, required = false)
    private List<String> fieldList;

    public List<Long> getShopIdList() {
        return shopIdList;
    }

    public void setShopIdList(List<Long> shopIdList) {
        this.shopIdList = shopIdList;
    }

    public List<Long> getLeafcategoryidList() {
        return leafcategoryidList;
    }

    public void setLeafcategoryidList(List<Long> leafcategoryidList) {
        this.leafcategoryidList = leafcategoryidList;
    }

    public Integer getManualStatus() {
        return manualStatus;
    }

    public void setManualStatus(Integer manualStatus) {
        this.manualStatus = manualStatus;
    }

    public Integer getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

}
