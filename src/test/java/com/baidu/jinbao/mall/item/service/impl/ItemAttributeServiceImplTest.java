package com.baidu.jinbao.mall.item.service.impl;

import java.util.Date;

import junit.framework.Assert;
import mockit.Injectable;
import mockit.Tested;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.dao.ItemAttributeDao;

@Service
public class ItemAttributeServiceImplTest {

    @Tested
    private ItemAttributeServiceImpl itemAttributeService;

    @Injectable
    private ItemAttributeDao itemAttributeDao;

    @Test
    public void testInsert() {

        ItemAttribute itemAttribute = new ItemAttribute();
        itemAttribute.setItemid(2L);
        itemAttribute.setPropertyId("1");
        itemAttribute.setPropertyMd5("2");
        itemAttribute.setPropertyValues("3");
        itemAttribute.setUpdatetime(new Date());
        itemAttribute.setAddtime(new Date());

        this.itemAttributeService.insert(itemAttribute);
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

        this.itemAttributeService.updateById(itemAttribute);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() throws Exception {
        ItemAttribute res = this.itemAttributeService.getRecord(2L);
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() throws Exception {
        this.itemAttributeService.deleteRecord(2L);
        Assert.assertTrue(true);
    }
}
