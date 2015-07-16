package com.baidu.jinbao.innerapi.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;
import com.baidu.jinbao.innerapi.category.dao.PropertyValueMapDao;

@Service
public class PropertyValueMapServiceImplTest {
    @Tested
    private PropertyValueMapServiceImpl propertyValueMapService;

    @Injectable
    private PropertyValueMapDao propertyValueMapDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                propertyValueMapDao.selectByPrimaryKey(anyLong);
                result = new PropertyValueMap();
            }
        };
        PropertyValueMap ret = propertyValueMapService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                propertyValueMapDao.batchInsert((List<PropertyValueMap>) any);
            }
        };
        List<PropertyValueMap> propertyValueMaps = new ArrayList<PropertyValueMap>();
        PropertyValueMap propertyValueMap1 = new PropertyValueMap();
        propertyValueMap1.setCmVid(1);
        PropertyValueMap propertyValueMap2 = new PropertyValueMap();
        propertyValueMap2.setCmVid(2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        this.propertyValueMapDao.batchInsert(propertyValueMaps);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                propertyValueMapDao.batchUpdate((List<PropertyValueMap>) any);
            }
        };
        List<PropertyValueMap> propertyValueMaps = new ArrayList<PropertyValueMap>();
        PropertyValueMap propertyValueMap1 = new PropertyValueMap();
        propertyValueMap1.setCmVid(1);
        PropertyValueMap propertyValueMap2 = new PropertyValueMap();
        propertyValueMap2.setCmVid(2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        propertyValueMaps.add(propertyValueMap1);
        propertyValueMaps.add(propertyValueMap2);
        this.propertyValueMapDao.batchUpdate(propertyValueMaps);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                propertyValueMapDao.batchSelect((List<Long>) any);
                result = new ArrayList<PropertyValueMap>();
            }
        };
        List<Long> propertyValueMapIds = new ArrayList<Long>();
        propertyValueMapIds.add(1L);
        List<PropertyValueMap> ret = propertyValueMapDao.batchSelect(propertyValueMapIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                propertyValueMapDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> propertyValueMapIds = new ArrayList<Long>();
        propertyValueMapIds.add(2L);
        propertyValueMapIds.add(3L);
        this.propertyValueMapDao.batchDelete(propertyValueMapIds);
    }
}
