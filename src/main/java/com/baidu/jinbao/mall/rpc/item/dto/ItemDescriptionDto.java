package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemDescriptionDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商户id
     */
    @Protobuf(order = 2)
    private Integer merchantid;

    /**
     * 店铺id
     */
    @Protobuf(order = 3)
    private Integer shopid;

    /**
     * 商品内部id
     */
    @Protobuf(order = 4)
    private Long itemid;

    /**
     * 商品描述的压缩信息
     */
    @Protobuf(order = 5)
    private byte[] itemDescOri;

    /**
     * 对item_desc_ori做hash
     */
    @Protobuf(order = 6)
    private String pdMd5;

    /**
     * 提取出来的商品描述压缩信息
     */
    @Protobuf(order = 7)
    private byte[] itemDesc;

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
