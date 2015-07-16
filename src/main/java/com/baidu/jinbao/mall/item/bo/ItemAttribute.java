package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemAttribute extends ItemAttributeBase {

    private static final long serialVersionUID = -3109486801493194054L;

    public ItemAttribute checkDefault() {
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }

    public ItemAttribute checkItemId() {
        if (this.getItemid() == null) {
            throw (new RuntimeException("item id is zero !"));
        }
        return this;
    }

    public ItemAttribute checkPropertyId() {
        if (this.getPropertyId() == null) {
            throw (new RuntimeException("item id is zero !"));
        }
        return this;
    }

    public ItemAttribute checkPropertyValues() {
        if (this.getPropertyValues() == null) {
            throw (new RuntimeException("item id is zero !"));
        }
        return this;
    }

    public ItemAttribute checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}