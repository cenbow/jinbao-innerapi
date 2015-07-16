package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuCspu extends SkuCspuBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getSkuInnerid() != null) {
            ret.append(this.getSkuInnerid());
        }
        if (this.getCspuid() != null) {
            ret.append(this.getCspuid());
        }
        if (this.getConfidence() != null) {
            ret.append(this.getConfidence());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuCspu skuCspu) {
        if (skuCspu == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuCspu.getSkuInnerid());
        }
        if (this.getCspuid() == null) {
            this.setCspuid(skuCspu.getCspuid());
        }
        if (this.getConfidence() == null) {
            this.setConfidence(skuCspu.getConfidence());
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
        if ((this.getSkuid() == null) || (this.getCspuid() == null) || (this.getType() == null)) {
            return false;
        }
        return true;
    }
}