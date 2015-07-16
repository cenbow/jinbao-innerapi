package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.dao.BaseCmPropertyDao;

@Service
public class BaseCmPropertyServiceImplTest {
    @Tested
    private BaseCmPropertyServiceImpl baseCmPropertyService;

    @Injectable
    private BaseCmPropertyDao baseCmPropertyDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                baseCmPropertyDao.selectByPrimaryKey(anyLong);
                result = new BaseCmProperty();
            }
        };
        BaseCmProperty ret = baseCmPropertyService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                baseCmPropertyDao.batchInsert((List<BaseCmProperty>) any);
            }
        };
        List<BaseCmProperty> baseCmProperties = new ArrayList<BaseCmProperty>();
        BaseCmProperty baseCmProperty1 = new BaseCmProperty();
        baseCmProperty1.setName("testname1");
        BaseCmProperty baseCmProperty2 = new BaseCmProperty();
        baseCmProperty2.setName("testname2");
        baseCmProperties.add(baseCmProperty1);
        baseCmProperties.add(baseCmProperty2);
        this.baseCmPropertyDao.batchInsert(baseCmProperties);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                baseCmPropertyDao.batchUpdate((List<BaseCmProperty>) any);
            }
        };
        List<BaseCmProperty> baseCmProperties = new ArrayList<BaseCmProperty>();
        BaseCmProperty baseCmProperty1 = new BaseCmProperty();
        baseCmProperty1.setName("testname2");
        BaseCmProperty baseCmProperty2 = new BaseCmProperty();
        baseCmProperty2.setName("testname3");
        baseCmProperties.add(baseCmProperty1);
        baseCmProperties.add(baseCmProperty2);
        this.baseCmPropertyDao.batchUpdate(baseCmProperties);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                baseCmPropertyDao.batchSelect((List<Long>) any);
                result = new ArrayList<BaseCmProperty>();
            }
        };
        List<Long> baseCmPropertyIds = new ArrayList<Long>();
        baseCmPropertyIds.add(1L);
        List<BaseCmProperty> ret = baseCmPropertyDao.batchSelect(baseCmPropertyIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                baseCmPropertyDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> baseCmPropertyIds = new ArrayList<Long>();
        baseCmPropertyIds.add(2L);
        baseCmPropertyIds.add(3L);
        this.baseCmPropertyDao.batchDelete(baseCmPropertyIds);
    }
}
