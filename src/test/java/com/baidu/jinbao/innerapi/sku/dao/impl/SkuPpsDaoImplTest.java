package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.dao.SkuPpsDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * SkuPpsDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class SkuPpsDaoImplTest extends AbstractDAOTests {
    @Resource
    private SkuPpsDao skuPpsDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/sku_pps_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuPpsList.add(item);

        this.skuPpsDao.batchInsert(0, skuPpsList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuPpsList.add(item);

        this.skuPpsDao.batchUpdate(0, skuPpsList);

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
        this.skuPpsDao.batchSelect(0, skuQueryCondition);
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
        this.skuPpsDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }
    
    @Test(expected = Exception.class)
    public void testbatchUpdateByKey() {
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setRegionid(1);
        item.setTerminal(new Integer(0).byteValue());
        skuPpsList.add(item);

        this.skuPpsDao.batchUpdateByKey(0, skuPpsList);

        Assert.assertTrue(true);
    }
    
    @Test
    public void testbatchSelectByKey() {
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        SkuPps item = new SkuPps();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        item.setRegionid(1);
        item.setTerminal(new Integer(0).byteValue());
        skuPpsList.add(item);

        this.skuPpsDao.batchSelectByKey(0, skuPpsList);

        Assert.assertTrue(true);
    }
}
