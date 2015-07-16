package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.dao.BasePropertyDao;

@Service
public class BasePropertyServiceImplTest {
    @Tested
    private BasePropertyServiceImpl basePropertyService;

    @Injectable
    private BasePropertyDao basePropertyDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                basePropertyDao.selectByPrimaryKey(anyLong);
                result = new BaseProperty();
            }
        };
        BaseProperty ret = basePropertyService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                basePropertyDao.batchInsert((List<BaseProperty>) any);
            }
        };
        List<BaseProperty> baseProperties = new ArrayList<BaseProperty>();
        BaseProperty baseProperty1 = new BaseProperty();
        baseProperty1.setPropertyName("testname1");
        BaseProperty baseProperty2 = new BaseProperty();
        baseProperty2.setPropertyName("testname2");
        baseProperties.add(baseProperty1);
        baseProperties.add(baseProperty2);
        this.basePropertyDao.batchInsert(baseProperties);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                basePropertyDao.batchUpdate((List<BaseProperty>) any);
            }
        };
        List<BaseProperty> baseProperties = new ArrayList<BaseProperty>();
        BaseProperty baseProperty1 = new BaseProperty();
        baseProperty1.setPropertyName("testname1");
        BaseProperty baseProperty2 = new BaseProperty();
        baseProperty2.setPropertyName("testname2");
        baseProperties.add(baseProperty1);
        baseProperties.add(baseProperty2);
        baseProperties.add(baseProperty1);
        baseProperties.add(baseProperty2);
        this.basePropertyDao.batchUpdate(baseProperties);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                basePropertyDao.batchSelect((List<Long>) any);
                result = new ArrayList<BaseProperty>();
            }
        };
        List<Long> basePropertyIds = new ArrayList<Long>();
        basePropertyIds.add(1L);
        List<BaseProperty> ret = basePropertyDao.batchSelect(basePropertyIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                basePropertyDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> basePropertyIds = new ArrayList<Long>();
        basePropertyIds.add(2L);
        basePropertyIds.add(3L);
        this.basePropertyDao.batchDelete(basePropertyIds);
    }
}
