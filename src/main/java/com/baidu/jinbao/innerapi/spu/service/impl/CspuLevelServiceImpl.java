package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;
import com.baidu.jinbao.innerapi.spu.dao.CspuLevelDao;
import com.baidu.jinbao.innerapi.spu.service.CspuLevelService;

@Service("cspuLevelService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class CspuLevelServiceImpl extends BaseService<CspuLevel, Long> implements CspuLevelService {
    @Autowired
    private CspuLevelDao cspuLevelDao;

    @Override
    public GenericMapperDao<CspuLevel, Long> getDao() {
        return this.cspuLevelDao;
    }

    @Override
    public Integer insertRecords(List<CspuLevel> cspuLevelList) {
        return this.cspuLevelDao.batchInsert(cspuLevelList);
    }

    @Override
    public Integer updateRecords(List<CspuLevel> cspuLevelList) {
        return this.cspuLevelDao.batchUpdate(cspuLevelList);
    }

    @Override
    public Integer deleteRecords(List<Long> cspuLevelIdList) {
        return this.cspuLevelDao.batchDelete(cspuLevelIdList);
    }

    @Override
    public List<CspuLevel> getRecords(List<Long> cspuLevelIds) {
        return this.cspuLevelDao.batchSelect(cspuLevelIds);
    }
}