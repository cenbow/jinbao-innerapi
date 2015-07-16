package com.baidu.jinbao.mall.item.dao.impl;

import javax.annotation.Resource;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.item.dao.ItemDescriptionDao;

public class ItemDescriptionDaoImplTest extends AbstractDAOTests {
    @Resource
    private ItemDescriptionDao itemDescription;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testInsert() {
        ItemDescription itemDescription = new ItemDescription();
        itemDescription.setItemid(2L);
        itemDescription.setItemDesc("2".getBytes());
        itemDescription.setItemDescOri("3".getBytes());
        itemDescription.setPdMd5("");
        itemDescription.setShopid(Integer.valueOf(1));
        int res = this.itemDescription.insert(itemDescription);

        itemDescription.setId(null);
        res = this.itemDescription.insert(itemDescription);
        Assert.assertTrue(true);

    }

    @Test
    public void testUpdate() {
        ItemDescription itemDescription = new ItemDescription();
        itemDescription.setItemid(2L);
        itemDescription.setItemDesc("2".getBytes());
        itemDescription.setItemDescOri("3".getBytes());
        itemDescription.setPdMd5("");
        itemDescription.setShopid(Integer.valueOf(1));
        int res = this.itemDescription.insert(itemDescription);

        itemDescription.setPdMd5("asd");
        ;

        res = this.itemDescription.update(itemDescription);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        ItemDescription res = this.itemDescription.select(2L);
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        int res = this.itemDescription.delete(2L);
        Assert.assertTrue(true);
    }

}
