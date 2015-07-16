package com.baidu.jinbao.innerapi.sku.bo;

public class BcsSkuImage extends BcsSkuImageBase {

    public int signature() {
        Integer id = 0;
        return (this.getSkuInnerid() == null ? 0 : this.getSkuInnerid().hashCode())
                ^ (this.getSkuid() == null ? 0 : this.getSkuid().hashCode())
                ^ (this.getImageUrl() == null ? 0 : this.getImageUrl().hashCode())
                ^ (this.getImageBcsUrl() == null ? 0 : this.getImageBcsUrl().hashCode())
                ^ (this.getStatus() == null ? 0 : this.getStatus().hashCode())
                ^ (this.getErrormessage() == null ? 0 : this.getErrormessage().hashCode())
                ^ (this.getWidth() == null ? 0 : this.getWidth().hashCode())
                ^ (this.getHeight() == null ? 0 : this.getHeight().hashCode())
                ^ (this.getContentMd5() == null ? 0 : this.getContentMd5().hashCode())
                ^ (this.getSequence() == null ? 0 : this.getSequence().hashCode())
                ^ (this.getGipsImage() == null ? 0 : this.getGipsImage().hashCode())
                ^ (this.getType() == null ? 0 : this.getType().hashCode());
    }
}