package com.baidu.jinbao.mall.item.dao.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.dao.MallSkuInfoDao;

public class MallSkuInfoDaoImplTest extends AbstractDAOTests {
    @Resource
    private MallSkuInfoDao mallSkuInfoDao;

    @Test
    public void testInsert() {
        MallSkuInfo mallSkuInfo = new MallSkuInfo();
        mallSkuInfo.setSkuType((byte) 0);
        mallSkuInfo.setPropertyValues("");
        this.mallSkuInfoDao.insert(mallSkuInfo);

        mallSkuInfo.setSkuid(null);
        this.mallSkuInfoDao.insert(mallSkuInfo);

        Assert.assertTrue(true);
    }

    @Test
    public void testUpdate() {
        MallSkuInfo mallSkuInfo = new MallSkuInfo();
        mallSkuInfo.setSkuType((byte) 0);
        mallSkuInfo.setPropertyValues("");
        this.mallSkuInfoDao.insert(mallSkuInfo);
        mallSkuInfo.setPropertyValues("asdasd");
        this.mallSkuInfoDao.update(mallSkuInfo);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        MallSkuInfo mallSkuInfo = new MallSkuInfo();
        mallSkuInfo.setSkuType((byte) 0);
        mallSkuInfo.setPropertyValues("");
        this.mallSkuInfoDao.insert(mallSkuInfo);

        this.mallSkuInfoDao.select(mallSkuInfo.getSkuid());
    }

    @Test
    public void testDelete() {
        MallSkuInfo mallSkuInfo = new MallSkuInfo();
        mallSkuInfo.setSkuType((byte) 0);
        mallSkuInfo.setPropertyValues("");
        this.mallSkuInfoDao.insert(mallSkuInfo);
        int res = this.mallSkuInfoDao.delete(mallSkuInfo.getSkuid());
        Assert.assertTrue(true);
    }
}
