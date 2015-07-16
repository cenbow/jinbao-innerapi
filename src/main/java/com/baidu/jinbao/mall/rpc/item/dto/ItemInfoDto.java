package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemInfoDto {
    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long itemid;

    /**
     * 商户id
     */
    @Protobuf(order = 2)
    private Integer merchantid;

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     */
    @Protobuf(order = 3)
    private Long ucid;

    /**
     * 店铺id
     */
    @Protobuf(order = 4)
    private Integer shopid;

    /**
     * 商品在商家ERP系统中的id
     */
    @Protobuf(order = 5)
    private String outerid;

    /**
     * 标题
     */
    @Protobuf(order = 6)
    private String title;

    /**
     * 副标题
     */
    @Protobuf(order = 7)
    private String subtitle;

    /**
     * 官网链接
     */
    @Protobuf(order = 8)
    private String url;

    /**
     * 开始时间
     */
    @Protobuf(order = 9)
    private String starttime;

    /**
     * 结束时间
     */
    @Protobuf(order = 10)
    private String endtime;

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     */
    @Protobuf(order = 11)
    private Integer merchantStatus;

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     */
    @Protobuf(order = 12)
    private Integer manualStatus;

    /**
     * 店铺内分类，可以有多个分类（一期不考虑，留着字段）
     */
    @Protobuf(order = 13)
    private String shopcategory;

    /**
     * 数据版本（防止老的覆盖旧的）
     */
    @Protobuf(order = 14)
    private Long dataversion;

    /**
     * 添加时间
     */
    @Protobuf(order = 15)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 16)
    private String updatetime;

    /**
     * 商品件型：必须输入，0免费、1超大件、2超大件半件、3大件、4大件半件、5中件、6中件半件、7小件、8超小件，FBP类型商品必须输入
     */
    @Protobuf(order = 17)
    private Integer wareBigSmallModel;

    /**
     * 商品包装：必须输入，1普通商品、2易碎品、3裸瓶液体、4带包装液体、5按原包装出库，FBP类型商品必须输入
     */
    @Protobuf(order = 18)
    private Integer warePackType;

    /**
     * 0：正常1：已删除，会移出表
     */
    @Protobuf(order = 19)
    private Integer deleted;

    /**
     * 原网站面包屑
     */
    @Protobuf(order = 20)
    private String categoryOri;

    /**
     * 原始网站品牌
     */
    @Protobuf(order = 21)
    private String brandOri;

    /**
     * 自增id
     * 
     * @param itemid the value for itemid
     */
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    /**
     * 自增id
     * 
     * @return itemid the value for itemid
     */
    public Long getItemid() {
        return this.itemid;
    }

    /**
     * 商户id
     * 
     * @param merchantid the value for merchantid
     */
    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * 商户id
     * 
     * @return merchantid the value for merchantid
     */
    public Integer getMerchantid() {
        return this.merchantid;
    }

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     * 
     * @param ucid the value for ucid
     */
    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     * 
     * @return ucid the value for ucid
     */
    public Long getUcid() {
        return this.ucid;
    }

    /**
     * 店铺id
     * 
     * @param shopid the value for shopid
     */
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    /**
     * 店铺id
     * 
     * @return shopid the value for shopid
     */
    public Integer getShopid() {
        return this.shopid;
    }

    /**
     * 商品在商家ERP系统中的id
     * 
     * @param outerid the value for outerid
     */
    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    /**
     * 商品在商家ERP系统中的id
     * 
     * @return outerid the value for outerid
     */
    public String getOuterid() {
        return this.outerid;
    }

    /**
     * 标题
     * 
     * @param title the value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 标题
     * 
     * @return title the value for title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 副标题
     * 
     * @param subtitle the value for subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 副标题
     * 
     * @return subtitle the value for subtitle
     */
    public String getSubtitle() {
        return this.subtitle;
    }

    /**
     * 官网链接
     * 
     * @param url the value for url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 官网链接
     * 
     * @return url the value for url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 开始时间
     * 
     * @param starttime the value for starttime
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * 开始时间
     * 
     * @return starttime the value for starttime
     */
    public String getStarttime() {
        return this.starttime;
    }

    /**
     * 结束时间
     * 
     * @param endtime the value for endtime
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * 结束时间
     * 
     * @return endtime the value for endtime
     */
    public String getEndtime() {
        return this.endtime;
    }

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     * 
     * @param merchantStatus the value for merchant_status
     */
    public void setMerchantStatus(Integer merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     * 
     * @return merchantStatus the value for merchant_status
     */
    public Integer getMerchantStatus() {
        return this.merchantStatus;
    }

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     * 
     * @param manualStatus the value for manual_status
     */
    public void setManualStatus(Integer manualStatus) {
        this.manualStatus = manualStatus;
    }

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     * 
     * @return manualStatus the value for manual_status
     */
    public Integer getManualStatus() {
        return this.manualStatus;
    }

    /**
     * 店铺内分类，可以有多个分类（一期不考虑，留着字段）
     * 
     * @param shopcategory the value for shopcategory
     */
    public void setShopcategory(String shopcategory) {
        this.shopcategory = shopcategory;
    }

    /**
     * 店铺内分类，可以有多个分类（一期不考虑，留着字段）
     * 
     * @return shopcategory the value for shopcategory
     */
    public String getShopcategory() {
        return this.shopcategory;
    }

    /**
     * 数据版本（防止老的覆盖旧的）
     * 
     * @param dataversion the value for dataversion
     */
    public void setDataversion(Long dataversion) {
        this.dataversion = dataversion;
    }

    /**
     * 数据版本（防止老的覆盖旧的）
     * 
     * @return dataversion the value for dataversion
     */
    public Long getDataversion() {
        return this.dataversion;
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

    /**
     * 商品件型：必须输入，0免费、1超大件、2超大件半件、3大件、4大件半件、5中件、6中件半件、7小件、8超小件，FBP类型商品必须输入
     * 
     * @param wareBigSmallModel the value for ware_big_small_model
     */
    public void setWareBigSmallModel(Integer wareBigSmallModel) {
        this.wareBigSmallModel = wareBigSmallModel;
    }

    /**
     * 商品件型：必须输入，0免费、1超大件、2超大件半件、3大件、4大件半件、5中件、6中件半件、7小件、8超小件，FBP类型商品必须输入
     * 
     * @return wareBigSmallModel the value for ware_big_small_model
     */
    public Integer getWareBigSmallModel() {
        return this.wareBigSmallModel;
    }

    /**
     * 商品包装：必须输入，1普通商品、2易碎品、3裸瓶液体、4带包装液体、5按原包装出库，FBP类型商品必须输入
     * 
     * @param warePackType the value for ware_pack_type
     */
    public void setWarePackType(Integer warePackType) {
        this.warePackType = warePackType;
    }

    /**
     * 商品包装：必须输入，1普通商品、2易碎品、3裸瓶液体、4带包装液体、5按原包装出库，FBP类型商品必须输入
     * 
     * @return warePackType the value for ware_pack_type
     */
    public Integer getWarePackType() {
        return this.warePackType;
    }

    /**
     * 0：正常1：已删除，会移出表
     * 
     * @param deleted the value for deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 0：正常1：已删除，会移出表
     * 
     * @return deleted the value for deleted
     */
    public Integer getDeleted() {
        return this.deleted;
    }

    /**
     * 原网站面包屑
     * 
     * @param categoryOri the value for category_ori
     */
    public void setCategoryOri(String categoryOri) {
        this.categoryOri = categoryOri;
    }

    /**
     * 原网站面包屑
     * 
     * @return categoryOri the value for category_ori
     */
    public String getCategoryOri() {
        return this.categoryOri;
    }

    /**
     * 原始网站品牌
     * 
     * @param brandOri the value for brand_ori
     */
    public void setBrandOri(String brandOri) {
        this.brandOri = brandOri;
    }

    /**
     * 原始网站品牌
     * 
     * @return brandOri the value for brand_ori
     */
    public String getBrandOri() {
        return this.brandOri;
    }
}
