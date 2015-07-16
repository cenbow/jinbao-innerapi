package com.baidu.jinbao.mall.item.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.dao.ItemAttributeDao;

public class ItemAttributeDaoImplTest extends AbstractDAOTests {
    @Resource
    private ItemAttributeDao itemAttributeDao;

    @Test
    public void testInsert() {
        ItemAttribute itemAttribute = new ItemAttribute();
        System.out.println(itemAttribute.getId());

        itemAttribute.setItemid(2L);
        itemAttribute.setPropertyId("1");
        itemAttribute.setPropertyMd5("2");
        itemAttribute.setPropertyValues("3");
        itemAttribute.setUpdatetime(new Date());
        itemAttribute.setAddtime(new Date());

        int res = this.itemAttributeDao.insert(itemAttribute);

        itemAttribute.setId(null);
        itemAttribute.setItemid(3L);
        itemAttribute.setPropertyId("2");
        itemAttribute.setPropertyMd5("3");
        itemAttribute.setPropertyValues("4");
        itemAttribute.setUpdatetime(new Date());
        itemAttribute.setAddtime(new Date());
        res = this.itemAttributeDao.insert(itemAttribute);
        Assert.assertTrue(true);

    }

    @Test
    public void testUpdate() {
        ItemAttribute itemAttribute = new ItemAttribute();
        itemAttribute.setItemid(2L);
        itemAttribute.setPropertyId("1");
        itemAttribute.setPropertyMd5("4");
        itemAttribute.setPropertyValues("6");
        itemAttribute.setUpdatetime(new Date());
        itemAttribute.setAddtime(new Date());

        int res = this.itemAttributeDao.update(itemAttribute);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        ItemAttribute res = this.itemAttributeDao.selectByItemId(2L);
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        int res = this.itemAttributeDao.delete(2L);
        Assert.assertTrue(true);
    }

}
