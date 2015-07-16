package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuCdtDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品Shard内商品id
     */
    @Protobuf(order = 2)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 3)
    private String skuid;

    /**
     * 叶子类目ID
     */
    @Protobuf(order = 4)
    private Long leafcategoryid;

    /**
     * 类目来源 0：手工修改，1：机器学习，其他状态
     */
    @Protobuf(order = 5)
    private Integer classificationtype;

    /**
     * 取值0-100，可信度(目前没在用)
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
     * 商品Shard内商品id
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * 商品Shard内商品id
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
     * 叶子类目ID
     * 
     * @param leafcategoryid the value for leafcategoryid
     */
    public void setLeafcategoryid(Long leafcategoryid) {
        this.leafcategoryid = leafcategoryid;
    }

    /**
     * 叶子类目ID
     * 
     * @return leafcategoryid the value for leafcategoryid
     */
    public Long getLeafcategoryid() {
        return this.leafcategoryid;
    }

    /**
     * 类目来源 0：手工修改，1：机器学习，其他状态
     * 
     * @param classificationtype the value for classificationtype
     */
    public void setClassificationtype(Integer classificationtype) {
        this.classificationtype = classificationtype;
    }

    /**
     * 类目来源 0：手工修改，1：机器学习，其他状态
     * 
     * @return classificationtype the value for classificationtype
     */
    public Integer getClassificationtype() {
        return this.classificationtype;
    }

    /**
     * 取值0-100，可信度(目前没在用)
     * 
     * @param confidence the value for confidence
     */
    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    /**
     * 取值0-100，可信度(目前没在用)
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
