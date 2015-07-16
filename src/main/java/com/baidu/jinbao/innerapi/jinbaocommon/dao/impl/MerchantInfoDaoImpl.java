
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
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.MerchantInfoDao;
import com.baidu.jinbao.innerapi.jinbaocommon.dao.mapper.MerchantInfoMapper;

@Repository
public class MerchantInfoDaoImpl extends BaseDao<MerchantInfo, Long>  implements MerchantInfoDao {

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    public GenericMapper<MerchantInfo, Long> getMapper() {
        return this.merchantInfoMapper;
    }

    @Override
    public int batchInsert(List<MerchantInfo> merchantInfoList) {
        if (CollectionUtils.isEmpty(merchantInfoList)) {
            return 0;
        }
        return merchantInfoMapper.batchInsert(merchantInfoList);
    }

    @Override
    public int batchUpdate(List<MerchantInfo> merchantInfoList) {
        if (CollectionUtils.isEmpty(merchantInfoList)) {
            return 0;
        }
        return merchantInfoMapper.batchUpdate(merchantInfoList);
    }

    @Override
    public int batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return merchantInfoMapper.batchDelete(idList);
    }

    @Override
    public List<MerchantInfo> batchSelect(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<MerchantInfo>();
        }
        return merchantInfoMapper.batchSelect(idList);
    }


}
