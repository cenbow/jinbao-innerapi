package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemCdt extends ItemCdtBase {

    private static final long serialVersionUID = -2496176676358887053L;
    private static final Byte DEFAULT_CLASSFI = Byte.valueOf("1");

    public ItemCdt checkId(ItemCdt itemCdt) {
        if (itemCdt.getId() == null) {
            throw new RuntimeException("id not found");
        }
        return this;
    }

    public ItemCdt checkItemId() {
        if (this.getItemid() == null) {
            throw new RuntimeException("Item id not found");
        }
        return this;
    }

    public ItemCdt checkLeafcategoryId() {
        if (this.getLeafcategoryid() == null) {
            throw new RuntimeException("Leafcategoryid not found");
        }
        return this;
    }

    public ItemCdt checkOperator() {
        if (this.getOperator() == null) {
            throw new RuntimeException("Operator not found");
        }
        return this;
    }

    public ItemCdt checkClassificationType() {
        if (this.getClassificationtype() == null) {
            throw new RuntimeException("Classificationtype not found");
        }
        return this;
    }

    public ItemCdt checkDefault() {
        if (this.getAddtime() == null) {
            this.setAddtime(new Date());
        }
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        if (this.getConfidence() == null) {
            this.setConfidence(Integer.valueOf(100));
        }
        if (this.getClassificationtype() == null) {
            this.setClassificationtype(DEFAULT_CLASSFI);
        }
        return this;
    }
    public ItemCdt checkUpdateTime() {
        if (this.getUpdatetime() == null) {
            this.setUpdatetime(new Date());
        }
        return this;
    }
}