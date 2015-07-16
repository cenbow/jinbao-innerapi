package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseVal;

public interface BaseValMapper extends GenericMapper<BaseVal, Long> {
    /**
     * 批量插入BaseVal
     * 
     * @param baseVals 插入baseVal列表
     * @return 插入条数
     */
    public Integer batchInsert(List<BaseVal> baseVals);

    /**
     * 批量更新BaseVal
     * 
     * @param baseVals 更新baseVal列表
     */
    public Integer batchUpdate(List<BaseVal> baseVals);

    /**
     * 批量删除BaseVal
     * 
     * @param baseValIds 删除BaseValId列表
     */

    public Integer batchDelete(List<Long> baseValIds);

    /**
     * 批量查询BaseVal
     * 
     * @param baseValIds 查询BaseVal Id 列表
     * @return BaseVal列表
     */
    public List<BaseVal> batchSelect(List<Long> baseValIds);
}
