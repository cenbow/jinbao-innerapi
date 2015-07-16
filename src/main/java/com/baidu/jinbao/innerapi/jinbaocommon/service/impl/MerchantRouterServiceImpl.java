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
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantRouterDao;
import com.baidu.jinbao.innerapi.jinbaocommon.service.MerchantRouterService;

@Service("merchantRouterService")
@SplitModule(moduleName = PdsConstants.COMMON_MODULE_DATASOURCE_KEY)
public class MerchantRouterServiceImpl extends BaseService<MerchantRouter, Long> implements MerchantRouterService {

    @Autowired
    private MerchantRouterDao merchantRouterDao;

    @Override
    public GenericMapperDao<MerchantRouter, Long> getDao() {
        return merchantRouterDao;
    }

    @Override
    public int insertRecords(List<MerchantRouter> merchantRouterList) {
        if (CollectionUtils.isEmpty(merchantRouterList)) {
            return 0;
        }
        return merchantRouterDao.batchInsert(merchantRouterList);
    }

    @Override
    public int updateRecords(List<MerchantRouter> merchantRouterList) {
        if (CollectionUtils.isEmpty(merchantRouterList)) {
            return 0;
        }
        return merchantRouterDao.batchUpdate(merchantRouterList);
    }

    @Override
    public int deleteRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return merchantRouterDao.batchDelete(idList);
    }

    @Override
    public List<MerchantRouter> getRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<MerchantRouter>();
        }
        return merchantRouterDao.batchSelect(idList);
    }

    @Override
    public List<MerchantRouter> getRecordsByMerchantid(Long merchantid) {
        return merchantRouterDao.selectByMerchantid(merchantid);
    }
}
