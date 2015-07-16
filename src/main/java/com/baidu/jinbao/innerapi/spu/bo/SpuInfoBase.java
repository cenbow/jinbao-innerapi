/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.spu.bo;

import java.util.Date;

public class SpuInfoBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long spuid;

    /**
     * 后台类目ID
     */
    private Long cid;

    /**
     * spu名称:比如 诺基亚n97 手机
     */
    private String name;

    /**
     * 品牌：比如诺基亚
     */
    private Long brand;

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     */
    private String productModel;

    /**
     * 0：正常;1：已删除，会移出表
     */
    private Byte deleted;

    /**
     * 是否有效,1有效，0无效
     */
    private Byte active;

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
     * @param spuid the value for spuid
     */
    public void setSpuid(Long spuid) {
        this.spuid = spuid;
    }

    /**
     * 自增id
     * 
     * @return spuid the value for spuid
     */
    public Long getSpuid() {
        return this.spuid;
    }

    /**
     * 后台类目ID
     * 
     * @param cid the value for cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 后台类目ID
     * 
     * @return cid the value for cid
     */
    public Long getCid() {
        return this.cid;
    }

    /**
     * spu名称:比如 诺基亚n97 手机
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * spu名称:比如 诺基亚n97 手机
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 品牌：比如诺基亚
     * 
     * @param brand the value for brand
     */
    public void setBrand(Long brand) {
        this.brand = brand;
    }

    /**
     * 品牌：比如诺基亚
     * 
     * @return brand the value for brand
     */
    public Long getBrand() {
        return this.brand;
    }

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     * 
     * @param productModel the value for product_model
     */
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     * 
     * @return productModel the value for product_model
     */
    public String getProductModel() {
        return this.productModel;
    }

    /**
     * 0：正常;1：已删除，会移出表
     * 
     * @param deleted the value for deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 0：正常;1：已删除，会移出表
     * 
     * @return deleted the value for deleted
     */
    public Byte getDeleted() {
        return this.deleted;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @param active the value for active
     */
    public void setActive(Byte active) {
        this.active = active;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @return active the value for active
     */
    public Byte getActive() {
        return this.active;
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
