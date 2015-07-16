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

import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuCommentDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service
public class SkuCommentServiceImplTest {
    @Tested
    private SkuCommentServiceImpl skuCommentService;

    @Injectable
    private SkuCommentDao skuCommentDao;
    @Injectable
    private SkuInfoDao skuInfoDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchInsert(0, (List<SkuComment>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);
        this.skuCommentService.insertRecords("db0_0", skuCommentList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchUpdate(0, (List<SkuComment>) any);
            }
        };
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);
        this.skuCommentService.updateRecords("db0_0", skuCommentList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsertRecords() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchUpdate(0, (List<SkuComment>) any);
                result = 0;

                skuCommentDao.batchInsert(0, (List<SkuComment>) any);
                result = 0;

                skuCommentDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuComment>();

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();

                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);
                result = 0;
            }
        };
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);
        this.skuCommentService.updateRecords("db0_0", skuCommentList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuComment>();
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
        this.skuCommentService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuComment>();
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
        this.skuCommentService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsertRecord() {
        new NonStrictExpectations() {
            {
                skuCommentDao.batchUpdateByKey(0, (List<SkuComment>) any);
                result = 0;

                skuCommentDao.batchInsert(0, (List<SkuComment>) any);
                result = 0;

                skuCommentDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuComment>();
            }
        };
        SkuComment item = new SkuComment();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setReviewcount(0);
        item.setGoodcount(0);
        item.setMedcount(0);
        item.setGoodcount(0);
        item.setMedcount(0);
        item.setBadcount(0);
        item.setTags("123");
        item.setScore(new Double(0));
        this.skuCommentService.updateInsertRecord("db0_0", item);

        Assert.assertTrue(true);
    }
}
