package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyValueDao;

@Service
public class CmPropertyValueServiceImplTest {
    @Tested
    private CmPropertyValueServiceImpl cmPropertyValueService;

    @Injectable
    private CmPropertyValueDao cmPropertyValueDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cmPropertyValueDao.selectByPrimaryKey(anyLong);
                result = new CmPropertyValue();
            }
        };
        CmPropertyValue ret = cmPropertyValueService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cmPropertyValueDao.batchInsert((List<CmPropertyValue>) any);
            }
        };
        List<CmPropertyValue> cmPropertyValues = new ArrayList<CmPropertyValue>();
        CmPropertyValue cmPropertyValue1 = new CmPropertyValue();
        cmPropertyValue1.setCmPid(1);
        CmPropertyValue cmPropertyValue2 = new CmPropertyValue();
        cmPropertyValue2.setCmPid(2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        this.cmPropertyValueDao.batchInsert(cmPropertyValues);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cmPropertyValueDao.batchUpdate((List<CmPropertyValue>) any);
            }
        };
        List<CmPropertyValue> cmPropertyValues = new ArrayList<CmPropertyValue>();
        CmPropertyValue cmPropertyValue1 = new CmPropertyValue();
        cmPropertyValue1.setCmPid(1);
        CmPropertyValue cmPropertyValue2 = new CmPropertyValue();
        cmPropertyValue2.setCmPid(2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        cmPropertyValues.add(cmPropertyValue1);
        cmPropertyValues.add(cmPropertyValue2);
        this.cmPropertyValueDao.batchUpdate(cmPropertyValues);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cmPropertyValueDao.batchSelect((List<Long>) any);
                result = new ArrayList<CmPropertyValue>();
            }
        };
        List<Long> cmPropertyValueIds = new ArrayList<Long>();
        cmPropertyValueIds.add(1L);
        List<CmPropertyValue> ret = cmPropertyValueDao.batchSelect(cmPropertyValueIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cmPropertyValueDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cmPropertyValueIds = new ArrayList<Long>();
        cmPropertyValueIds.add(2L);
        cmPropertyValueIds.add(3L);
        this.cmPropertyValueDao.batchDelete(cmPropertyValueIds);
    }
}
