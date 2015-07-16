package com.baidu.jinbao.mall.item.vo;

import java.util.List;

public class PageItemVo {
    /**
     * shop_id list
     */
    private List<Long> shopIdList;
    
    /**
     * leafcategoryid list
     */
    private List<Long> leafcategoryidList;
    
    /**
     * manualStatus
     */
    private Byte manualStatus;
    
    /**
     * merchantStatus
     */
    private Byte merchantStatus;
    
    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 排序方式：0上架时间升序，1上架时间降序
     */
    private Integer rank;

    /**
     * 需要查询的field组成的字符串
     */
    private String field;
    
    /**
     * item总数
     */
    private Long totalNum;
    
    /**
     * 总页数
     */
    private Integer pages;
    
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

    public Byte getManualStatus() {
        return manualStatus;
    }

    public void setManualStatus(Byte manualStatus) {
        this.manualStatus = manualStatus;
    }

    public Byte getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(Byte merchantStatus) {
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
