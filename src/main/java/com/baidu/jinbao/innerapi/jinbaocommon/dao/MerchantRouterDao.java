
/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.jinbaocommon.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;

public interface MerchantRouterDao extends GenericMapperDao<MerchantRouter, Long> {
    /**
     * 批量插入merchantRouter
     * 
     * @param merchantRouters : merchantRouter list
     * @return 影响的行数
     */
    public int batchInsert(List<MerchantRouter> merchantRouterList);

    /**
     * 批量更新merchantRouter
     * 
     * @param merchantRouterps : merchantRouter list
     * @return 影响的行数
     */
    public int batchUpdate(List<MerchantRouter> merchantRouterList);

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
     * @return 获取的merchantRouter list
     */
    public List<MerchantRouter> batchSelect(List<Long> idList);
    
    /**
     * 通过merchantid查询
     * 
     * @param merchantid
     * @return 获取的merchantRouter list
     */
    public List<MerchantRouter> selectByMerchantid(Long merchantid);
    
}
