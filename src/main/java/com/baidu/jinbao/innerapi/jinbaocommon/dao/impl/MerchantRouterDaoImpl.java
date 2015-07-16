
/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantRouterDao;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.mapper.MerchantRouterMapper;

@Repository
public class MerchantRouterDaoImpl extends BaseDao<MerchantRouter, Long>  implements MerchantRouterDao {

    @Autowired
    private MerchantRouterMapper merchantRouterMapper;

    @Override
    public GenericMapper<MerchantRouter, Long> getMapper() {
        return this.merchantRouterMapper;
    }

    @Override
    public int batchInsert(List<MerchantRouter> merchantRouterList) {
        if (CollectionUtils.isEmpty(merchantRouterList)) {
            return 0;
        }
        return merchantRouterMapper.batchInsert(merchantRouterList);
    }

    @Override
    public int batchUpdate(List<MerchantRouter> merchantRouterList) {
        if (CollectionUtils.isEmpty(merchantRouterList)) {
            return 0;
        }
        return merchantRouterMapper.batchUpdate(merchantRouterList);
    }

    @Override
    public int batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return merchantRouterMapper.batchDelete(idList);
    }

    @Override
    public List<MerchantRouter> batchSelect(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<MerchantRouter>();
        }
        return merchantRouterMapper.batchSelect(idList);
    }

    @Override
    public List<MerchantRouter> selectByMerchantid(Long merchantid) {
        return merchantRouterMapper.selectByMerchantid(merchantid);
    }


}
