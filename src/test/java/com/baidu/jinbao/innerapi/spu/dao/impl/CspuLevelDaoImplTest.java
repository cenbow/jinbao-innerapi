package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;
import com.baidu.jinbao.innerapi.spu.dao.CspuLevelDao;

public class CspuLevelDaoImplTest extends AbstractDAOTests {
    @Resource
    private CspuLevelDao cspuLevelDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/cspu_level_init.sql");
    }

    @Test
    public void testFindById() {
        CspuLevel ret = this.cspuLevelDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CspuLevel> cspuLevels = new ArrayList<CspuLevel>();
        CspuLevel cspuLevel1 = new CspuLevel();
        cspuLevel1.setCspuid(1L);
        CspuLevel cspuLevel2 = new CspuLevel();
        cspuLevel2.setCspuid(2L);
        cspuLevels.add(cspuLevel1);
        cspuLevels.add(cspuLevel2);
        this.cspuLevelDao.batchInsert(cspuLevels);
        CspuLevel ret = this.cspuLevelDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(1L));
        ret = this.cspuLevelDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(2L));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CspuLevel> cspuLevels = new ArrayList<CspuLevel>();
        CspuLevel cspuLevel1 = new CspuLevel();
        cspuLevel1.setCspuid(1L);
        CspuLevel cspuLevel2 = new CspuLevel();
        cspuLevel2.setCspuid(2L);
        cspuLevels.add(cspuLevel1);
        cspuLevels.add(cspuLevel2);
        this.cspuLevelDao.batchInsert(cspuLevels);
        cspuLevel1.setId(2L);
        cspuLevel1.setCspuid(3L);
        cspuLevel2.setId(3L);
        cspuLevel2.setCspuid(4L);
        this.cspuLevelDao.batchUpdate(cspuLevels);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cspuLevelIds = new ArrayList<Long>();
        cspuLevelIds.add(1L);
        List<CspuLevel> ret = cspuLevelDao.batchSelect(cspuLevelIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cspuLevelIds = new ArrayList<Long>();
        cspuLevelIds.add(2L);
        cspuLevelIds.add(3L);
        List<CspuLevel> ret = cspuLevelDao.batchSelect(cspuLevelIds);
        Assert.assertNotNull(ret);
        this.cspuLevelDao.batchDelete(cspuLevelIds);
        ret = cspuLevelDao.batchSelect(cspuLevelIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
