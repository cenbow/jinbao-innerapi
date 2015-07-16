package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.dao.BaseValDao;

@Service
public class BaseValServiceImplTest {
    @Tested
    private BaseValServiceImpl baseValService;

    @Injectable
    private BaseValDao baseValDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                baseValDao.selectByPrimaryKey(anyLong);
                result = new BaseVal();
            }
        };
        BaseVal ret = baseValService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                baseValDao.batchInsert((List<BaseVal>) any);
            }
        };
        List<BaseVal> baseVals = new ArrayList<BaseVal>();
        BaseVal baseVal1 = new BaseVal();
        baseVal1.setValue("testname1");
        BaseVal baseVal2 = new BaseVal();
        baseVal2.setValue("testname2");
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        this.baseValDao.batchInsert(baseVals);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                baseValDao.batchUpdate((List<BaseVal>) any);
            }
        };
        List<BaseVal> baseVals = new ArrayList<BaseVal>();
        BaseVal baseVal1 = new BaseVal();
        baseVal1.setValue("testname1");
        BaseVal baseVal2 = new BaseVal();
        baseVal2.setValue("testname2");
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        baseVals.add(baseVal1);
        baseVals.add(baseVal2);
        this.baseValDao.batchUpdate(baseVals);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                baseValDao.batchSelect((List<Long>) any);
                result = new ArrayList<BaseVal>();
            }
        };
        List<Long> baseValIds = new ArrayList<Long>();
        baseValIds.add(1L);
        List<BaseVal> ret = baseValDao.batchSelect(baseValIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                baseValDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> baseValIds = new ArrayList<Long>();
        baseValIds.add(2L);
        baseValIds.add(3L);
        this.baseValDao.batchDelete(baseValIds);
    }
}
