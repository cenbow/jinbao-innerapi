package com.baidu.jinbao.innerapi.sku.bo;

import java.util.Date;

public class SkuComment extends SkuCommentBase {
    public String signature() {
        StringBuilder ret = new StringBuilder();
        if (this.getSkuInnerid() != null) {
            ret.append(this.getSkuInnerid());
        }
        if (this.getReviewcount() != null) {
            ret.append(this.getReviewcount());
        }
        if (this.getGoodcount() != null) {
            ret.append(this.getGoodcount());
        }
        if (this.getMedcount() != null) {
            ret.append(this.getMedcount());
        }
        if (this.getBadcount() != null) {
            ret.append(this.getBadcount());
        }
        if (this.getTags() != null) {
            ret.append(this.getTags());
        }
        if (this.getScore() != null) {
            ret.append(this.getScore());
        }
        return String.valueOf(ret.toString().hashCode());
    }

    public void mergeValue(SkuComment skuComment) {
        if (skuComment == null) {
            return;
        }
        if (this.getSkuInnerid() == null) {
            this.setSkuInnerid(skuComment.getSkuInnerid());
        }
        if (this.getReviewcount() == null) {
            this.setReviewcount(skuComment.getReviewcount());
        }
        if (this.getGoodcount() == null) {
            this.setGoodcount(skuComment.getGoodcount());
        }
        if (this.getMedcount() == null) {
            this.setMedcount(skuComment.getMedcount());
        }
        if (this.getBadcount() == null) {
            this.setBadcount(skuComment.getBadcount());
        }
        if (this.getTags() == null) {
            this.setTags(skuComment.getTags());
        }
        if (this.getScore() == null) {
            this.setScore(skuComment.getScore());
        }
    }

    public void setDefaultValue() {
        if (this.getTags() == null) {
            this.setTags("");
        }

        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
    }

    public boolean checkRequiredField() {
        if ((this.getSkuid() == null) || (this.getReviewcount() == null) || (this.getGoodcount() == null)
                || (this.getMedcount() == null) || (this.getBadcount() == null) || (this.getScore() == null)) {
            return false;
        }
        return true;
    }
}