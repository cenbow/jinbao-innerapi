package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.dao.BcsSkuImageDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * BcsSkuImageDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class BcsSkuImageDaoImplTest extends AbstractDAOTests {
    @Resource
    private BcsSkuImageDao bcsSkuImageDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/bcs_sku_image_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<BcsSkuImage> bcsSkuImageList = new ArrayList<BcsSkuImage>();
        BcsSkuImage item = new BcsSkuImage();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        bcsSkuImageList.add(item);

        this.bcsSkuImageDao.batchInsert(0, bcsSkuImageList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<BcsSkuImage> bcsSkuImageList = new ArrayList<BcsSkuImage>();
        BcsSkuImage item = new BcsSkuImage();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        bcsSkuImageList.add(item);

        this.bcsSkuImageDao.batchUpdate(0, bcsSkuImageList);

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
        this.bcsSkuImageDao.batchSelect(0, skuQueryCondition);
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
        this.bcsSkuImageDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }
}
