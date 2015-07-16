package com.baidu.jinbao.innerapi.demo.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.demo.bo.Demo;

public interface DemoMapper extends GenericMapper<Demo, Long> {
    public void executeInsert(@Param("sql") String sql);
    
    public void executeUpdate(@Param("sql") String sql);

    public void executeDelete(@Param("sql") String sql);
}