package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CmCategory;
import com.baidu.jinbao.innerapi.category.dao.CmCategoryDao;
import com.baidu.jinbao.innerapi.category.dao.mapper.CmCategoryMapper;

@Repository
public class CmCategoryDaoImpl extends BaseDao<CmCategory, Long> implements CmCategoryDao {
    @Autowired
    private CmCategoryMapper cmCategoryMapper;

    @Override
    public GenericMapper<CmCategory, Long> getMapper() {
        return this.cmCategoryMapper;
    }

    @Override
    public Integer batchInsert(List<CmCategory> cmCategories) {
        if (CollectionUtils.isEmpty(cmCategories)) {
            return 0;
        }
        return this.cmCategoryMapper.batchInsert(cmCategories);
    }

    @Override
    public Integer batchUpdate(List<CmCategory> cmCategories) {
        if (CollectionUtils.isEmpty(cmCategories)) {
            return 0;
        }
        return this.cmCategoryMapper.batchUpdate(cmCategories);
    }

    @Override
    public Integer batchDelete(List<Long> cmCategoryIds) {
        if (CollectionUtils.isEmpty(cmCategoryIds)) {
            return 0;
        }
        return this.cmCategoryMapper.batchDelete(cmCategoryIds);
    }

    @Override
    public List<CmCategory> batchSelect(List<Long> cmCategoryIds) {
        if (CollectionUtils.isEmpty(cmCategoryIds)) {
            return new ArrayList<CmCategory>();
        }
        return this.cmCategoryMapper.batchSelect(cmCategoryIds);
    }
}
