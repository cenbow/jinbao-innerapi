package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.dao.SpuInfoDao;
import com.baidu.jinbao.innerapi.spu.service.SpuInfoService;
import com.baidu.jinbao.innerapi.spu.vo.SpuTotalInfoVo;

@Service("spuInfoService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class SpuInfoServiceImpl extends BaseService<SpuInfo, Long> implements SpuInfoService {
    @Autowired
    private SpuInfoDao spuInfoDao;

    @Override
    public GenericMapperDao<SpuInfo, Long> getDao() {
        return this.spuInfoDao;
    }

    @Override
    public Integer insertRecords(List<SpuInfo> spuInfoList) {
        return this.spuInfoDao.batchInsert(spuInfoList);
    }

    @Override
    public Integer updateRecords(List<SpuInfo> spuInfoList) {
        return this.spuInfoDao.batchUpdate(spuInfoList);
    }

    @Override
    public Integer deleteRecords(List<Long> spuInfoIdList) {
        return this.spuInfoDao.batchDelete(spuInfoIdList);
    }

    @Override
    public List<SpuInfo> getRecords(List<Long> spuInfoIdList) {
        return this.spuInfoDao.batchSelect(spuInfoIdList);
    }

    @Override
    public Integer insertSpuTotalInfo(SpuTotalInfoVo vo) {
        return null;
    }

    @Override
    public Integer insertSpuTotalInfoList(List<SpuTotalInfoVo> voList) {
        // TODO Auto-generated method stub
        return null;
    }
}