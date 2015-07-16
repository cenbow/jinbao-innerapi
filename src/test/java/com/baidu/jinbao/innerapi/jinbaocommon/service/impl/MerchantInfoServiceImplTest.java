package com.baidu.jinbao.innerapi.jinbaocommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantInfoDao;

@Service
public class MerchantInfoServiceImplTest {
    @Tested
    private MerchantInfoServiceImpl merchantInfoService;

    @Injectable
    private MerchantInfoDao merchantInfoDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                merchantInfoDao.selectByPrimaryKey(anyLong);
                result = new MerchantInfo();
            }
        };
        MerchantInfo ret = merchantInfoService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                merchantInfoDao.batchInsert((List<MerchantInfo>) any);
            }
        };
        List<MerchantInfo> merchantInfoList = new ArrayList<MerchantInfo>();
        MerchantInfo merchantInfo1 = new MerchantInfo();
        merchantInfo1.setName("testname1");
        MerchantInfo merchantInfo2 = new MerchantInfo();
        merchantInfo2.setName("testname2");
        merchantInfoList.add(merchantInfo1);
        merchantInfoList.add(merchantInfo2);
        this.merchantInfoDao.batchInsert(merchantInfoList);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                merchantInfoDao.batchUpdate((List<MerchantInfo>) any);
            }
        };
        List<MerchantInfo> merchantInfoList = new ArrayList<MerchantInfo>();
        MerchantInfo merchantInfo1 = new MerchantInfo();
        merchantInfo1.setName("testname2");
        MerchantInfo merchantInfo2 = new MerchantInfo();
        merchantInfo2.setName("testname3");
        merchantInfoList.add(merchantInfo1);
        merchantInfoList.add(merchantInfo2);
        this.merchantInfoDao.batchUpdate(merchantInfoList);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                merchantInfoDao.batchSelect((List<Long>) any);
                result = new ArrayList<MerchantInfo>();
            }
        };
        List<Long> merchantInfoIds = new ArrayList<Long>();
        merchantInfoIds.add(1L);
        List<MerchantInfo> ret = merchantInfoDao.batchSelect(merchantInfoIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                merchantInfoDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> merchantInfoIds = new ArrayList<Long>();
        merchantInfoIds.add(2L);
        merchantInfoIds.add(3L);
        this.merchantInfoDao.batchDelete(merchantInfoIds);
    }
}
