package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuInfo extends SkuInfoBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getSkuid() != null) {
            ret.append(this.getSkuid());
        }
        if (this.getMerchantid() != null) {
            ret.append(this.getMerchantid());
        }
        if (this.getUniqueKey() != null) {
            ret.append(this.getUniqueKey());
        }
        if (this.getFeedid() != null) {
            ret.append(this.getFeedid());
        }
        if (this.getUcid() != null) {
            ret.append(this.getUcid());
        }
        if (this.getTitle() != null) {
            ret.append(this.getTitle());
        }
        if (this.getSubtitle() != null) {
            ret.append(this.getSubtitle());
        }
        if (this.getUrl() != null) {
            ret.append(this.getUrl());
        }
        if (this.getMobileurl() != null) {
            ret.append(this.getMobileurl());
        }
        if (this.getOuterid() != null) {
            ret.append(this.getOuterid());
        }
        if (this.getCategoryOri() != null) {
            ret.append(this.getCategoryOri());
        }
        if (this.getBrandOri() != null) {
            ret.append(this.getBrandOri());
        }
        if (this.getMerchantStatus() != null) {
            ret.append(this.getMerchantStatus());
        }
        if (this.getManualStatus() != null) {
            ret.append(this.getManualStatus());
        }
        if (this.getIsselfopen() != null) {
            ret.append(this.getIsselfopen());
        }
        if (this.getDataversion() != null) {
            ret.append(this.getDataversion());
        }
        if (this.getDeleted() != null) {
            ret.append(this.getDeleted());
        }
        if (this.getSellerName() != null) {
            ret.append(this.getSellerName());
        }
        if (this.getUpc() != null) {
            ret.append(this.getUpc());
        }

        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuInfo skuInfo) {
        if (skuInfo == null) {
            return;
        }
        if (this.getSkuid() == null) {
            this.setSkuid(skuInfo.getSkuid());
        }
        if (this.getMerchantid() == null) {
            this.setMerchantid(skuInfo.getMerchantid());
        }
        if (this.getUniqueKey() == null) {
            this.setUniqueKey(skuInfo.getUniqueKey());
        }
        if (this.getFeedid() == null) {
            this.setFeedid(skuInfo.getFeedid());
        }
        if (this.getUcid() == null) {
            this.setUcid(skuInfo.getUcid());
        }
        if (this.getTitle() == null) {
            this.setTitle(skuInfo.getTitle());
        }
        if (this.getSubtitle() == null) {
            this.setSubtitle(skuInfo.getSubtitle());
        }
        if (this.getUrl() == null) {
            this.setUrl(skuInfo.getUrl());
        }
        if (this.getMobileurl() == null) {
            this.setMobileurl(skuInfo.getMobileurl());
        }
        if (this.getOuterid() == null) {
            this.setOuterid(skuInfo.getOuterid());
        }
        if (this.getCategoryOri() == null) {
            this.setCategoryOri(skuInfo.getCategoryOri());
        }
        if (this.getBrandOri() == null) {
            this.setBrandOri(skuInfo.getBrandOri());
        }
        if (this.getStarttime() == null) {
            this.setStarttime(skuInfo.getStarttime());
        }
        if (this.getEndtime() == null) {
            this.setEndtime(skuInfo.getEndtime());
        }
        if (this.getMerchantStatus() == null) {
            this.setMerchantStatus(skuInfo.getManualStatus());
        }
        if (this.getManualStatus() == null) {
            this.setManualStatus(skuInfo.getManualStatus());
        }
        if (this.getIsselfopen() == null) {
            this.setIsselfopen(skuInfo.getIsselfopen());
        }
        if (this.getDataversion() == null) {
            this.setDataversion(skuInfo.getDataversion());
        }
        if (this.getInactivetime() == null) {
            this.setInactivetime(skuInfo.getInactivetime());
        }
        if (this.getDeleted() == null) {
            this.setDeleted(skuInfo.getDeleted());
        }
        if (this.getSellerName() == null) {
            this.setSellerName(skuInfo.getSellerName());
        }
        if (this.getUpc() == null) {
            this.setUpc(skuInfo.getUpc());
        }
    }

    public void setDefaultValue() {
        if (this.getUcid() == null) {
            this.setUcid(0L);
        }
        if (this.getSubtitle() == null) {
            this.setSubtitle("");
        }
        if (this.getUrl() == null) {
            this.setUrl("");
        }
        if (this.getMobileurl() == null) {
            this.setMobileurl("");
        }
        if (this.getCategoryOri() == null) {
            this.setCategoryOri("");
        }
        if (this.getBrandOri() == null) {
            this.setBrandOri("");
        }
        if (this.getMerchantStatus() == null) {
            this.setMerchantStatus(new Integer(0).byteValue());
        }
        if (this.getManualStatus() == null) {
            this.setManualStatus(new Integer(0).byteValue());
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getDeleted() == null) {
            this.setDeleted(new Integer(0).byteValue());
        }
        if (this.getSellerName() == null) {
            this.setSellerName("");
        }
        if (this.getUpc() == null) {
            this.setUpc(0L);
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getMerchantid() == null) || (this.getFeedid() == null)
                || (this.getTitle() == null) || (this.getOuterid() == null) || (this.getStarttime() == null)
                || (this.getEndtime() == null) || (this.getSignature() == null) || (this.getIsselfopen() == null)
                || (this.getDataversion() == null) || (this.getInactivetime() == null)) {
            return false;
        }
        return true;
    }
}