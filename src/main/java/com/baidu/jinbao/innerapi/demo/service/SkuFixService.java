package com.baidu.jinbao.innerapi.demo.service;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.demo.bo.Demo;

public interface SkuFixService extends GenericMapperService<Demo, Long> {

    public void executeInsert(String splitDbInfo, String sql);

    public void executeUpdate(String splitDbInfo, String sql);

    public void executeDelete(String splitDbInfo, String sql);

}
