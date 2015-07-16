package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCspuDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品shard内部id
     */
    @Protobuf(order = 2)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 3)
    private String skuid;

    /**
     * Cspu ID
     */
    @Protobuf(order = 4)
    private Long cspuid;

    /**
     * cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习
     */
    @Protobuf(order = 5)
    private Integer type;

    /**
     * 取值0-100，可信度
     */
    @Protobuf(order = 6)
    private Float confidence;

    /**
     * 添加时间
     */
    @Protobuf(order = 7)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 8)
    private String updatetime;

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
     * 商品shard内部id
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * 商品shard内部id
     * 
     * @return skuInnerid the value for sku_innerid
     */
    public Long getSkuInnerid() {
        return this.skuInnerid;
    }

    /**
     * 商品全局唯一标识id
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    /**
     * 商品全局唯一标识id
     * 
     * @return skuid the value for skuid
     */
    public String getSkuid() {
        return this.skuid;
    }

    /**
     * Cspu ID
     * 
     * @param cspuid the value for cspuid
     */
    public void setCspuid(Long cspuid) {
        this.cspuid = cspuid;
    }

    /**
     * Cspu ID
     * 
     * @return cspuid the value for cspuid
     */
    public Long getCspuid() {
        return this.cspuid;
    }

    /**
     * cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习
     * 
     * @param type the value for type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * cspu来源:1:MANUAL_CLASSIFICATION：手工修改，2:MACHINE_CLASSIFICATION：机器学习
     * 
     * @return type the value for type
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 取值0-100，可信度
     * 
     * @param confidence the value for confidence
     */
    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    /**
     * 取值0-100，可信度
     * 
     * @return confidence the value for confidence
     */
    public Float getConfidence() {
        return this.confidence;
    }

    /**
     * 添加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
     * 
     * @return addtime the value for addtime
     */
    public String getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
}
