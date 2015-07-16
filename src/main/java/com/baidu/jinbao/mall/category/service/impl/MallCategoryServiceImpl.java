package com.baidu.jinbao.mall.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.dao.CategoryDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.mall.category.service.MallCategoryService;

@Service("mallCategoryService")
@SplitModule(moduleName = PdsConstants.MALL_CATEGORY_MODULE_DATASOURCE_KEY)
public class MallCategoryServiceImpl implements MallCategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getRecordByParentid(Long parentid) {
        if (parentid == null) {
            return new ArrayList<Category>();
        }
        return this.categoryDao.selectByParentid(parentid);
    }

    @Override
    public List<Category> getAllRecords() {
        return this.categoryDao.selectAll();
    }

}
