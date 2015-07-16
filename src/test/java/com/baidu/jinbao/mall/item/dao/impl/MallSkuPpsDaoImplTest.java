package com.baidu.jinbao.mall.item.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.dao.MallSkuPpsDao;

public class MallSkuPpsDaoImplTest extends AbstractDAOTests {
    @Resource
    private MallSkuPpsDao mallSkuPpsDao;

    @Test
    public void testInsert() {
        MallSkuPps mallSkuPps = new MallSkuPps();
        mallSkuPps.setShopid(Integer.valueOf(1));

        mallSkuPpsDao.insert(mallSkuPps);
    }

    @Test
    public void testDelete() {
        MallSkuPps mallSkuPps = new MallSkuPps();
        mallSkuPps.setShopid(Integer.valueOf(1));
        mallSkuPps.setPpsMd5("555");
        mallSkuPpsDao.insert(mallSkuPps);

        mallSkuPpsDao.delete("555");
    }

    @Test
    public void testUpdate() {
        MallSkuPps mallSkuPps = new MallSkuPps();
        mallSkuPps.setShopid(Integer.valueOf(1));
        mallSkuPps.setPpsMd5("555");
        mallSkuPpsDao.insert(mallSkuPps);
        mallSkuPps.setPpsMd5("666");

        mallSkuPpsDao.update(mallSkuPps);
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        MallSkuPps mallSkuPps = new MallSkuPps();
        mallSkuPps.setPpsMd5("555");
        mallSkuPps.setMerchantid(2L);
        mallSkuPpsDao.insert(mallSkuPps);
        mallSkuPps.setPpsMd5("666");
        mallSkuPps.setId(null);
        mallSkuPps.setMerchantid(3L);
        mallSkuPpsDao.insert(mallSkuPps);

        List<MallSkuPps> testList = new ArrayList<MallSkuPps>();
        mallSkuPps = new MallSkuPps();
        mallSkuPps.setPpsMd5("555");
        mallSkuPps.setMerchantid(5L);

        testList.add(mallSkuPps);
        mallSkuPps = new MallSkuPps();
        mallSkuPps.setPpsMd5("666");
        mallSkuPps.setMerchantid(6L);
        testList.add(mallSkuPps);

        System.out.println(testList.size());
        for (MallSkuPps tt : testList) {
            System.out.println(tt.getPpsMd5() + "\t" + tt.getMerchantid());
        }

        mallSkuPpsDao.batchUpdate(testList);

    }
    
    @Test
    public void testBatcheInsert() {
        List<MallSkuPps> insertList = new ArrayList<MallSkuPps>();
        MallSkuPps mallSkuPps = new MallSkuPps();
        mallSkuPps.setShopid(Integer.valueOf(1));
        insertList.add(mallSkuPps);
        mallSkuPpsDao.batchInsert(insertList);
    }
}
