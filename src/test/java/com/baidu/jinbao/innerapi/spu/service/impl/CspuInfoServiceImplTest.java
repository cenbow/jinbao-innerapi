package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.dao.CspuInfoDao;

@Service
public class CspuInfoServiceImplTest {
    @Tested
    private CspuInfoServiceImpl cspuInfoService;

    @Injectable
    private CspuInfoDao cspuInfoDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cspuInfoDao.selectByPrimaryKey(anyLong);
                result = new CspuInfo();
            }
        };
        CspuInfo ret = cspuInfoService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cspuInfoDao.batchInsert((List<CspuInfo>) any);
            }
        };
        List<CspuInfo> cspuInfos = new ArrayList<CspuInfo>();
        CspuInfo cspuInfo1 = new CspuInfo();
        cspuInfo1.setName("testname1");
        CspuInfo cspuInfo2 = new CspuInfo();
        cspuInfo2.setName("testname2");
        cspuInfos.add(cspuInfo1);
        cspuInfos.add(cspuInfo2);
        this.cspuInfoDao.batchInsert(cspuInfos);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cspuInfoDao.batchUpdate((List<CspuInfo>) any);
            }
        };
        List<CspuInfo> cspuInfos = new ArrayList<CspuInfo>();
        CspuInfo cspuInfo1 = new CspuInfo();
        cspuInfo1.setName("testname2");
        CspuInfo cspuInfo2 = new CspuInfo();
        cspuInfo2.setName("testname3");
        cspuInfos.add(cspuInfo1);
        cspuInfos.add(cspuInfo2);
        this.cspuInfoDao.batchUpdate(cspuInfos);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cspuInfoDao.batchSelect((List<Long>) any);
                result = new ArrayList<CspuInfo>();
            }
        };
        List<Long> cspuInfoIds = new ArrayList<Long>();
        cspuInfoIds.add(1L);
        List<CspuInfo> ret = cspuInfoDao.batchSelect(cspuInfoIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cspuInfoDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cspuInfoIds = new ArrayList<Long>();
        cspuInfoIds.add(2L);
        cspuInfoIds.add(3L);
        this.cspuInfoDao.batchDelete(cspuInfoIds);
    }
}
