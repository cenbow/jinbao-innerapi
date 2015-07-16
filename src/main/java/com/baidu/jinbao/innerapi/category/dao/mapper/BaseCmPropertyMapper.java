package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;

public interface BaseCmPropertyMapper extends GenericMapper<BaseCmProperty, Long> {
    /**
     * 批量插入BaseCmProperty
     * 
     * @param baseCmProperties 插入baseCmProperty列表
     * @return 插入条数
     */
    public Integer batchInsert(List<BaseCmProperty> baseCmProperties);

    /**
     * 批量更新BaseCmProperty
     * 
     * @param baseCmProperties 更新baseCmProperty列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<BaseCmProperty> baseCmProperties);

    /**
     * 批量删除BaseCmProperty
     * 
     * @param baseCmPropertyIds 删除BaseCmPropertyId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> baseCmPropertyIds);

    /**
     * 批量查询BaseCmProperty
     * 
     * @param baseCmPropertyIds 查询BaseCmProperty Id 列表
     * @return BaseCmProperty列表
     */
    public List<BaseCmProperty> batchSelect(List<Long> baseCmPropertyIds);
}
