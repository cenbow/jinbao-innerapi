package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

import com.baidu.jinbao.innerapi.common.utils.MD5Util;

public class SkuAttribute extends SkuAttributeBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getMerchantid() != null) {
            ret.append(this.getMerchantid());
        }
        if (this.getPropertyId() != null) {
            ret.append(this.getPropertyId());
        }
        if (this.getPropertyHash() != null) {
            ret.append(this.getPropertyHash());
        }
        if (this.getPropertyValues() != null) {
            ret.append(this.getPropertyValues());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuAttribute skuAttribute) {
        if (skuAttribute == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuAttribute.getSkuInnerid());
        }
        if (this.getMerchantid() == null) {
            this.setMerchantid(skuAttribute.getMerchantid());
        }
        if (this.getPropertyId() == null) {
            this.setPropertyId(skuAttribute.getPropertyId());
        }
        if (this.getPropertyValues() == null) {
            this.setPropertyValues(skuAttribute.getPropertyValues());
        }
        this.setPropertyHash(MD5Util.getMD5Value(this.getPropertyId() + this.getPropertyValues()));
    }

    public void setDefaultValue() {
        if (this.getPropertyId() == null) {
            this.setPropertyId("");
        }
        if (this.getPropertyValues() == null) {
            this.setPropertyValues("");
        }
        this.setPropertyHash(MD5Util.getMD5Value(this.getPropertyId() + this.getPropertyValues()));
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getMerchantid() == null)) {
            return false;
        }
        return true;
    }
}