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
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuCommentService;
import com.baidu.jinbao.innerapi.sku.service.SkuLevelService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jinbao.innerapi.sku.vo.SkuTotalInfoVo;

@Service
public class SkuInfoServiceImplTest {
    @Tested
    private SkuInfoServiceImpl skuInfoService;

    @Injectable
    private SkuCommentService skuCommentService;
    @Injectable
    private SkuLevelService skuLevelService;
    @Injectable
    private SkuInfoDao skuInfoDao;

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchInsert(0, (List<SkuInfo>) any);

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        SkuInfo item = new SkuInfo();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setUpdatetime(new Date());
        skuInfoList.add(item);
        this.skuInfoService.insertRecords("db0_0", skuInfoList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);

                skuInfoService.updateSignature("db0_0", (List<String>) any, (List<String>) any, 0);
                result = 0;
            }
        };
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        SkuInfo item = new SkuInfo();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuInfoList.add(item);
        this.skuInfoService.updateRecords("db0_0", skuInfoList);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuInfo>();
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
        this.skuInfoService.getRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchSelect(0, (SkuQueryCondition) any);
                result = new ArrayList<SkuInfo>();
                
                skuInfoDao.batchDelete(0, (SkuQueryCondition) any);
                result = 0;
                
                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);
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
        this.skuInfoService.deleteRecords("db0_0", skuQueryCondition);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testInsertSkuTotalInfo() {
        new NonStrictExpectations() {
            {
                skuInfoDao.insertWithSplitNumber(0, (SkuInfo) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();

                skuCommentService.insertRecords("db0_0", (List<SkuComment>) any);
                result = 1;

                skuCommentService.directInsertRecords("db0_0", (List<SkuComment>) any);
                result = 1;

                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);
                result = 1;
            }
        };
        SkuTotalInfoVo vo = new SkuTotalInfoVo();
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setAddtime(new Date());
        skuInfo.setSkuid("1001_2001");
        skuInfo.setUpdatetime(new Date());
        SkuComment skuComment = new SkuComment();
        skuComment.setAddtime(new Date());
        skuComment.setSkuid("1001_2001");
        skuComment.setUpdatetime(new Date());
        vo.setSkuInfo(skuInfo);
        vo.setSkuComment(skuComment);
        this.skuInfoService.insertSkuTotalInfo("db0_0", vo);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testInsertSkuTotalInfoList() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchInsert(0, (List<SkuInfo>) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();

                skuCommentService.insertRecords("db0_0", (List<SkuComment>) any);
                result = 1;

                skuCommentService.directInsertRecords("db0_0", (List<SkuComment>) any);
                result = 1;

                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);
                result = 1;
            }
        };
        SkuTotalInfoVo vo = new SkuTotalInfoVo();
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setAddtime(new Date());
        skuInfo.setSkuid("1001_2001");
        skuInfo.setUpdatetime(new Date());
        SkuComment skuComment = new SkuComment();
        skuComment.setAddtime(new Date());
        skuComment.setSkuid("1001_2001");
        skuComment.setUpdatetime(new Date());
        vo.setSkuInfo(skuInfo);
        vo.setSkuComment(skuComment);
        List<SkuTotalInfoVo> voList = new ArrayList<SkuTotalInfoVo>();
        voList.add(vo);
        this.skuInfoService.insertSkuTotalInfoList("db0_0", voList);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateInsert() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchInsert(0, (List<SkuInfo>) any);
                result = 0;

                skuInfoDao.batchUpdate(0, (List<SkuInfo>) any);
                result = 0;

                skuInfoDao.insertWithSplitNumber(0, (SkuInfo) any);
                result = 0;

                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
                
                skuLevelService.directInsertRecords("db0_0", (List<SkuLevel>) any);
                result = 0;
                
                skuLevelService.updateBySkuId("db0_0", (SkuLevel) any);
                result = 0;
            }
        };
        SkuTotalInfoVo vo = new SkuTotalInfoVo();
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setAddtime(new Date());
        skuInfo.setSkuid("1001_2001");
        skuInfo.setUpdatetime(new Date());
        SkuLevel skuLevel = new SkuLevel();
        skuLevel.setAddtime(new Date());
        skuLevel.setSkuid("1001_2001");
        skuLevel.setUpdatetime(new Date());
        vo.setSkuInfo(skuInfo);
        vo.setSkuLevel(skuLevel);
        this.skuInfoService.updateInsert("db0_0", vo);

        Assert.assertTrue(true);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetRecordsBySkuHashkey() {
        new NonStrictExpectations() {
            {
                skuInfoDao.batchSelectBySkuHashkey(0, (List<Long>) any);
                result = new ArrayList<SkuInfo>();
            }
        };
        List<Long> skuHashKeyList = new ArrayList<Long>();
        skuHashKeyList.add((long) 100);
        this.skuInfoService.getRecordsBySkuHashkey("db0_0", skuHashKeyList);

        Assert.assertTrue(true);
    }
}
