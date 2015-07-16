package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;
import com.baidu.jinbao.innerapi.category.dao.BaseCmValueDao;

@Service
public class BaseCmValueServiceImplTest {
    @Tested
    private BaseCmValueServiceImpl baseCmValueService;

    @Injectable
    private BaseCmValueDao baseCmValueDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                baseCmValueDao.selectByPrimaryKey(anyLong);
                result = new BaseCmValue();
            }
        };
        BaseCmValue ret = baseCmValueService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                baseCmValueDao.batchInsert((List<BaseCmValue>) any);
            }
        };
        List<BaseCmValue> baseCmValues = new ArrayList<BaseCmValue>();
        BaseCmValue baseCmValue1 = new BaseCmValue();
        baseCmValue1.setValue("test1");
        BaseCmValue baseCmValue2 = new BaseCmValue();
        baseCmValue2.setValue("test2");
        baseCmValues.add(baseCmValue1);
        baseCmValues.add(baseCmValue2);
        this.baseCmValueDao.batchInsert(baseCmValues);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                baseCmValueDao.batchUpdate((List<BaseCmValue>) any);
            }
        };
        List<BaseCmValue> baseCmValues = new ArrayList<BaseCmValue>();
        BaseCmValue baseCmValue1 = new BaseCmValue();
        baseCmValue1.setValue("test1");
        BaseCmValue baseCmValue2 = new BaseCmValue();
        baseCmValue2.setValue("test2");
        baseCmValues.add(baseCmValue1);
        baseCmValues.add(baseCmValue2);
        this.baseCmValueDao.batchUpdate(baseCmValues);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                baseCmValueDao.batchSelect((List<Long>) any);
                result = new ArrayList<BaseCmValue>();
            }
        };
        List<Long> baseCmValueIds = new ArrayList<Long>();
        baseCmValueIds.add(1L);
        List<BaseCmValue> ret = baseCmValueDao.batchSelect(baseCmValueIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                baseCmValueDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> baseCmValueIds = new ArrayList<Long>();
        baseCmValueIds.add(2L);
        baseCmValueIds.add(3L);
        this.baseCmValueDao.batchDelete(baseCmValueIds);
    }
}
