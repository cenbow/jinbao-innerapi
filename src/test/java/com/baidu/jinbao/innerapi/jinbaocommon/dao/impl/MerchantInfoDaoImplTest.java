package com.baidu.jinbao.innerapi.jinbaocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantInfoDao;

public class MerchantInfoDaoImplTest extends AbstractDAOTests {
    @Resource
    private MerchantInfoDao merchantInfoDao;

    @Before
    public void setUp() {
        this.executeDatas("jinbaocommon/merchant_info_init.sql");
    }

    @Test
    public void testFindById() {
        MerchantInfo ret = this.merchantInfoDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<MerchantInfo> merchantInfoList = new ArrayList<MerchantInfo>();
        MerchantInfo merchantInfo1 = new MerchantInfo();
        merchantInfo1.setName("testname1");
        MerchantInfo merchantInfo2 = new MerchantInfo();
        merchantInfo2.setName("testname2");
        merchantInfoList.add(merchantInfo1);
        merchantInfoList.add(merchantInfo2);
        this.merchantInfoDao.batchInsert(merchantInfoList);
        MerchantInfo ret = this.merchantInfoDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname1"));
        ret = this.merchantInfoDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getName().equals("testname2"));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<MerchantInfo> merchantInfoList = new ArrayList<MerchantInfo>();
        MerchantInfo merchantInfo1 = new MerchantInfo();
        merchantInfo1.setName("testname1");
        MerchantInfo merchantInfo2 = new MerchantInfo();
        merchantInfo2.setName("testname2");
        merchantInfoList.add(merchantInfo1);
        merchantInfoList.add(merchantInfo2);
        this.merchantInfoDao.batchInsert(merchantInfoList);
        merchantInfo1.setId(1L);
        merchantInfo1.setName("testname3");
        merchantInfo2.setId(2L);
        merchantInfo2.setName("testname4");
        this.merchantInfoDao.batchUpdate(merchantInfoList);
    }

    @Test
    public void testBatchSelect() {
        List<Long> merchantInfoIds = new ArrayList<Long>();
        merchantInfoIds.add(1L);
        List<MerchantInfo> ret = merchantInfoDao.batchSelect(merchantInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> merchantInfoIds = new ArrayList<Long>();
        merchantInfoIds.add(2L);
        merchantInfoIds.add(3L);
        List<MerchantInfo> ret = merchantInfoDao.batchSelect(merchantInfoIds);
        Assert.assertNotNull(ret);
        this.merchantInfoDao.batchDelete(merchantInfoIds);
        ret = merchantInfoDao.batchSelect(merchantInfoIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
