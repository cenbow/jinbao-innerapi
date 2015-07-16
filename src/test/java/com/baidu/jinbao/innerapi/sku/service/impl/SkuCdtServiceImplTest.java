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

import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCdtDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuCdtServiceImplTest {
    @Tested
    private SkuCdtServiceImpl skuCdtService;

    @Injectable
    private SkuCdtDao skuCdtDao;
    @Injectable
    private SkuInfoDao skuInfoDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuCdtDao.batchInsert(0, (List<SkuCdt>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        SkuCdt item = new SkuCdt();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCdtList.add(item);
        this.skuCdtService.insertRecords("db0_0", skuCdtList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuCdtDao.batchUpdate(0, (List<SkuCdt>) any);
            }
        };
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        SkuCdt item = new SkuCdt();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCdtList.add(item);
        this.skuCdtService.updateRecords("db0_0", skuCdtList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsertRecord() {
        new NonStrictExpectations() {
            {
                skuCdtDao.batchUpdate(0, (List<SkuCdt>) any);
                result = 0;

                skuCdtDao.batchInsert(0, (List<SkuCdt>) any);
                result = 0;

                skuCdtDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCdt>();
            }
        };
        SkuCdt item = new SkuCdt();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setClassificationtype(new Integer(0).byteValue());
        item.setLeafcategoryid(0L);
        this.skuCdtService.updateInsertRecord("db0_0", item);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuCdtDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCdt>();
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
        this.skuCdtService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuCdtDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCdt>();
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
        this.skuCdtService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
}
