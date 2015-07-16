package com.baidu.jinbao.innerapi.sku.vo;

import java.util.List;

/**
 * sku表相关查询条件
 * 
 * @author cgd
 * @date 2015年6月11日 下午9:43:56
 */
public class SkuQueryCondition {

    private List<Long> idList; // 主键ID List
    private List<Long> skuInnerIdList; // sku表主键ID List
    private List<String> skuIdList; // sku id List(商家ID+outerId)

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getSkuInnerIdList() {
        return skuInnerIdList;
    }

    public void setSkuInnerIdList(List<Long> skuInnerIdList) {
        this.skuInnerIdList = skuInnerIdList;
    }

    public List<String> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<String> skuIdList) {
        this.skuIdList = skuIdList;
    }

}
