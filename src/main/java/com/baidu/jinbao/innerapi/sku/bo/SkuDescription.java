package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;


public class SkuDescription extends SkuDescriptionBase {
    public String signature() {

        StringBuilder ret = new StringBuilder();
        if (this.getMerchantid() != null) {
            ret.append(this.getMerchantid());
        }
        if (this.getSkuDescOri() != null) {
            ret.append(new String(this.getSkuDescOri()));
        }
        if (this.getPdHash() != null) {
            ret.append(this.getPdHash());
        }
        if (this.getSkuDesc() != null) {
            ret.append(new String(this.getSkuDesc()));
        }
        if (this.getPdStatus() != null) {
            ret.append(this.getPdStatus());
        }
        if (this.getArea() != null) {
            ret.append(this.getArea());
        }
        if (this.getWordCount() != null) {
            ret.append(this.getWordCount());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuDescription skuDescription) {
        if (skuDescription == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuDescription.getSkuInnerid());
        }
        if (this.getMerchantid() == null) {
            this.setMerchantid(skuDescription.getMerchantid());
        }
        if (this.getSkuDescOri() == null) {
            this.setSkuDescOri(skuDescription.getSkuDescOri());
        }
        if (this.getPdHash() == null) {
            this.setPdHash(skuDescription.getPdHash());
        }
        if (this.getSkuDesc() == null) {
            this.setSkuDesc(skuDescription.getSkuDesc());
        }
        if (this.getPdStatus() == null) {
            this.setPdStatus(skuDescription.getPdStatus());
        }
        if (this.getArea() == null) {
            this.setArea(skuDescription.getArea());
        }
        if (this.getWordCount() == null) {
            this.setWordCount(skuDescription.getWordCount());
        }
    }
    
    public void setDefaultValue() {
        if (this.getPdStatus() == null) {
            this.setPdStatus(new Integer(0).byteValue());
        }
        if (this.getArea() == null) {
            this.setArea(0);
        }
        if (this.getWordCount() == null) {
            this.setWordCount(0);
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getMerchantid() == null) || (this.getSkuDescOri() == null)
                || (this.getSkuDesc() == null)) {
            return false;
        }
        return true;
    }
}