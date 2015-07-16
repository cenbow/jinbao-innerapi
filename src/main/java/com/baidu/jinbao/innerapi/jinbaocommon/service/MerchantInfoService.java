/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;

public interface MerchantInfoService extends GenericMapperService<MerchantInfo, Long> {
    /**
     * 批量插入merchantInfo
     * 
     * @param merchantInfos : merchantInfo list
     * @return 影响的行数
     */
    public int insertRecords(List<MerchantInfo> merchantInfoList);

    /**
     * 批量更新merchantInfo
     * 
     * @param merchantInfos : merchantInfo list
     * @return 影响的行数
     */
    public int updateRecords(List<MerchantInfo> merchantInfoList);

    /**
     * 批量删除
     * 
     * @param ids
     * @return 影响的行数
     */
    public int deleteRecords(List<Long> idList);

    /**
     * 批量查询
     * 
     * @param ids
     * @return 获取的merchantInfo list
     */
    public List<MerchantInfo> getRecords(List<Long> idList);

}
