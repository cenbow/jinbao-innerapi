package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class MerchantRouterDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Long merchantId;

    @Protobuf(order = 3)
    private String usedShards;

    @Protobuf(order = 4)
    private Integer merchantAmountType;

    @Protobuf(order = 5)
    private Integer merchantRouterStat;

    @Protobuf(order = 6)
    private String addtime;

    @Protobuf(order = 7)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getUsedShards() {
        return usedShards;
    }

    public void setUsedShards(String usedShards) {
        this.usedShards = usedShards;
    }

    public Integer getMerchantAmountType() {
        return merchantAmountType;
    }

    public void setMerchantAmountType(Integer merchantAmountType) {
        this.merchantAmountType = merchantAmountType;
    }

    public Integer getMerchantRouterStat() {
        return merchantRouterStat;
    }

    public void setMerchantRouterStat(Integer merchantRouterStat) {
        this.merchantRouterStat = merchantRouterStat;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
