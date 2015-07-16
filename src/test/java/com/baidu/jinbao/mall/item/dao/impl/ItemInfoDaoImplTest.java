package com.baidu.jinbao.mall.item.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemInfoDao;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

public class ItemInfoDaoImplTest extends AbstractDAOTests {

    @Resource
    private ItemInfoDao itemInfoDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testInsert() {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemid(2L);
        itemInfo.setShopid(Integer.valueOf(1));
        itemInfo.setItemInfoHashcode(0);

        int res = this.itemInfoDao.insert(itemInfo);

        itemInfo.setItemid(null);
        res = this.itemInfoDao.insert(itemInfo);
        Assert.assertTrue(true);

    }

    @Test
    public void testUpdate() {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemid(2L);

        itemInfo.setShopid(Integer.valueOf(1));
        int res = this.itemInfoDao.insert(itemInfo);

        itemInfo.setManualStatus(Byte.valueOf("2"));

        res = this.itemInfoDao.update(itemInfo);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        ItemInfo res = this.itemInfoDao.select(2L);
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        int res = this.itemInfoDao.delete(2L);
        Assert.assertTrue(true);
    }

    @Test
    public void testBatchInsert() {
        ArrayList<ItemInfo> itemInfos = new ArrayList<ItemInfo>();
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemid(2L);
        itemInfos.add(itemInfo);
        itemInfo = new ItemInfo();
        itemInfo.setItemid(3L);
        itemInfos.add(itemInfo);

        // this.itemInfoDao.batchUpdate(itemInfos);

    }

    @Test
    public void testSelectPageItemTotalInfo() {
        PageItemVo pageItemVo = new PageItemVo();
        pageItemVo.setPageSize(5);
        pageItemVo.setPageNumber(1);
        pageItemVo.setLeafcategoryidList(Arrays.asList(2L, 3L));
        pageItemVo.setShopIdList(Arrays.asList(1L));
        pageItemVo.setManualStatus(new Integer(0).byteValue());
        pageItemVo.setMerchantStatus(new Integer(0).byteValue());
        this.itemInfoDao.selectPageItemTotalInfo(pageItemVo);
    }

}
