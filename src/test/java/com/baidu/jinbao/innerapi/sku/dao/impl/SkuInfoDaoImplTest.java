package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * SkuInfoDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class SkuInfoDaoImplTest extends AbstractDAOTests {
    @Resource
    private SkuInfoDao skuInfoDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/sku_info_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        SkuInfo item = new SkuInfo();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuInfoList.add(item);

        this.skuInfoDao.batchInsert(0, skuInfoList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        SkuInfo item = new SkuInfo();
        item.setSkuInnerid(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setUpdatetime(new Date());
        skuInfoList.add(item);

        this.skuInfoDao.batchUpdate(0, skuInfoList);

        Assert.assertTrue(true);
    }

    @Test
    public void testbatchSelect() {
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
        this.skuInfoDao.batchSelect(0, skuQueryCondition);
        Assert.assertTrue(true);
    }

    @Test
    public void testbatchDeletes() {
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
        this.skuInfoDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }

    @Test
    public void testInsertWithSplitNumber() {
        SkuInfo item = new SkuInfo();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setUpdatetime(new Date());
        item.setUpc(1L);
        this.skuInfoDao.insertWithSplitNumber(0, item);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelectBySkuHashkey() {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        skuHashKeyList.add((long) 100);

        this.skuInfoDao.batchSelectBySkuHashkey(0, skuHashKeyList);

        Assert.assertTrue(true);
    }
    
    @Test
    public void testUpdateBySkuId() {
        SkuInfo item = new SkuInfo();
        item.setSkuInnerid(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setUpdatetime(new Date());

        this.skuInfoDao.updateBySkuId(0, item);

        Assert.assertTrue(true);
    }
}
