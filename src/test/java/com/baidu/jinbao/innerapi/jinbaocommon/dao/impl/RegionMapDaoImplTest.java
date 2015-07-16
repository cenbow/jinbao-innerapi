package com.baidu.jinbao.innerapi.jinbaocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.RegionMapDao;

public class RegionMapDaoImplTest extends AbstractDAOTests {
    @Resource
    private RegionMapDao regionMapDao;

    @Before
    public void setUp() {
        this.executeDatas("jinbaocommon/region_map_init.sql");
    }

    @Test
    public void testFindById() {
        RegionMap ret = this.regionMapDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<RegionMap> regionMapList = new ArrayList<RegionMap>();
        RegionMap regionMap1 = new RegionMap();
        regionMap1.setCityid(1);
        regionMap1.setRegionid(1);
        RegionMap regionMap2 = new RegionMap();
        regionMap2.setCityid(2);
        regionMap2.setRegionid(2);
        regionMapList.add(regionMap1);
        regionMapList.add(regionMap2);
        this.regionMapDao.batchInsert(regionMapList);
        RegionMap ret = this.regionMapDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCityid().equals(1));
        ret = this.regionMapDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCityid().equals(2));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<RegionMap> regionMapList = new ArrayList<RegionMap>();
        RegionMap regionMap1 = new RegionMap();
        regionMap1.setCityid(1);
        regionMap1.setRegionid(1);
        RegionMap regionMap2 = new RegionMap();
        regionMap2.setCityid(2);
        regionMap2.setRegionid(2);
        regionMapList.add(regionMap1);
        regionMapList.add(regionMap2);
        this.regionMapDao.batchInsert(regionMapList);
        regionMap1.setId(1L);
        regionMap1.setCityid(3);
        regionMap1.setRegionid(3);
        regionMap2.setId(2L);
        regionMap2.setCityid(4);
        regionMap2.setRegionid(4);
        this.regionMapDao.batchUpdate(regionMapList);
    }

    @Test
    public void testBatchSelect() {
        List<Long> regionMapIds = new ArrayList<Long>();
        regionMapIds.add(1L);
        List<RegionMap> ret = regionMapDao.batchSelect(regionMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> regionMapIds = new ArrayList<Long>();
        regionMapIds.add(2L);
        regionMapIds.add(3L);
        List<RegionMap> ret = regionMapDao.batchSelect(regionMapIds);
        Assert.assertNotNull(ret);
        this.regionMapDao.batchDelete(regionMapIds);
        ret = regionMapDao.batchSelect(regionMapIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
