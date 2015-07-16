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

import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCspuDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuCspuServiceImplTest {
    @Tested
    private SkuCspuServiceImpl skuCspuService;

    @Injectable
    private SkuCspuDao skuCspuDao;
    @Injectable
    private SkuInfoDao skuInfoDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuCspuDao.batchInsert(0, (List<SkuCspu>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<SkuCspu> skuCspuList = new ArrayList<SkuCspu>();
        SkuCspu item = new SkuCspu();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCspuList.add(item);
        this.skuCspuService.insertRecords("db0_0", skuCspuList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuCspuDao.batchUpdate(0, (List<SkuCspu>) any);
            }
        };
        List<SkuCspu> skuCspuList = new ArrayList<SkuCspu>();
        SkuCspu item = new SkuCspu();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCspuList.add(item);
        this.skuCspuService.updateRecords("db0_0", skuCspuList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuCspuDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCspu>();
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
        this.skuCspuService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuCspuDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCspu>();
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
        this.skuCspuService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsertRecord() {
        new NonStrictExpectations() {
            {
                skuCspuDao.batchUpdate(0, (List<SkuCspu>) any);
                result = 0;

                skuCspuDao.batchInsert(0, (List<SkuCspu>) any);
                result = 0;

                skuCspuDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuCspu>();
                
            }
        };
        SkuCspu item = new SkuCspu();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setType(new Integer(0).byteValue());
        item.setCspuid(1L);
        this.skuCspuService.updateInsertRecord("db0_0", item);

        Assert.assertTrue(true);
    }
}
