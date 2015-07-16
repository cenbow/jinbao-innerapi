
/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;

public interface MerchantInfoMapper extends GenericMapper<MerchantInfo, Long> {
    /**
     * 批量插入merchantInfo
     * 
     * @param merchantInfos : merchantInfo list
     * @return 影响的行数
     */
    public int batchInsert(List<MerchantInfo> merchantInfoList);

    /**
     * 批量更新merchantInfo
     * 
     * @param merchantInfos : merchantInfo list
     * @return 影响的行数
     */
    public int batchUpdate(List<MerchantInfo> merchantInfoList);

    /**
     * 批量删除
     * 
     * @param ids
     * @return 影响的行数
     */
    public int batchDelete(List<Long> idList);

    /**
     * 批量查询
     * 
     * @param ids
     * @return 获取的merchantInfo list
     */
    public List<MerchantInfo> batchSelect(List<Long> idList);
    
}