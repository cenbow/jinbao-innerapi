package com.baidu.jinbao.innerapi.demo.service;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.demo.bo.Demo;

public interface FixService extends GenericMapperService<Demo, Long> {

    public void executeInsert(String sql);

    public void executeUpdate(String sql);

    public void executeDelete(String sql);

}
