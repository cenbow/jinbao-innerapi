package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;


public class SkuCdt extends SkuCdtBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getSkuInnerid() != null) {
            ret.append(this.getSkuInnerid());
        }
        if (this.getLeafcategoryid() != null) {
            ret.append(this.getLeafcategoryid());
        }
        if (this.getConfidence() != null) {
            ret.append(this.getConfidence());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuCdt skuCdt) {
        if (skuCdt == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuCdt.getSkuInnerid());
        }
        if (this.getLeafcategoryid() == null) {
            this.setLeafcategoryid(skuCdt.getLeafcategoryid());
        }
        if (this.getConfidence() == null) {
            this.setConfidence(skuCdt.getConfidence());
        }
    }
    
    public void setDefaultValue() {
        if (this.getConfidence() == null) {
            this.setConfidence(0F);
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getLeafcategoryid() == null) || (this.getClassificationtype() == null)) {
            return false;
        }
        return true;
    }
}