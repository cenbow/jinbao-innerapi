package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.dao.CspuInfoDao;
import com.baidu.jinbao.innerapi.spu.service.CspuInfoService;
import com.baidu.jinbao.innerapi.spu.vo.CspuTotalInfoVo;

@Service("cspuInfoService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class CspuInfoServiceImpl extends BaseService<CspuInfo, Long> implements CspuInfoService {
    @Autowired
    private CspuInfoDao cspuInfoDao;

    @Override
    public GenericMapperDao<CspuInfo, Long> getDao() {
        return this.cspuInfoDao;
    }

    @Override
    public Integer insertRecords(List<CspuInfo> cspuInfoList) {
        return this.cspuInfoDao.batchInsert(cspuInfoList);
    }

    @Override
    public Integer updateRecords(List<CspuInfo> cspuInfoList) {
        // 获取被更新的记录进行比较,机器修改不能覆盖手工修改
        List<Long> idList = new ArrayList<Long>();
        for (CspuInfo cspuInfo : cspuInfoList) {
            idList.add(cspuInfo.getCspuid());
        }
        List<CspuInfo> queryList = this.getRecords(idList);
        Map<Long, CspuInfo> queryMap = new HashMap<Long, CspuInfo>();
        for (CspuInfo cspuInfo : queryList) {
            queryMap.put(cspuInfo.getCspuid(), cspuInfo);
        }
        List<CspuInfo> updateList = new ArrayList<CspuInfo>();
        for (CspuInfo cspuInfo : cspuInfoList) {
            if (queryMap.containsKey(cspuInfo.getCspuid())
                    && cspuInfo.getCspuFrom() <= queryMap.get(cspuInfo.getCspuid()).getCspuFrom()) {
                updateList.add(cspuInfo);
            }
        }
        return this.cspuInfoDao.batchUpdate(updateList);
    }

    @Override
    public Integer deleteRecords(List<Long> cspuInfoIdList) {
        return this.cspuInfoDao.batchDelete(cspuInfoIdList);
    }

    @Override
    public List<CspuInfo> getRecords(List<Long> cspuInfoIdList) {
        return this.cspuInfoDao.batchSelect(cspuInfoIdList);
    }

    @Override
    public Integer insertCspuTotalInfo(CspuTotalInfoVo vo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer insertCspuTotalInfoList(List<CspuTotalInfoVo> voList) {
        // TODO Auto-generated method stub
        return null;
    }
}