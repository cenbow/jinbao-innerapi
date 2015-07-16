package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.dao.SpuInfoDao;

@Service
public class SpuInfoServiceImplTest {
    @Tested
    private SpuInfoServiceImpl spuInfoService;

    @Injectable
    private SpuInfoDao spuInfoDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                spuInfoDao.selectByPrimaryKey(anyLong);
                result = new SpuInfo();
            }
        };
        SpuInfo ret = spuInfoService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                spuInfoDao.batchInsert((List<SpuInfo>) any);
            }
        };
        List<SpuInfo> spuInfos = new ArrayList<SpuInfo>();
        SpuInfo spuInfo1 = new SpuInfo();
        spuInfo1.setName("testname1");
        SpuInfo spuInfo2 = new SpuInfo();
        spuInfo2.setName("testname2");
        spuInfos.add(spuInfo1);
        spuInfos.add(spuInfo2);
        this.spuInfoDao.batchInsert(spuInfos);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                spuInfoDao.batchUpdate((List<SpuInfo>) any);
            }
        };
        List<SpuInfo> spuInfos = new ArrayList<SpuInfo>();
        SpuInfo spuInfo1 = new SpuInfo();
        spuInfo1.setName("testname2");
        SpuInfo spuInfo2 = new SpuInfo();
        spuInfo2.setName("testname3");
        spuInfos.add(spuInfo1);
        spuInfos.add(spuInfo2);
        this.spuInfoDao.batchUpdate(spuInfos);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                spuInfoDao.batchSelect((List<Long>) any);
                result = new ArrayList<SpuInfo>();
            }
        };
        List<Long> spuInfoIds = new ArrayList<Long>();
        spuInfoIds.add(1L);
        List<SpuInfo> ret = spuInfoDao.batchSelect(spuInfoIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                spuInfoDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> spuInfoIds = new ArrayList<Long>();
        spuInfoIds.add(2L);
        spuInfoIds.add(3L);
        this.spuInfoDao.batchDelete(spuInfoIds);
    }
}
