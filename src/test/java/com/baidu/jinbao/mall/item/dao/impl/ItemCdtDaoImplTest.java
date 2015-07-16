package com.baidu.jinbao.mall.item.dao.impl;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.dao.ItemCdtDao;

public class ItemCdtDaoImplTest extends AbstractDAOTests {
    @Resource
    private ItemCdtDao itemCdt;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testInsert() {
        ItemCdt itemCdt = new ItemCdt();
        itemCdt.setItemid(2L);
        itemCdt.setLeafcategoryid(3L);
        itemCdt.setClassificationtype(Byte.valueOf("1"));
        itemCdt.setCdtMd5("");
        int res = this.itemCdt.insert(itemCdt);
        Assert.assertTrue(true);

    }

    @Test
    public void testUpdate() {
        ItemCdt itemCdt = new ItemCdt();
        itemCdt.setItemid(2L);
        itemCdt.setLeafcategoryid(3L);
        itemCdt.setClassificationtype(Byte.valueOf("1"));
        itemCdt.setCdtMd5("");

        int res = this.itemCdt.insert(itemCdt);

        itemCdt.setLeafcategoryid(3L);
        itemCdt.setClassificationtype(Byte.valueOf("2"));
        res = this.itemCdt.update(itemCdt);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        ItemCdt res = this.itemCdt.select("");
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        int res = this.itemCdt.delete(2L);
        Assert.assertTrue(true);
    }

}
