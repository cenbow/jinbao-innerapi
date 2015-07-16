package com.baidu.jinbao.innerapi.jinbaocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantRouterDao;

public class MerchantRouterDaoImplTest extends AbstractDAOTests {
    @Resource
    private MerchantRouterDao merchantRouterDao;

    @Before
    public void setUp() {
        this.executeDatas("jinbaocommon/merchant_router_init.sql");
    }

    @Test
    public void testFindById() {
        MerchantRouter ret = this.merchantRouterDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<MerchantRouter> merchantRouterList = new ArrayList<MerchantRouter>();
        MerchantRouter merchantRouter1 = new MerchantRouter();
        merchantRouter1.setMerchantId(1L);
        merchantRouter1.setUsedShards("1001_1002");
        MerchantRouter merchantRouter2 = new MerchantRouter();
        merchantRouter2.setMerchantId(2L);
        merchantRouter2.setUsedShards("1001_1002");
        merchantRouterList.add(merchantRouter1);
        merchantRouterList.add(merchantRouter2);
        this.merchantRouterDao.batchInsert(merchantRouterList);
        MerchantRouter ret = this.merchantRouterDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getMerchantId().equals(1L));
        ret = this.merchantRouterDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getMerchantId().equals(2L));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<MerchantRouter> merchantRouterList = new ArrayList<MerchantRouter>();
        MerchantRouter merchantRouter1 = new MerchantRouter();
        merchantRouter1.setMerchantId(1L);
        merchantRouter1.setUsedShards("1001_1002");
        MerchantRouter merchantRouter2 = new MerchantRouter();
        merchantRouter2.setMerchantId(2L);
        merchantRouter2.setUsedShards("1001_1002");
        merchantRouterList.add(merchantRouter1);
        merchantRouterList.add(merchantRouter2);
        this.merchantRouterDao.batchInsert(merchantRouterList);
        merchantRouter1.setId(1L);
        merchantRouter2.setMerchantId(3L);
        merchantRouter2.setUsedShards("1001_1002");
        merchantRouter2.setId(2L);
        merchantRouter2.setMerchantId(4L);
        merchantRouter2.setUsedShards("1001_1002");
        this.merchantRouterDao.batchUpdate(merchantRouterList);
    }

    @Test
    public void testBatchSelect() {
        List<Long> merchantRouterIds = new ArrayList<Long>();
        merchantRouterIds.add(1L);
        List<MerchantRouter> ret = merchantRouterDao.batchSelect(merchantRouterIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> merchantRouterIds = new ArrayList<Long>();
        merchantRouterIds.add(2L);
        merchantRouterIds.add(3L);
        List<MerchantRouter> ret = merchantRouterDao.batchSelect(merchantRouterIds);
        Assert.assertNotNull(ret);
        this.merchantRouterDao.batchDelete(merchantRouterIds);
        ret = merchantRouterDao.batchSelect(merchantRouterIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }

    @Test
    public void testSelectByMerchantid() {
        Long merchantid = 1001L;
        List<MerchantRouter> ret = this.merchantRouterDao.selectByMerchantid(merchantid);
        Assert.assertNotNull(ret);
    }
}
