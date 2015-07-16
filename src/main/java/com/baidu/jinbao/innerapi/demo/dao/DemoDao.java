package com.baidu.jinbao.innerapi.demo.dao;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.demo.bo.Demo;

public interface DemoDao extends GenericMapperDao<Demo, Long> {
    
    public void executeInsert(String sql);

    public void executeUpdate(String sql);

    public void executeDelete(String sql);

}
