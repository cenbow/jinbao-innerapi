package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * BcsImageId
 * 
 * @author cgd
 * @date 2015年7月3日 下午3:52:04
 */
public class BcsImageCondition {

    @Protobuf(fieldType = FieldType.INT64)
    private List<Long> idList;

    @Protobuf(fieldType = FieldType.INT64)
    private List<Long> itemIdList;
    
    @Protobuf(fieldType = FieldType.INT64)
    private List<Long> skuIdList;
    

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List<Long> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public List<Long> getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(List<Long> skuIdList) {
        this.skuIdList = skuIdList;
    }

}
