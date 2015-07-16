/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.jinbaocommon.bo;

import java.util.Date;

public class MerchantRouterBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * DB分片信息
     */
    private String usedShards;

    /**
     * 标识商家是否是大小商家: 0小商家，1中商家，2大商家
     */
    private Byte merchantAmountType;

    /**
     * 当前路由的状态：0正常，1扩容中
     */
    private Byte merchantRouterStat;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 自增id
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 自增id
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 商家ID
     * 
     * @param merchantId the value for merchant_id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 商家ID
     * 
     * @return merchantId the value for merchant_id
     */
    public Long getMerchantId() {
        return this.merchantId;
    }

    /**
     * DB分片信息
     * 
     * @param usedShards the value for used_shards
     */
    public void setUsedShards(String usedShards) {
        this.usedShards = usedShards;
    }

    /**
     * DB分片信息
     * 
     * @return usedShards the value for used_shards
     */
    public String getUsedShards() {
        return this.usedShards;
    }

    /**
     * 标识商家是否是大小商家: 0小商家，1中商家，2大商家
     * 
     * @param merchantAmountType the value for merchant_amount_type
     */
    public void setMerchantAmountType(Byte merchantAmountType) {
        this.merchantAmountType = merchantAmountType;
    }

    /**
     * 标识商家是否是大小商家: 0小商家，1中商家，2大商家
     * 
     * @return merchantAmountType the value for merchant_amount_type
     */
    public Byte getMerchantAmountType() {
        return this.merchantAmountType;
    }

    /**
     * 当前路由的状态：0正常，1扩容中
     * 
     * @param merchantRouterStat the value for merchant_router_stat
     */
    public void setMerchantRouterStat(Byte merchantRouterStat) {
        this.merchantRouterStat = merchantRouterStat;
    }

    /**
     * 当前路由的状态：0正常，1扩容中
     * 
     * @return merchantRouterStat the value for merchant_router_stat
     */
    public Byte getMerchantRouterStat() {
        return this.merchantRouterStat;
    }

    /**
     * 添加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
     * 
     * @return addtime the value for addtime
     */
    public Date getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public Date getUpdatetime() {
        return this.updatetime;
    }
}
