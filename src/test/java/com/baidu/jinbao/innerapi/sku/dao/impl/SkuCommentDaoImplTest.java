package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.dao.SkuCommentDao;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

/**
 * SkuCommentDaoImplTest
 * 
 * @author cgd
 * @date 2015年6月12日 上午10:45:37
 */
public class SkuCommentDaoImplTest extends AbstractDAOTests {
    @Resource
    private SkuCommentDao skuCommentDao;

    @Before
    public void setUp() {
        this.executeDatas("sku/sku_comment_init.sql");
    }

    @Test
    public void testbatchInsert() {
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);

        this.skuCommentDao.batchInsert(0, skuCommentList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdate() {
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);

        this.skuCommentDao.batchUpdate(0, skuCommentList);

        Assert.assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testbatchUpdateByKey() {
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        SkuComment item = new SkuComment();
        item.setId(2L);
        item.setAddtime(new Date());
        item.setSkuid("1001_2001");
        item.setSkuInnerid(1001L);
        item.setUpdatetime(new Date());
        skuCommentList.add(item);

        this.skuCommentDao.batchUpdateByKey(0, skuCommentList);

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
        this.skuCommentDao.batchSelect(0, skuQueryCondition);
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
        this.skuCommentDao.batchDelete(0, skuQueryCondition);
        Assert.assertTrue(true);
    }
}
