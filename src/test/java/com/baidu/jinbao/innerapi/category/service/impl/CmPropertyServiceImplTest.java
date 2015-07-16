package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.CmProperty;
import com.baidu.jinbao.innerapi.category.dao.CmPropertyDao;

@Service
public class CmPropertyServiceImplTest {
    @Tested
    private CmPropertyServiceImpl cmPropertyService;

    @Injectable
    private CmPropertyDao cmPropertyDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                cmPropertyDao.selectByPrimaryKey(anyLong);
                result = new CmProperty();
            }
        };
        CmProperty ret = cmPropertyService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                cmPropertyDao.batchInsert((List<CmProperty>) any);
            }
        };
        List<CmProperty> cmProperties = new ArrayList<CmProperty>();
        CmProperty cmProperty1 = new CmProperty();
        cmProperty1.setBasePid(1);
        CmProperty cmProperty2 = new CmProperty();
        cmProperty2.setBasePid(2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        this.cmPropertyDao.batchInsert(cmProperties);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                cmPropertyDao.batchUpdate((List<CmProperty>) any);
            }
        };
        List<CmProperty> cmProperties = new ArrayList<CmProperty>();
        CmProperty cmProperty1 = new CmProperty();
        cmProperty1.setBasePid(1);
        CmProperty cmProperty2 = new CmProperty();
        cmProperty2.setBasePid(2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        cmProperties.add(cmProperty1);
        cmProperties.add(cmProperty2);
        this.cmPropertyDao.batchUpdate(cmProperties);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                cmPropertyDao.batchSelect((List<Long>) any);
                result = new ArrayList<CmProperty>();
            }
        };
        List<Long> cmPropertyIds = new ArrayList<Long>();
        cmPropertyIds.add(1L);
        List<CmProperty> ret = cmPropertyDao.batchSelect(cmPropertyIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                cmPropertyDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> cmPropertyIds = new ArrayList<Long>();
        cmPropertyIds.add(2L);
        cmPropertyIds.add(3L);
        this.cmPropertyDao.batchDelete(cmPropertyIds);
    }
}
