package com.baidu.jinbao.innerapi.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.demo.bo.Demo;
import com.baidu.jinbao.innerapi.demo.dao.DemoDao;
import com.baidu.jinbao.innerapi.demo.dao.mapper.DemoMapper;

@Repository
public class DemoDaoImpl extends BaseDao<Demo, Long> implements DemoDao {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public GenericMapper<Demo, Long> getMapper() {
        return this.demoMapper;
    }

    @Override
    public void executeInsert(String sql) {
        this.demoMapper.executeInsert(sql);
    }

    @Override
    public void executeUpdate(String sql) {
        this.demoMapper.executeUpdate(sql);
    }

    @Override
    public void executeDelete(String sql) {
        this.demoMapper.executeDelete(sql);
    }

}
