package com.baidu.jinbao.innerapi.demo.dao.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.innerapi.demo.dao.DemoDao;

/**
 * DemoDaoImplTest
 * 
 * @author cgd
 * @date 2015年7月14日 上午11:09:25
 */
public class DemoDaoImplTest extends AbstractDAOTests {
    @Resource
    private DemoDao demoDao;

    @Before
    public void setUp() {
        // this.executeDatas("category/base_cm_property_init.sql");
    }

    @Test
    public void testDemo() {
        // this.demoDao.executeInsert("insert into demo(id, msg) values(1, 'test');");
        // this.demoDao.executeUpdate("update demo set msg='test2' where id=1;");
        // this.demoDao.executeDelete("delete from demo where id = 1;");

        Assert.assertTrue(true);
    }
}
