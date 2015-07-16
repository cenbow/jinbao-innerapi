package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.category.dao.CategoryPropertyDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CategoryPropertyMapper;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;

@Repository
public class CategoryPropertyDaoImpl extends BaseDao<CategoryProperty, Long> implements CategoryPropertyDao {
    @Autowired
    private CategoryPropertyMapper categoryPropertyMapper;

    @Override
    public GenericMapper<CategoryProperty, Long> getMapper() {
        return this.categoryPropertyMapper;
    }

    @Override
    public Integer batchInsert(List<CategoryProperty> categoryProperties) {
        if (CollectionUtils.isEmpty(categoryProperties)) {
            return 0;
        }
        return this.categoryPropertyMapper.batchInsert(categoryProperties);
    }

    @Override
    public Integer batchUpdate(List<CategoryProperty> categoryProperties) {
        if (CollectionUtils.isEmpty(categoryProperties)) {
            return 0;
        }
        return this.categoryPropertyMapper.batchUpdate(categoryProperties);
    }

    @Override
    public Integer batchDelete(List<Long> categoryPropertyIds) {
        if (CollectionUtils.isEmpty(categoryPropertyIds)) {
            return 0;
        }
        return this.categoryPropertyMapper.batchDelete(categoryPropertyIds);
    }

    @Override
    public List<CategoryProperty> batchSelect(List<Long> categoryPropertyIds) {
        if (CollectionUtils.isEmpty(categoryPropertyIds)) {
            return new ArrayList<CategoryProperty>();
        }
        return this.categoryPropertyMapper.batchSelect(categoryPropertyIds);
    }

    @Override
    public List<PropertyInfo> batchSelectPropertyInfo(List<PropertyIdCondition> propertyIdConditonList) {
        if (CollectionUtils.isEmpty(propertyIdConditonList)) {
            return new ArrayList<PropertyInfo>();
        }
        return this.categoryPropertyMapper.batchSelectPropertyInfo(propertyIdConditonList);
    }
}
