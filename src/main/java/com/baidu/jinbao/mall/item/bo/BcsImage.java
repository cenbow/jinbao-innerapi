package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class BcsImage extends BcsImageBase {

    private static final long serialVersionUID = 2862532935770061846L;

    public BcsImage checkId() {
        if (this.getId() == null) {
            throw new RuntimeException("id not found!");
        }
        return this;
    }

    public BcsImage checkItemId() {
        if (this.getItemid() == null) {
            throw new RuntimeException("item id is null!");
        }
        return this;
    }

    public BcsImage checkImageUrl() {
        if (this.getImageurl() == null) {
            throw new RuntimeException("Image url is null!");
        }
        return this;
    }

    public BcsImage checkImageBcsUrl() {
        if (this.getImagebcsurl() == null) {
            throw new RuntimeException("ImageBcsUrl is null!");
        }
        return this;
    }

    public BcsImage checkWidth() {
        if (this.getWidth() == null) {
            throw new RuntimeException("width is null!");
        }
        return this;
    }

    public BcsImage checkHeight() {
        if (this.getHeight() == null) {
            throw new RuntimeException("height is null!");
        }
        return this;
    }

    public BcsImage checkContentMd5() {
        if (this.getContentMd5() == null) {
            throw new RuntimeException("contentMd5 is null!");
        }
        return this;
    }

    public BcsImage checkSequence() {
        if (this.getSequence() == null) {
            throw new RuntimeException("sequence is null!");
        }
        return this;
    }

    public BcsImage checkGipsImage() {
        if (this.getGipsImage() == null) {
            throw new RuntimeException("gipsimage is null!");
        }
        return this;
    }

    public BcsImage checkPicType() {
        if (this.getPicType() == null) {
            throw new RuntimeException("pyctype is null!");
        }
        return this;
    }

    public BcsImage checkDefault() {
        if (this.getSkuid() == null) {
            this.setSkuid(0L);
        }
        if (this.getBcsStatus() == null) {
            this.setBcsStatus((byte) 3);
        }
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getErrormessage() == null) {
            this.setErrormessage("");
        }
        return this;
    }

    public BcsImage checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}