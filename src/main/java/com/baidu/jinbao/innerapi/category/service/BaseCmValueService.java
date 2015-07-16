package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;

public interface BaseCmValueService extends GenericMapperService<BaseCmValue, Long> {

    /**
     * 批量插入BaseCmValue
     * 
     * @param baseCmValues 插入baseCmValue列表
     * @return 插入条数
     */
    public Integer insertRecords(List<BaseCmValue> baseCmValueList);

    /**
     * 批量更新BaseCmValue
     * 
     * @param baseCmValues 更新baseCmValue列表
     * @return 更新条数
     */
    public Integer updateRecords(List<BaseCmValue> baseCmValueList);

    /**
     * 批量删除BaseCmValue
     * 
     * @param baseCmValueIds 删除BaseCmValueId列表
     * @return 删除条数
     */
    public Integer deleteRecords(List<Long> baseCmValueIdList);

    /**
     * 批量查询BaseCmValue
     * 
     * @param baseCmValueIds 查询BaseCmValue Id 列表
     * @return BaseCmValue列表
     */
    public List<BaseCmValue> getRecords(List<Long> baseCmValueIdList);
}
