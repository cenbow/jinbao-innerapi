package com.baidu.jinbao.mall.item.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.dao.BcsImageDao;

public class BcsImageDaoImplTest extends AbstractDAOTests {
    @Resource
    private BcsImageDao bcsImageDao;

    @Test
    public void testInsert() {
        BcsImage bcsImage = new BcsImage();
        bcsImage.setImagebcsurl("asd");
        bcsImageDao.insert(bcsImage);
        Assert.assertTrue(true);

    }

    @Test
    public void testInsertRecords() {
        BcsImage bcsImage = new BcsImage();
        bcsImage.setItemid(1L);
        bcsImage.setSkuid(1L);
        bcsImage.setImageurl("");
        bcsImage.setImagebcsurl("asd");
        bcsImage.setBcsStatus((byte) 0);
        bcsImage.setWidth(0);
        bcsImage.setHeight(0);
        bcsImage.setSequence(0);
        bcsImage.setContentMd5("");
        bcsImage.setGipsImage("");
        bcsImage.setPicType((byte) 0);
        bcsImage.checkDefault();

        List<BcsImage> data = new ArrayList<BcsImage>();
        data.add(bcsImage);

        bcsImageDao.insertRecords(data);
        Assert.assertTrue(true);

    }

    @Test
    public void testUpdate() {

        BcsImage bcsImage = new BcsImage();
        bcsImage.setImagebcsurl("asd");
        bcsImage.setContentMd5("asdasd");
        bcsImage.setItemid(10L);
        bcsImageDao.insert(bcsImage);

        bcsImage.setContentMd5("SKDJW");
        bcsImageDao.update(bcsImage);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelect() {
        BcsImage bcsImage = new BcsImage();
        bcsImage.setImagebcsurl("asd");
        bcsImage.setContentMd5("asdasd");
        bcsImage.setItemid(10L);
        bcsImageDao.insert(bcsImage);

        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(true);
    }

    @Test
    public void testSelectBySkuIdandItemId() {
        BcsImage bcsImage = new BcsImage();
        bcsImage.setImagebcsurl("asd");
        bcsImage.setContentMd5("asdasd");
        bcsImage.setItemid(1L);
        bcsImage.setSkuid(1L);
        bcsImageDao.insert(bcsImage);

        bcsImage.setId(null);
        bcsImage.setImagebcsurl("asd");
        bcsImage.setContentMd5("asdasd");
        bcsImage.setItemid(2L);
        bcsImage.setSkuid(3L);
        bcsImageDao.insert(bcsImage);

        List<Long> skuIds = new ArrayList<Long>();
        skuIds.add(3L);
        List<Long> itemIds = new ArrayList<Long>();
        itemIds.add(1L);

        List<BcsImage> bcsImageVos = bcsImageDao.getRecordsBySkuIdOrItemId(itemIds, skuIds);
        for (BcsImage vo : bcsImageVos) {
            System.out.println(String.valueOf(vo.getItemid()) + " " + String.valueOf(vo.getSkuid()));
        }

    }
    
    @Test
    public void testBatcheDelete() {
        List<Long> idList = Arrays.asList(1L, 2L);
        this.bcsImageDao.batchDelete(idList);

        Assert.assertTrue(true);
    }
}
