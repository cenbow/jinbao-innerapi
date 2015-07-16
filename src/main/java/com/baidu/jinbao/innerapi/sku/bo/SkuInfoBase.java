package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuInfoBase implements java.io.Serializable {

    /**
     * 商品shard内部id，自增id
     */
    private Long skuInnerid;

    /**
     * Hash（skuid）的结果
     */
    private Long skuHashkey;

    /**
     * 商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。
     */
    private String skuid;

    /**
     * 商家id
     */
    private Long merchantid;

    /**
     * outer_id 的md5值 用来建索引
     */
    private String uniqueKey;

    /**
     * 属于哪个feed，feed平台导入商品时用
     */
    private Long feedid;

    /**
     * 商家在百度内部的统一ID，和merchantid的区别是，merchantid是产品内部自己定义的
     */
    private Long ucid;

    /**
     * 标题（name字段去除，和title一样的东西）
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 官网链接(web)
     */
    private String url;

    /**
     * 官网链接(移动端)
     */
    private String mobileurl;

    /**
     * 商户系统中的商品id
     */
    private String outerid;

    /**
     * 原网站面包屑（和微购schema不同，这边没有拆开分多个字段）
     */
    private String categoryOri;

    /**
     * 原始网站品牌（归一化后品牌从attribute表找）
     */
    private String brandOri;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 商品状态：0 ONLINE(上架),1 OFFLINE（下架）
     */
    private Byte merchantStatus;

    /**
     * 运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）
     */
    private Byte manualStatus;

    /**
     * 商品签名（签名字段：name,title,url,category输入的绝大多数信息，用于重复判断）
     */
    private String signature;

    /**
     * 是否自营（1：自营，0：非自营）
     */
    private Byte isselfopen;

    /**
     * 数据版本（防止老的覆盖旧的）
     */
    private Long dataversion;

    /**
     * 失效时间（先保留，后续再看逻辑）
     */
    private Date inactivetime;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 0：正常;1：已删除，会移出表
     */
    private Byte deleted;

    /**
     * 商家名称
     */
    private String sellerName;

    /**
     * 条形码
     */
    private Long upc;

    /**
     * 商品shard内部id，自增id
     * 
     * @param skuInnerid the value for sku_innerid
     */
    public void setSkuInnerid(Long skuInnerid) {
        this.skuInnerid = skuInnerid;
    }

    /**
     * 商品shard内部id，自增id
     * 
     * @return skuInnerid the value for sku_innerid
     */
    public Long getSkuInnerid() {
        return this.skuInnerid;
    }

    /**
     * Hash（skuid）的结果
     * 
     * @param skuHashkey the value for sku_hashkey
     */
    public void setSkuHashkey(Long skuHashkey) {
        this.skuHashkey = skuHashkey;
    }

    /**
     * Hash（skuid）的结果
     * 
     * @return skuHashkey the value for sku_hashkey
     */
    public Long getSkuHashkey() {
        return this.skuHashkey;
    }

    /**
     * 商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。
     * 
     * @param skuid the value for skuid
     */
    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    /**
     * 商品全局唯一标识id,Merchantid(数字转化为8位的明文)拼接outerid。
     * 
     * @return skuid the value for skuid
     */
    public String getSkuid() {
        return this.skuid;
    }

    /**
     * 商家id
     * 
     * @param merchantid the value for merchantid
     */
    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    /**
     * 商家id
     * 
     * @return merchantid the value for merchantid
     */
    public Long getMerchantid() {
        return this.merchantid;
    }

    /**
     * outer_id 的md5值 用来建索引
     * 
     * @param uniqueKey the value for unique_key
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * outer_id 的md5值 用来建索引
     * 
     * @return uniqueKey the value for unique_key
     */
    public String getUniqueKey() {
        return this.uniqueKey;
    }

    /**
     * 属于哪个feed，feed平台导入商品时用
     * 
     * @param feedid the value for feedid
     */
    public void setFeedid(Long feedid) {
        this.feedid = feedid;
    }

    /**
     * 属于哪个feed，feed平台导入商品时用
     * 
     * @return feedid the value for feedid
     */
    public Long getFeedid() {
        return this.feedid;
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
     * 标题（name字段去除，和title一样的东西）
     * 
     * @param title the value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 标题（name字段去除，和title一样的东西）
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
     * 官网链接(web)
     * 
     * @param url the value for url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 官网链接(web)
     * 
     * @return url the value for url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 官网链接(移动端)
     * 
     * @param mobileurl the value for mobileurl
     */
    public void setMobileurl(String mobileurl) {
        this.mobileurl = mobileurl;
    }

    /**
     * 官网链接(移动端)
     * 
     * @return mobileurl the value for mobileurl
     */
    public String getMobileurl() {
        return this.mobileurl;
    }

    /**
     * 商户系统中的商品id
     * 
     * @param outerid the value for outerid
     */
    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    /**
     * 商户系统中的商品id
     * 
     * @return outerid the value for outerid
     */
    public String getOuterid() {
        return this.outerid;
    }

    /**
     * 原网站面包屑（和微购schema不同，这边没有拆开分多个字段）
     * 
     * @param categoryOri the value for category_ori
     */
    public void setCategoryOri(String categoryOri) {
        this.categoryOri = categoryOri;
    }

    /**
     * 原网站面包屑（和微购schema不同，这边没有拆开分多个字段）
     * 
     * @return categoryOri the value for category_ori
     */
    public String getCategoryOri() {
        return this.categoryOri;
    }

    /**
     * 原始网站品牌（归一化后品牌从attribute表找）
     * 
     * @param brandOri the value for brand_ori
     */
    public void setBrandOri(String brandOri) {
        this.brandOri = brandOri;
    }

    /**
     * 原始网站品牌（归一化后品牌从attribute表找）
     * 
     * @return brandOri the value for brand_ori
     */
    public String getBrandOri() {
        return this.brandOri;
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
     * 商品状态：0 ONLINE(上架),1 OFFLINE（下架）
     * 
     * @param merchantStatus the value for merchant_status
     */
    public void setMerchantStatus(Byte merchantStatus) {
        this.merchantStatus = merchantStatus;
    }

    /**
     * 商品状态：0 ONLINE(上架),1 OFFLINE（下架）
     * 
     * @return merchantStatus the value for merchant_status
     */
    public Byte getMerchantStatus() {
        return this.merchantStatus;
    }

    /**
     * 运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）
     * 
     * @param manualStatus the value for manual_status
     */
    public void setManualStatus(Byte manualStatus) {
        this.manualStatus = manualStatus;
    }

    /**
     * 运营维护状态（0 Normal，1 Pause，2 uncheck，3 check）
     * 
     * @return manualStatus the value for manual_status
     */
    public Byte getManualStatus() {
        return this.manualStatus;
    }

    /**
     * 商品签名（签名字段：name,title,url,category输入的绝大多数信息，用于重复判断）
     * 
     * @param signature the value for signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 商品签名（签名字段：name,title,url,category输入的绝大多数信息，用于重复判断）
     * 
     * @return signature the value for signature
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * 是否自营（1：自营，0：非自营）
     * 
     * @param isselfopen the value for isselfopen
     */
    public void setIsselfopen(Byte isselfopen) {
        this.isselfopen = isselfopen;
    }

    /**
     * 是否自营（1：自营，0：非自营）
     * 
     * @return isselfopen the value for isselfopen
     */
    public Byte getIsselfopen() {
        return this.isselfopen;
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
     * 失效时间（先保留，后续再看逻辑）
     * 
     * @param inactivetime the value for inactivetime
     */
    public void setInactivetime(Date inactivetime) {
        this.inactivetime = inactivetime;
    }

    /**
     * 失效时间（先保留，后续再看逻辑）
     * 
     * @return inactivetime the value for inactivetime
     */
    public Date getInactivetime() {
        return this.inactivetime;
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
     * 商家名称
     * 
     * @param sellerName the value for seller_name
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 商家名称
     * 
     * @return sellerName the value for seller_name
     */
    public String getSellerName() {
        return this.sellerName;
    }

    /**
     * 条形码
     * 
     * @param upc the value for upc
     */
    public void setUpc(Long upc) {
        this.upc = upc;
    }

    /**
     * 条形码
     * 
     * @return upc the value for upc
     */
    public Long getUpc() {
        return this.upc;
    }
}
