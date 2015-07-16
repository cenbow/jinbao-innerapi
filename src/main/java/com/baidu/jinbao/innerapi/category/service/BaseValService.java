package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.BaseVal;

public interface BaseValService extends GenericMapperService<BaseVal, Long> {

    /**
     * 批量插入BaseVal
     * 
     * @param baseVals 插入baseVal列表
     * @return 插入条数
     */
    public Integer insertRecords(List<BaseVal> baseValList);

    /**
     * 批量更新BaseVal
     * 
     * @param baseVals 更新baseVal列表
     */
    public Integer updateRecords(List<BaseVal> baseValList);

    /**
     * 批量删除BaseVal
     * 
     * @param baseValIds 删除BaseValId列表
     */

    public Integer deleteRecords(List<Long> baseValIdList);

    /**
     * 批量查询BaseVal
     * 
     * @param baseValIds 查询BaseVal Id 列表
     * @return BaseVal列表
     */
    public List<BaseVal> getRecords(List<Long> baseValIdList);
}
