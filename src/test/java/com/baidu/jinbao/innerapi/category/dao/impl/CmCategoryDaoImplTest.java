package com.baidu.jinbao.innerapi.category.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.category.bo.CmCategory;
import com.baidu.jinbao.innerapi.category.dao.CmCategoryDao;

public class CmCategoryDaoImplTest extends AbstractDAOTests {
    @Resource
    private CmCategoryDao cmCategoryDao;

    @Before
    public void setUp() {
        this.executeDatas("category/cm_category_init.sql");
    }

    @Test
    public void testFindById() {
        CmCategory ret = this.cmCategoryDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CmCategory> cmCategories = new ArrayList<CmCategory>();
        CmCategory cmCategory1 = new CmCategory();
        cmCategory1.setName("testname1");
        CmCategory cmCategory2 = new CmCategory();
        cmCategory2.setName("testname2");
        cmCategories.add(cmCategory1);
        cmCategories.add(cmCategory2);
        this.cmCategoryDao.batchInsert(cmCategories);
        CmCategory ret = this.cmCategoryDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.cmCategoryDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CmCategory> cmCategories = new ArrayList<CmCategory>();
        CmCategory cmCategory1 = new CmCategory();
        cmCategory1.setName("testname1");
        CmCategory cmCategory2 = new CmCategory();
        cmCategory2.setName("testname2");
        cmCategories.add(cmCategory1);
        cmCategories.add(cmCategory2);
        this.cmCategoryDao.batchInsert(cmCategories);
        cmCategory1.setId(1L);
        cmCategory1.setName("testname3");
        cmCategory2.setId(2L);
        cmCategory2.setName("testname4");
        this.cmCategoryDao.batchUpdate(cmCategories);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cmCategoryIds = new ArrayList<Long>();
        cmCategoryIds.add(1L);
        List<CmCategory> ret = cmCategoryDao.batchSelect(cmCategoryIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cmCategoryIds = new ArrayList<Long>();
        cmCategoryIds.add(2L);
        cmCategoryIds.add(3L);
        List<CmCategory> ret = cmCategoryDao.batchSelect(cmCategoryIds);
        Assert.assertNotNull(ret);
        this.cmCategoryDao.batchDelete(cmCategoryIds);
        ret = cmCategoryDao.batchSelect(cmCategoryIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
