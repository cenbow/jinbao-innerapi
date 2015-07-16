package com.baidu.jinbao.innerapi.category.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;

public interface BaseCmValueDao extends GenericMapperDao<BaseCmValue, Long> {
    /**
     * 批量插入BaseCmValue
     * 
     * @param baseCmValues 插入baseCmValue列表
     * @return 插入条数
     */
    public Integer batchInsert(List<BaseCmValue> baseCmValues);

    /**
     * 批量更新BaseCmValue
     * 
     * @param baseCmValues 更新baseCmValue列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<BaseCmValue> baseCmValues);

    /**
     * 批量删除BaseCmValue
     * 
     * @param baseCmValueIds 删除BaseCmValueId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> baseCmValueIds);

    /**
     * 批量查询BaseCmValue
     * 
     * @param baseCmValueIds 查询BaseCmValue Id 列表
     * @return BaseCmValue列表
     */
    public List<BaseCmValue> batchSelect(List<Long> baseCmValueIds);
}
