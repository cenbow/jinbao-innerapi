package com.baidu.jinbao.mall.category.vo;

import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;

public class PropertyInfo {

    private CategoryProperty categoryProperty;

    private BaseProperty baseProperty;

    public CategoryProperty getCategoryProperty() {
        return categoryProperty;
    }

    public void setCategoryProperty(CategoryProperty categoryProperty) {
        this.categoryProperty = categoryProperty;
    }

    public BaseProperty getBaseProperty() {
        return baseProperty;
    }

    public void setBaseProperty(BaseProperty baseProperty) {
        this.baseProperty = baseProperty;
    }
}
