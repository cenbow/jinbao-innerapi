package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;
import com.baidu.jinbao.innerapi.spu.dao.CspuLevelDao;

@Service
public class CspuLevelServiceImplTest {
    @Tested
    private CspuLevelServiceImpl cspuLevelService;

    @Injectable
    private CspuLevelDao cspuLevelDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cspuLevelDao.selectByPrimaryKey(anyLong);
                result = new CspuLevel();
            }
        };
        CspuLevel ret = cspuLevelService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cspuLevelDao.batchInsert((List<CspuLevel>) any);
            }
        };
        List<CspuLevel> cspuLevels = new ArrayList<CspuLevel>();
        CspuLevel cspuLevel1 = new CspuLevel();
        cspuLevel1.setCspuid(1L);
        CspuLevel cspuLevel2 = new CspuLevel();
        cspuLevel2.setCspuid(2L);
        cspuLevels.add(cspuLevel1);
        cspuLevels.add(cspuLevel2);
        this.cspuLevelDao.batchInsert(cspuLevels);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cspuLevelDao.batchUpdate((List<CspuLevel>) any);
            }
        };
        List<CspuLevel> cspuLevels = new ArrayList<CspuLevel>();
        CspuLevel cspuLevel1 = new CspuLevel();
        cspuLevel1.setCspuid(3L);
        CspuLevel cspuLevel2 = new CspuLevel();
        cspuLevel2.setCspuid(4L);
        cspuLevels.add(cspuLevel1);
        cspuLevels.add(cspuLevel2);
        this.cspuLevelDao.batchUpdate(cspuLevels);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cspuLevelDao.batchSelect((List<Long>) any);
                result = new ArrayList<CspuLevel>();
            }
        };
        List<Long> cspuLevelIds = new ArrayList<Long>();
        cspuLevelIds.add(1L);
        List<CspuLevel> ret = cspuLevelDao.batchSelect(cspuLevelIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cspuLevelDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cspuLevelIds = new ArrayList<Long>();
        cspuLevelIds.add(2L);
        cspuLevelIds.add(3L);
        this.cspuLevelDao.batchDelete(cspuLevelIds);
    }
}
