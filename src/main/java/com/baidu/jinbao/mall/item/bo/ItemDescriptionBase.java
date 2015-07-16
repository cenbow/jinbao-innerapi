package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemDescriptionBase implements java.io.Serializable {

    private static final long serialVersionUID = -8957130992161988928L;

    /**
     * 自增id
     */
    private Long id;

    /**
     * 商户id
     */
    private Integer merchantid;

    /**
     * 店铺id
     */
    private Integer shopid;

    /**
     * 商品内部id
     */
    private Long itemid;

    /**
     * 商品描述的压缩信息
     */
    private byte[] itemDescOri;

    /**
     * 对item_desc_ori做hash
     */
    private String pdMd5;

    /**
     * 提取出来的商品描述压缩信息
     */
    private byte[] itemDesc;

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
     * 商品内部id
     * 
     * @param itemid the value for itemid
     */
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    /**
     * 商品内部id
     * 
     * @return itemid the value for itemid
     */
    public Long getItemid() {
        return this.itemid;
    }

    /**
     * 商品描述的压缩信息
     * 
     * @param itemDescOri the value for item_desc_ori
     */
    public void setItemDescOri(byte[] itemDescOri) {
        this.itemDescOri = itemDescOri;
    }

    /**
     * 商品描述的压缩信息
     * 
     * @return itemDescOri the value for item_desc_ori
     */
    public byte[] getItemDescOri() {
        return this.itemDescOri;
    }

    /**
     * 对item_desc_ori做hash
     * 
     * @param pdMd5 the value for pd_md5
     */
    public void setPdMd5(String pdMd5) {
        this.pdMd5 = pdMd5;
    }

    /**
     * 对item_desc_ori做hash
     * 
     * @return pdMd5 the value for pd_md5
     */
    public String getPdMd5() {
        return this.pdMd5;
    }

    /**
     * 提取出来的商品描述压缩信息
     * 
     * @param itemDesc the value for item_desc
     */
    public void setItemDesc(byte[] itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 提取出来的商品描述压缩信息
     * 
     * @return itemDesc the value for item_desc
     */
    public byte[] getItemDesc() {
        return this.itemDesc;
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
