package com.baidu.jinbao.innerapi.sku.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuAttributeDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuAttributeServiceImplTest {
    @Tested
    private SkuAttributeServiceImpl skuAttributeService;

    @Injectable
    private SkuAttributeDao skuAttributeDao;
    @Injectable
    private SkuInfoDao skuInfoDao;
    @Injectable
    private SkuInfoService skuInfoService;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuAttributeDao.batchInsert(0, (List<SkuAttribute>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();

                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 1);
                result = 1;
            }
        };
        List<SkuAttribute> skuAttributeList = new ArrayList<SkuAttribute>();
        SkuAttribute item = new SkuAttribute();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuAttributeList.add(item);
        this.skuAttributeService.insertRecords("db0_0", skuAttributeList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuAttributeDao.batchUpdate(0, (List<SkuAttribute>) any);
                result = 0;

                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 1);
                result = 0;
            }
        };
        List<SkuAttribute> skuAttributeList = new ArrayList<SkuAttribute>();
        SkuAttribute item = new SkuAttribute();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuAttributeList.add(item);
        this.skuAttributeService.updateRecords("db0_0", skuAttributeList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuAttributeDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuAttribute>();
            }
        };
        SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
        List<Long> idList = new ArrayList<Long>();
        idList.add(1L);
        List<Long> skuInnerIdList = new ArrayList<Long>();
        skuInnerIdList.add(1L);
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add("1001_2001");
        // skuQueryCondition.setIdList(idList);
        skuQueryCondition.setSkuInnerIdList(skuInnerIdList);
        skuQueryCondition.setSkuIdList(skuIdList);
        this.skuAttributeService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuAttributeDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuAttribute>();
                
                skuAttributeDao.batchDelete(0, (SkuQueryCondition) any);
                result = 0;
                
                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 1);
                result = 0;
            }
        };
        SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
        List<Long> idList = new ArrayList<Long>();
        idList.add(1L);
        List<Long> skuInnerIdList = new ArrayList<Long>();
        skuInnerIdList.add(1L);
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add("1001_2001");
        skuQueryCondition.setIdList(idList);
        skuQueryCondition.setSkuInnerIdList(skuInnerIdList);
        skuQueryCondition.setSkuIdList(skuIdList);
        this.skuAttributeService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
}
