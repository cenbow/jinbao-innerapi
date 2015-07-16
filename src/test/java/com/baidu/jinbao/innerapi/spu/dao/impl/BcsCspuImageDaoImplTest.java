package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.dao.BcsCspuImageDao;

public class BcsCspuImageDaoImplTest extends AbstractDAOTests {
    @Resource
    private BcsCspuImageDao bcsCspuImageDao;

    @Before
    public void setUp() {
        this.executeDatas("spu/bcs_cspu_image_init.sql");
    }

    @Test
    public void testFindById() {
        BcsCspuImage ret = this.bcsCspuImageDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getId().equals(1L));
    }

    @Test
    public void testBatchInsert() {
        List<BcsCspuImage> bcsCspuImages = new ArrayList<BcsCspuImage>();
        BcsCspuImage bcsCspuImage1 = new BcsCspuImage();
        bcsCspuImage1.setCspuid(1L);
        BcsCspuImage bcsCspuImage2 = new BcsCspuImage();
        bcsCspuImage2.setCspuid(2L);
        bcsCspuImages.add(bcsCspuImage1);
        bcsCspuImages.add(bcsCspuImage2);
        this.bcsCspuImageDao.batchInsert(bcsCspuImages);
        BcsCspuImage ret = this.bcsCspuImageDao.selectByPrimaryKey(2L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(1L));
        ret = this.bcsCspuImageDao.selectByPrimaryKey(3L);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.getCspuid().equals(2L));
    }

    @Test(expected = Exception.class)
    public void testBatchUpdate() {
        List<BcsCspuImage> bcsCspuImages = new ArrayList<BcsCspuImage>();
        BcsCspuImage bcsCspuImage1 = new BcsCspuImage();
        bcsCspuImage1.setCspuid(1L);
        BcsCspuImage bcsCspuImage2 = new BcsCspuImage();
        bcsCspuImage2.setCspuid(2L);
        bcsCspuImages.add(bcsCspuImage1);
        bcsCspuImages.add(bcsCspuImage2);
        this.bcsCspuImageDao.batchInsert(bcsCspuImages);
        bcsCspuImage1.setId(1L);
        bcsCspuImage1.setCspuid(3L);
        bcsCspuImage2.setId(2L);
        bcsCspuImage2.setCspuid(4L);
        this.bcsCspuImageDao.batchUpdate(bcsCspuImages);
    }

    @Test
    public void testBatchSelect() {
        List<Long> bcsCspuImageIds = new ArrayList<Long>();
        bcsCspuImageIds.add(1L);
        List<BcsCspuImage> ret = bcsCspuImageDao.batchSelect(bcsCspuImageIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 1);
    }

    @Test
    public void testBatchDelete() {
        List<Long> bcsCspuImageIds = new ArrayList<Long>();
        bcsCspuImageIds.add(2L);
        bcsCspuImageIds.add(3L);
        List<BcsCspuImage> ret = bcsCspuImageDao.batchSelect(bcsCspuImageIds);
        Assert.assertNotNull(ret);
        this.bcsCspuImageDao.batchDelete(bcsCspuImageIds);
        ret = bcsCspuImageDao.batchSelect(bcsCspuImageIds);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() == 0);
    }
}
