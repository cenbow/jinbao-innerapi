package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CmCategory;
import com.baidu.jinbao.innerapi.category.dao.CmCategoryDao;

@Service
public class CmCategoryServiceImplTest {
    @Tested
    private CmCategoryServiceImpl cmCategoryService;

    @Injectable
    private CmCategoryDao cmCategoryDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cmCategoryDao.selectByPrimaryKey(anyLong);
                result = new CmCategory();
            }
        };
        CmCategory ret = cmCategoryService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cmCategoryDao.batchInsert((List<CmCategory>) any);
            }
        };
        List<CmCategory> cmCategories = new ArrayList<CmCategory>();
        CmCategory cmCategory1 = new CmCategory();
        cmCategory1.setName("testname1");
        CmCategory cmCategory2 = new CmCategory();
        cmCategory2.setName("testname2");
        cmCategories.add(cmCategory1);
        cmCategories.add(cmCategory2);
        this.cmCategoryDao.batchInsert(cmCategories);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cmCategoryDao.batchUpdate((List<CmCategory>) any);
            }
        };
        List<CmCategory> cmCategories = new ArrayList<CmCategory>();
        CmCategory cmCategory1 = new CmCategory();
        cmCategory1.setName("testname2");
        CmCategory cmCategory2 = new CmCategory();
        cmCategory2.setName("testname3");
        cmCategories.add(cmCategory1);
        cmCategories.add(cmCategory2);
        this.cmCategoryDao.batchUpdate(cmCategories);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cmCategoryDao.batchSelect((List<Long>) any);
                result = new ArrayList<CmCategory>();
            }
        };
        List<Long> cmCategoryIds = new ArrayList<Long>();
        cmCategoryIds.add(1L);
        List<CmCategory> ret = cmCategoryDao.batchSelect(cmCategoryIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cmCategoryDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cmCategoryIds = new ArrayList<Long>();
        cmCategoryIds.add(2L);
        cmCategoryIds.add(3L);
        this.cmCategoryDao.batchDelete(cmCategoryIds);
    }
}
