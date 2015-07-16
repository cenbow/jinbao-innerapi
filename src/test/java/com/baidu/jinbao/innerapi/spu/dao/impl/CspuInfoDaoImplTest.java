package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.dao.CspuInfoDao;

public class CspuInfoDaoImplTest extends AbstractDAOTests {
    @Resource
    private CspuInfoDao cspuInfoDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/cspu_info_init.sql");
    }

    @Test
    public void testFindById() {
        CspuInfo ret = this.cspuInfoDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<CspuInfo> cspuInfos = new ArrayList<CspuInfo>();
        CspuInfo cspuInfo1 = new CspuInfo();
        cspuInfo1.setName("testname1");
        CspuInfo cspuInfo2 = new CspuInfo();
        cspuInfo2.setName("testname2");
        cspuInfos.add(cspuInfo1);
        cspuInfos.add(cspuInfo2);
        this.cspuInfoDao.batchInsert(cspuInfos);
        CspuInfo ret = this.cspuInfoDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.cspuInfoDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<CspuInfo> cspuInfos = new ArrayList<CspuInfo>();
        CspuInfo cspuInfo1 = new CspuInfo();
        cspuInfo1.setName("testname1");
        CspuInfo cspuInfo2 = new CspuInfo();
        cspuInfo2.setName("testname2");
        cspuInfos.add(cspuInfo1);
        cspuInfos.add(cspuInfo2);
        this.cspuInfoDao.batchInsert(cspuInfos);
        cspuInfo1.setCspuid(1L);
        cspuInfo1.setName("testname3");
        cspuInfo2.setCspuid(2L);
        cspuInfo2.setName("testname4");
        this.cspuInfoDao.batchUpdate(cspuInfos);
    }

    @Test
    public void testBatchSelect() {
        List<Long> cspuInfoIds = new ArrayList<Long>();
        cspuInfoIds.add(1L);
        List<CspuInfo> ret = cspuInfoDao.batchSelect(cspuInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> cspuInfoIds = new ArrayList<Long>();
        cspuInfoIds.add(2L);
        cspuInfoIds.add(3L);
        List<CspuInfo> ret = cspuInfoDao.batchSelect(cspuInfoIds);
        Assert.assertNotNull(ret);
        this.cspuInfoDao.batchDelete(cspuInfoIds);
        ret = cspuInfoDao.batchSelect(cspuInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
