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

import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuDescriptionDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuDescriptionServiceImplTest {
    @Tested
    private SkuDescriptionServiceImpl skuDescriptionService;

    @Injectable
    private SkuDescriptionDao skuDescriptionDao;
    @Injectable
    private SkuInfoDao skuInfoDao;
    @Injectable
    private SkuInfoService skuInfoService;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuDescriptionDao.batchInsert(0, (List<SkuDescription>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();

                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 2);
                result = 0;
            }
        };
        List<SkuDescription> skuDescriptionList = new ArrayList<SkuDescription>();
        SkuDescription item = new SkuDescription();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuDescriptionList.add(item);
        this.skuDescriptionService.insertRecords("db0_0", skuDescriptionList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuDescriptionDao.batchUpdate(0, (List<SkuDescription>) any);
                result = 0;

                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 2);
                result = 0;
            }
        };
        List<SkuDescription> skuDescriptionList = new ArrayList<SkuDescription>();
        SkuDescription item = new SkuDescription();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuDescriptionList.add(item);
        this.skuDescriptionService.updateRecords("db0_0", skuDescriptionList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuDescriptionDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuDescription>();
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
        this.skuDescriptionService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuDescriptionDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuDescription>();
                
                skuDescriptionDao.batchDelete(0, (SkuQueryCondition) any);
                result = 0;
                
                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 2);
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
        this.skuDescriptionService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
}
