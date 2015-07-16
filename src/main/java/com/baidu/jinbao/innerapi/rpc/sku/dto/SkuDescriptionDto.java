package com.baidu.jinbao.innerapi.rpc.sku.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SkuDescriptionDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商家id
     */
    @Protobuf(order = 2)
    private Long merchantid;

    /**
     * 商品Shard内商品id
     */
    @Protobuf(order = 3)
    private Long skuInnerid;

    /**
     * 商品全局唯一标识id
     */
    @Protobuf(order = 4)
    private String skuid;

    /**
     * 商品描述的压缩信息
     */
    @Protobuf(order = 5)
    private byte[] skuDescOri;

    /**
     * 商品描述的压缩信息
     */
    @Protobuf(order = 6)
    private String pdHash;

    /**
     * 提取出来的商品描述压缩信息
     */
    @Protobuf(order = 7)
    private byte[] skuDesc;

    /**
     * 0 Undo, 1 doing, 2 done
     */
    @Protobuf(order = 8)
    private Integer pdStatus;

    /**
     * 总的图片区域
     */
    @Protobuf(order = 9)
    private Integer area;

    /**
     * 总字数
     */
    @Protobuf(order = 10)
    private Integer wordCount;

    /**
     * 添加时间
     */
    @Protobuf(order = 11)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 12)
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
     * 商品描述的压缩信息
     * 
     * @param skuDescOri the value for sku_desc_ori
     */
    public void setSkuDescOri(byte[] skuDescOri) {
        this.skuDescOri = skuDescOri;
    }

    /**
     * 商品描述的压缩信息
     * 
     * @return skuDescOri the value for sku_desc_ori
     */
    public byte[] getSkuDescOri() {
        return this.skuDescOri;
    }

    /**
     * 商品描述的压缩信息
     * 
     * @param pdHash the value for pd_hash
     */
    public void setPdHash(String pdHash) {
        this.pdHash = pdHash;
    }

    /**
     * 商品描述的压缩信息
     * 
     * @return pdHash the value for pd_hash
     */
    public String getPdHash() {
        return this.pdHash;
    }

    /**
     * 提取出来的商品描述压缩信息
     * 
     * @param skuDesc the value for sku_desc
     */
    public void setSkuDesc(byte[] skuDesc) {
        this.skuDesc = skuDesc;
    }

    /**
     * 提取出来的商品描述压缩信息
     * 
     * @return skuDesc the value for sku_desc
     */
    public byte[] getSkuDesc() {
        return this.skuDesc;
    }

    /**
     * 0 Undo, 1 doing, 2 done
     * 
     * @param pdStatus the value for pd_status
     */
    public void setPdStatus(Integer pdStatus) {
        this.pdStatus = pdStatus;
    }

    /**
     * 0 Undo, 1 doing, 2 done
     * 
     * @return pdStatus the value for pd_status
     */
    public Integer getPdStatus() {
        return this.pdStatus;
    }

    /**
     * 总的图片区域
     * 
     * @param area the value for area
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * 总的图片区域
     * 
     * @return area the value for area
     */
    public Integer getArea() {
        return this.area;
    }

    /**
     * 总字数
     * 
     * @param wordCount the value for Word_count
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 总字数
     * 
     * @return wordCount the value for Word_count
     */
    public Integer getWordCount() {
        return this.wordCount;
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
