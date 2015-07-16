package com.baidu.jinbao.innerapi.jinbaocommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantRouterDao;

@Service
public class MerchantRouterServiceImplTest {
    @Tested
    private MerchantRouterServiceImpl merchantRouterService;

    @Injectable
    private MerchantRouterDao merchantRouterDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.selectByPrimaryKey(anyLong);
                result = new MerchantRouter();
            }
        };
        MerchantRouter ret = merchantRouterService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.batchInsert((List<MerchantRouter>) any);
            }
        };
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
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.batchUpdate((List<MerchantRouter>) any);
            }
        };
        List<MerchantRouter> merchantRouterList = new ArrayList<MerchantRouter>();
        MerchantRouter merchantRouter1 = new MerchantRouter();
        MerchantRouter merchantRouter2 = new MerchantRouter();
        merchantRouter1.setId(1L);
        merchantRouter2.setMerchantId(3L);
        merchantRouter2.setUsedShards("1001_1002");
        merchantRouter2.setId(2L);
        merchantRouter2.setMerchantId(4L);
        merchantRouter2.setUsedShards("1001_1002");
        merchantRouterList.add(merchantRouter1);
        merchantRouterList.add(merchantRouter2);
        this.merchantRouterDao.batchUpdate(merchantRouterList);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.batchSelect((List<Long>) any);
                result = new ArrayList<MerchantRouter>();
            }
        };
        List<Long> merchantRouterIds = new ArrayList<Long>();
        merchantRouterIds.add(1L);
        List<MerchantRouter> ret = merchantRouterDao.batchSelect(merchantRouterIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> merchantRouterIds = new ArrayList<Long>();
        merchantRouterIds.add(2L);
        merchantRouterIds.add(3L);
        this.merchantRouterDao.batchDelete(merchantRouterIds);
    }

    @Test
    public void testGetRecordsByMerchantid() {
        new NonStrictExpectations() {
            {
                merchantRouterDao.selectByMerchantid((Long) any);
                result = new ArrayList<MerchantRouter>();
            }
        };
        Long merchantid = 1001L;
        List<MerchantRouter> ret = this.merchantRouterDao.selectByMerchantid(merchantid);
        Assert.assertNotNull(ret);
    }
}
