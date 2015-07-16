package com.baidu.jinbao.mall.category.vo;

import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;

public class PropertyValueInfo {

    private CategoryPropertyValue categoryPropertyValue;

    private BaseVal baseVal;

    public CategoryPropertyValue getCategoryPropertyValue() {
        return categoryPropertyValue;
    }

    public void setCategoryPropertyValue(CategoryPropertyValue categoryPropertyValue) {
        this.categoryPropertyValue = categoryPropertyValue;
    }

    public BaseVal getBaseVal() {
        return baseVal;
    }

    public void setBaseVal(BaseVal baseVal) {
        this.baseVal = baseVal;
    }


}
