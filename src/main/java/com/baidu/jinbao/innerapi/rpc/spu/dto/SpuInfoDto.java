package com.baidu.jinbao.innerapi.rpc.spu.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SpuInfoDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long spuid;

    /**
     * 后台类目ID
     */
    @Protobuf(order = 2)
    private Long cid;

    /**
     * spu名称:比如 诺基亚n97 手机
     */
    @Protobuf(order = 3)
    private String name;

    /**
     * 品牌：比如诺基亚
     */
    @Protobuf(order = 4)
    private Long brand;

    /**
     * 产品型号：对于产品的描述，可以是型号或文本描述，例如茅台飞天酒
     */
    @Protobuf(order = 5)
    private String productModel;

    /**
     * 0：正常;1：已删除，会移出表
     */
    @Protobuf(order = 6)
    private Integer deleted;

    /**
     * 是否有效,1有效，0无效
     */
    @Protobuf(order = 7)
    private Integer active;

    /**
     * 添加时间
     */
    @Protobuf(order = 8)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 9)
    private String updatetime;

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
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 0：正常;1：已删除，会移出表
     * 
     * @return deleted the value for deleted
     */
    public Integer getDeleted() {
        return this.deleted;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @param active the value for active
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * 是否有效,1有效，0无效
     * 
     * @return active the value for active
     */
    public Integer getActive() {
        return this.active;
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
