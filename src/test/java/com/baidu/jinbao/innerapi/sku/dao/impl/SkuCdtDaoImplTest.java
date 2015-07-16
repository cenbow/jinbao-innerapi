package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.dao.SkuCdtDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * SkuCdtDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class SkuCdtDaoImplTest extends AbstractDAOTests {
    @Resource
    private SkuCdtDao skuCdtDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/sku_cdt_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        SkuCdt item = new SkuCdt();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCdtList.add(item);

        this.skuCdtDao.batchInsert(0, skuCdtList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        SkuCdt item = new SkuCdt();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCdtList.add(item);

        this.skuCdtDao.batchUpdate(0, skuCdtList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testBatchUpdateByKey() {
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        SkuCdt item = new SkuCdt();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setClassificationtype(new Integer(0).byteValue());
        item.setUpdatetime(new Date());
        skuCdtList.add(item);

        this.skuCdtDao.batchUpdateByKey(0, skuCdtList);

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
        this.skuCdtDao.batchSelect(0, skuQueryCondition);
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
        this.skuCdtDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }
}
