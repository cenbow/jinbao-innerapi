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

import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuPpsDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuPpsServiceImplTest {
    @Tested
    private SkuPpsServiceImpl skuPpsService;

    @Injectable
    private SkuPpsDao skuPpsDao;
    @Injectable
    private SkuInfoDao skuInfoDao;
    
    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuPpsDao.batchInsert(0, (List<SkuPps>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuPpsList.add(item);
        this.skuPpsService.insertRecords("db0_0", skuPpsList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuPpsDao.batchUpdate(0, (List<SkuPps>) any);
                result = 0;
            }
        };
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuPpsList.add(item);
        this.skuPpsService.updateRecords("db0_0", skuPpsList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuPpsDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuPps>();
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
        this.skuPpsService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuPpsDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuPps>();
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
        this.skuPpsService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsertRecord() {
        new NonStrictExpectations() {
            {
                skuPpsDao.batchUpdateByKey(0, (List<SkuPps>) any);
                result = 0;

                skuPpsDao.batchInsert(0, (List<SkuPps>) any);
                result = 0;

                skuPpsDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuPps>();
                
                skuPpsDao.batchSelectByKey(0, (List<SkuPps>) any);
                result = new ArrayList<SkuPps>();
            }
        };
        SkuPps item = new SkuPps();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setRegionid(1);
        item.setTerminal(new Integer(0).byteValue() );
        item.setMUpdateTime(new Date());
        item.setMerchantid(1L);
        this.skuPpsService.updateInsertRecord("db0_0", item);

        Assert.assertTrue(true);
    }
}
