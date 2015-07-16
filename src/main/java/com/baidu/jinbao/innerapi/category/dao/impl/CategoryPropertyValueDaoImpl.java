package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyValueDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CategoryPropertyValueMapper;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;

@Repository
public class CategoryPropertyValueDaoImpl extends BaseDao<CategoryPropertyValue, Long> implements
        CategoryPropertyValueDao {
    @Autowired
    private CategoryPropertyValueMapper categoryPropertyValueMapper;

    @Override
    public GenericMapper<CategoryPropertyValue, Long> getMapper() {
        return this.categoryPropertyValueMapper;
    }

    @Override
    public Integer batchInsert(List<CategoryPropertyValue> categoryPropertyValues) {
        if (CollectionUtils.isEmpty(categoryPropertyValues)) {
            return 0;
        }
        return this.categoryPropertyValueMapper.batchInsert(categoryPropertyValues);
    }

    @Override
    public Integer batchUpdate(List<CategoryPropertyValue> categoryPropertyValues) {
        if (CollectionUtils.isEmpty(categoryPropertyValues)) {
            return 0;
        }
        return this.categoryPropertyValueMapper.batchUpdate(categoryPropertyValues);
    }

    @Override
    public Integer batchDelete(List<Long> categoryPropertyValueIds) {
        if (CollectionUtils.isEmpty(categoryPropertyValueIds)) {
            return 0;
        }
        return this.categoryPropertyValueMapper.batchDelete(categoryPropertyValueIds);
    }

    @Override
    public List<CategoryPropertyValue> batchSelect(List<Long> categoryPropertyValueIds) {
        if (CollectionUtils.isEmpty(categoryPropertyValueIds)) {
            return new ArrayList<CategoryPropertyValue>();
        }
        return this.categoryPropertyValueMapper.batchSelect(categoryPropertyValueIds);
    }

    @Override
    public List<PropertyValueInfo> batchSelectPropertyValueInfo(List<PropertyIdCondition> propertyIdConditonList) {
        if (CollectionUtils.isEmpty(propertyIdConditonList)) {
            return new ArrayList<PropertyValueInfo>();
        }
        return this.categoryPropertyValueMapper.batchSelectPropertyValueInfo(propertyIdConditonList);
    }
}
