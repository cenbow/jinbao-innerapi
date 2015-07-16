package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * SkuPpsCondition
 * 
 * @author cgd
 * @date 2015年7月3日 下午4:04:12
 */
public class SkuPpsCondition {

    @Protobuf
    private Long skuId;

    @Protobuf
    private Integer regionId;

    @Protobuf
    private Integer pcMobile;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getPcMobile() {
        return pcMobile;
    }

    public void setPcMobile(Integer pcMobile) {
        this.pcMobile = pcMobile;
    }

}
