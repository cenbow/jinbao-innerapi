package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.dao.SpuInfoDao;

public class SpuInfoDaoImplTest extends AbstractDAOTests {
    @Resource
    private SpuInfoDao spuInfoDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/spu_info_init.sql");
    }

    @Test
    public void testFindById() {
        SpuInfo ret = this.spuInfoDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getSpuid().equals(1L));
    }
    
    @Test
    public void testInsert() {
        SpuInfo spuInfo = new SpuInfo();
        Integer ret = this.spuInfoDao.insert(spuInfo);
        System.out.println("result:" + spuInfo.getSpuid());
    }
    
    @Test
    public void testBatchInsert() {
        List<SpuInfo> spuInfos = new ArrayList<SpuInfo>();
        SpuInfo spuInfo1 = new SpuInfo();
        spuInfo1.setName("testname1");
        SpuInfo spuInfo2 = new SpuInfo();
        spuInfo2.setCid(0L);
        spuInfo2.setName("testname2");
        spuInfos.add(spuInfo1);
        spuInfos.add(spuInfo2);
        this.spuInfoDao.batchInsert(spuInfos);
        SpuInfo ret = this.spuInfoDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.spuInfoDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<SpuInfo> spuInfos = new ArrayList<SpuInfo>();
        SpuInfo spuInfo1 = new SpuInfo();
        spuInfo1.setName("testname1");
        SpuInfo spuInfo2 = new SpuInfo();
        spuInfo2.setName("testname2");
        spuInfos.add(spuInfo1);
        spuInfos.add(spuInfo2);
        this.spuInfoDao.batchInsert(spuInfos);
        spuInfo1.setSpuid(1L);
        spuInfo1.setName("testname3");
        spuInfo2.setSpuid(2L);
        spuInfo2.setName("testname4");
        this.spuInfoDao.batchUpdate(spuInfos);
    }

    @Test
    public void testBatchSelect() {
        List<Long> spuInfoIds = new ArrayList<Long>();
        spuInfoIds.add(1L);
        List<SpuInfo> ret = spuInfoDao.batchSelect(spuInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> spuInfoIds = new ArrayList<Long>();
        spuInfoIds.add(2L);
        spuInfoIds.add(3L);
        List<SpuInfo> ret = spuInfoDao.batchSelect(spuInfoIds);
        Assert.assertNotNull(ret);
        this.spuInfoDao.batchDelete(spuInfoIds);
        ret = spuInfoDao.batchSelect(spuInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
