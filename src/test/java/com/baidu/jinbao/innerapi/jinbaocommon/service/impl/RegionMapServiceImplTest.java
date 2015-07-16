package com.baidu.jinbao.innerapi.jinbaocommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.RegionMapDao;

@Service
public class RegionMapServiceImplTest {
    @Tested
    private RegionMapServiceImpl regionMapService;

    @Injectable
    private RegionMapDao regionMapDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                regionMapDao.selectByPrimaryKey(anyLong);
                result = new RegionMap();
            }
        };
        RegionMap ret = regionMapService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                regionMapDao.batchInsert((List<RegionMap>) any);
            }
        };
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
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                regionMapDao.batchUpdate((List<RegionMap>) any);
            }
        };
        List<RegionMap> regionMapList = new ArrayList<RegionMap>();
        RegionMap regionMap1 = new RegionMap();
        RegionMap regionMap2 = new RegionMap();
        regionMap1.setId(1L);
        regionMap1.setCityid(3);
        regionMap1.setRegionid(3);
        regionMap2.setId(2L);
        regionMap2.setCityid(4);
        regionMap2.setRegionid(4);
        regionMapList.add(regionMap1);
        regionMapList.add(regionMap2);
        this.regionMapDao.batchUpdate(regionMapList);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                regionMapDao.batchSelect((List<Long>) any);
                result = new ArrayList<RegionMap>();
            }
        };
        List<Long> regionMapIds = new ArrayList<Long>();
        regionMapIds.add(1L);
        List<RegionMap> ret = regionMapDao.batchSelect(regionMapIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                regionMapDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> regionMapIds = new ArrayList<Long>();
        regionMapIds.add(2L);
        regionMapIds.add(3L);
        this.regionMapDao.batchDelete(regionMapIds);
    }
}
