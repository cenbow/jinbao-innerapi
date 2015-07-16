package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemInfoBase implements java.io.Serializable {

    private static final long serialVersionUID = 2089587843924350420L;

    /**
     * 自增id
     */
    private Long itemid;

    /**
     * 商户id
     */
    private Integer merchantid;

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     */
    private Long ucid;

    /**
     * 店铺id
     */
    private Integer shopid;

    /**
     * 商品在商家ERP系统中的id
     */
    private String outerid;

    /**
     * mechantid+outerid对应的hashcode
     */
    private Integer itemInfoHashcode;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 官网链接
     */
    private String url;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     */
    private Byte merchantStatus;

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     */
    private Byte manualStatus;

    /**
     * 店铺内分类，可以有多个分类（一期不考虑，留着字段）
     */
    private String shopcategory;

    /**
     * 数据版本（防止老的覆盖旧的）
     */
    private Long dataversion;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 商品件型：必须输入，0免费、1超大件、2超大件半件、3大件、4大件半件、5中件、6中件半件、7小件、8超小件，FBP类型商品必须输入
     */
    private Integer wareBigSmallModel;

    /**
     * 商品包装：必须输入，1普通商品、2易碎品、3裸瓶液体、4带包装液体、5按原包装出库，FBP类型商品必须输入
     */
    private Integer warePackType;

    /**
     * 0：正常1：已删除，会移出表
     */
    private Byte deleted;

    /**
     * 原网站面包屑
     */
    private String categoryOri;

    /**
     * 原始网站品牌
     */
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
     * mechantid+outerid对应的hashcode
     * 
     * @param itemInfoHashcode the value for item_info_hashcode
     */
    public void setItemInfoHashcode(Integer itemInfoHashcode) {
        this.itemInfoHashcode = itemInfoHashcode;
    }

    /**
     * mechantid+outerid对应的hashcode
     * 
     * @return itemInfoHashcode the value for item_info_hashcode
     */
    public Integer getItemInfoHashcode() {
        return this.itemInfoHashcode;
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
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 开始时间
     * 
     * @return starttime the value for starttime
     */
    public Date getStarttime() {
        return this.starttime;
    }

    /**
     * 结束时间
     * 
     * @param endtime the value for endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 结束时间
     * 
     * @return endtime the value for endtime
     */
    public Date getEndtime() {
        return this.endtime;
    }

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     * 
     * @param merchantStatus the value for merchant_status
     */
    public void setMerchantStatus(Byte merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    /**
     * 商家维护状态（0:ONLINE：下架，1:OFFLINE：上架，2:EDIT：编辑中，3：未生效）
     * 
     * @return merchantStatus the value for merchant_status
     */
    public Byte getMerchantStatus() {
        return this.merchantStatus;
    }

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     * 
     * @param manualStatus the value for manual_status
     */
    public void setManualStatus(Byte manualStatus) {
        this.manualStatus = manualStatus;
    }

    /**
     * 运营维护状态（0:Normal，1:Pause，2:uncheck，3:check）
     * 
     * @return manualStatus the value for manual_status
     */
    public Byte getManualStatus() {
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
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * 0：正常1：已删除，会移出表
     * 
     * @return deleted the value for deleted
     */
    public Byte getDeleted() {
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
