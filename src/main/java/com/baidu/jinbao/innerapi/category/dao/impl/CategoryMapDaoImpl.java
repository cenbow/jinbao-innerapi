package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CategoryMap;
import com.baidu.jinbao.innerapi.category.dao.CategoryMapDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CategoryMapMapper;

@Repository
public class CategoryMapDaoImpl extends BaseDao<CategoryMap, Long> implements CategoryMapDao {
    @Autowired
    private CategoryMapMapper categoryMapMapper;

    @Override
    public GenericMapper<CategoryMap, Long> getMapper() {
        return this.categoryMapMapper;
    }

    @Override
    public Integer batchInsert(List<CategoryMap> categoryMaps) {
        if (CollectionUtils.isEmpty(categoryMaps)) {
            return 0;
        }
        return this.categoryMapMapper.batchInsert(categoryMaps);
    }

    @Override
    public Integer batchUpdate(List<CategoryMap> categoryMaps) {
        if (CollectionUtils.isEmpty(categoryMaps)) {
            return 0;
        }
        return this.categoryMapMapper.batchUpdate(categoryMaps);
    }

    @Override
    public Integer batchDelete(List<Long> categoryMapIds) {
        if (CollectionUtils.isEmpty(categoryMapIds)) {
            return 0;
        }
        return this.categoryMapMapper.batchDelete(categoryMapIds);
    }

    @Override
    public List<CategoryMap> batchSelect(List<Long> categoryMapIds) {
        if (CollectionUtils.isEmpty(categoryMapIds)) {
            return new ArrayList<CategoryMap>();
        }
        return this.categoryMapMapper.batchSelect(categoryMapIds);
    }
}
