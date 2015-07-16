package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuLevel extends SkuLevelBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getLevel() != null) {
            ret.append(this.getLevel());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuLevel skuLevel) {
        if (skuLevel == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuLevel.getSkuInnerid());
        }
        if (this.getLevel() == null) {
            this.setLevel(skuLevel.getLevel());
        }
    }
    
    public void setDefaultValue() {
        if (this.getLevel() == null) {
            this.setLevel(0);
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if (this.getSkuid() == null) {
            return false;
        }
        return true;
    }
}