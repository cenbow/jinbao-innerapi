/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;

public interface MerchantRouterService extends GenericMapperService<MerchantRouter, Long> {
    /**
     * 批量插入merchantRouter
     * 
     * @param merchantRouters : merchantRouter list
     * @return 影响的行数
     */
    public int insertRecords(List<MerchantRouter> merchantRouterList);

    /**
     * 批量更新merchantRouter
     * 
     * @param merchantRouterps : merchantRouter list
     * @return 影响的行数
     */
    public int updateRecords(List<MerchantRouter> merchantRouterList);

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
     * @return 获取的merchantRouter list
     */
    public List<MerchantRouter> getRecords(List<Long> idList);

    /**
     * 通过merchantid查询
     * 
     * @param merchantid
     * @return 获取的merchantRouter list
     */
    public List<MerchantRouter> getRecordsByMerchantid(Long merchantid);
}
