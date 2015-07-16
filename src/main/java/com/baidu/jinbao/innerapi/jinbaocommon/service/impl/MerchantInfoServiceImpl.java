/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantInfoDao;
import com.baidu.jinbao.innerapi.jinbaocommon.service.MerchantInfoService;

@Service("merchantInfoService")
@SplitModule(moduleName = PdsConstants.COMMON_MODULE_DATASOURCE_KEY)
public class MerchantInfoServiceImpl extends BaseService<MerchantInfo, Long> implements MerchantInfoService {

    @Autowired
    private MerchantInfoDao merchantInfoDao;

    @Override
    public GenericMapperDao<MerchantInfo, Long> getDao() {
        return merchantInfoDao;
    }

    @Override
    public int insertRecords(List<MerchantInfo> merchantInfoList) {
        if (CollectionUtils.isEmpty(merchantInfoList)) {
            return 0;
        }
        return merchantInfoDao.batchInsert(merchantInfoList);
    }

    @Override
    public int updateRecords(List<MerchantInfo> merchantInfoList) {
        if (CollectionUtils.isEmpty(merchantInfoList)) {
            return 0;
        }
        return merchantInfoDao.batchUpdate(merchantInfoList);
    }

    @Override
    public int deleteRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return merchantInfoDao.batchDelete(idList);
    }

    @Override
    public List<MerchantInfo> getRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<MerchantInfo>();
        }
        return merchantInfoDao.batchSelect(idList);
    }
}
