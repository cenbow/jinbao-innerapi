package com.baidu.jinbao.innerapi.sku.vo;

import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;

/**
 * Sku相关信息Vo
 * 
 * @author cgd
 * @date 2015年6月15日 下午3:32:45
 */
public class SkuTotalInfoVo {

    private SkuInfo skuInfo;
    private SkuAttribute skuAttribute;
    private SkuCdt skuCdt;
    private SkuDescription skuDescription;
    private SkuPps skuPps;
    private SkuLevel skuLevel;
    private SkuComment skuComment;
    private SkuCspu skuCspu;

    public SkuInfo getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(SkuInfo skuInfo) {
        this.skuInfo = skuInfo;
    }

    public SkuAttribute getSkuAttribute() {
        return skuAttribute;
    }

    public void setSkuAttribute(SkuAttribute skuAttribute) {
        this.skuAttribute = skuAttribute;
    }

    public SkuCdt getSkuCdt() {
        return skuCdt;
    }

    public void setSkuCdt(SkuCdt skuCdt) {
        this.skuCdt = skuCdt;
    }

    public SkuDescription getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(SkuDescription skuDescription) {
        this.skuDescription = skuDescription;
    }

    public SkuPps getSkuPps() {
        return skuPps;
    }

    public void setSkuPps(SkuPps skuPps) {
        this.skuPps = skuPps;
    }

    public SkuLevel getSkuLevel() {
        return skuLevel;
    }

    public void setSkuLevel(SkuLevel skuLevel) {
        this.skuLevel = skuLevel;
    }

    public SkuComment getSkuComment() {
        return skuComment;
    }

    public void setSkuComment(SkuComment skuComment) {
        this.skuComment = skuComment;
    }

    public SkuCspu getSkuCspu() {
        return skuCspu;
    }

    public void setSkuCspu(SkuCspu skuCspu) {
        this.skuCspu = skuCspu;
    }
}
