package com.baidu.jinbao.innerapi.demo.service.impl;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.demo.bo.Demo;
import com.baidu.jinbao.innerapi.demo.dao.DemoDao;
import com.baidu.jinbao.innerapi.demo.service.FixService;

@Service("mallItemFixService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class MallItemFixServiceImpl extends BaseService<Demo, Long> implements FixService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public GenericMapperDao<Demo, Long> getDao() {
        return demoDao;
    }

    @Override
    public void executeInsert(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return;
        }
        this.demoDao.executeInsert(sql);
    }

    @Override
    public void executeUpdate(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return;
        }
        this.demoDao.executeUpdate(sql);

    }

    @Override
    public void executeDelete(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return;
        }
        this.demoDao.executeDelete(sql);
    }
}
