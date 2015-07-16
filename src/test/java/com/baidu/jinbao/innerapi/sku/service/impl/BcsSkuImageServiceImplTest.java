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

import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.BcsSkuImageDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class BcsSkuImageServiceImplTest {
    @Tested
    private BcsSkuImageServiceImpl bcsSkuImageService;

    @Injectable
    private BcsSkuImageDao bcsSkuImageDao;

    @Injectable
    private SkuInfoDao skuInfoDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                bcsSkuImageDao.batchInsert(0, (List<BcsSkuImage>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<BcsSkuImage> bcsSkuImageList = new ArrayList<BcsSkuImage>();
        BcsSkuImage item = new BcsSkuImage();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        bcsSkuImageList.add(item);
        this.bcsSkuImageService.insertRecords("db0_0", bcsSkuImageList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                bcsSkuImageDao.batchUpdate(0, (List<BcsSkuImage>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<BcsSkuImage> bcsSkuImageList = new ArrayList<BcsSkuImage>();
        BcsSkuImage item = new BcsSkuImage();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        bcsSkuImageList.add(item);
        this.bcsSkuImageService.updateRecords("db0_0", bcsSkuImageList);

        Assert.assertTrue(true);
    }
    
    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                bcsSkuImageDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<BcsSkuImage>();
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
        this.bcsSkuImageService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                bcsSkuImageDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<BcsSkuImage>();
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
        this.bcsSkuImageService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
}
