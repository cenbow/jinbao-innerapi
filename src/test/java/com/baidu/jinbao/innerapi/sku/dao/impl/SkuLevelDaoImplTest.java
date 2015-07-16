package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.dao.SkuLevelDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * SkuLevelDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class SkuLevelDaoImplTest extends AbstractDAOTests {
    @Resource
    private SkuLevelDao skuLevelDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/sku_level_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<SkuLevel> skuLevelList = new ArrayList<SkuLevel>();
        SkuLevel item = new SkuLevel();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuLevelList.add(item);

        this.skuLevelDao.batchInsert(0, skuLevelList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<SkuLevel> skuLevelList = new ArrayList<SkuLevel>();
        SkuLevel item = new SkuLevel();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuLevelList.add(item);

        this.skuLevelDao.batchUpdate(0, skuLevelList);

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
        this.skuLevelDao.batchSelect(0, skuQueryCondition);
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
        this.skuLevelDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }
    
    @Test
    public void testUpdateSkuId() {
        SkuLevel item = new SkuLevel();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());

        this.skuLevelDao.updateBySkuId(0, item);

        Assert.assertTrue(true);
    }
}
