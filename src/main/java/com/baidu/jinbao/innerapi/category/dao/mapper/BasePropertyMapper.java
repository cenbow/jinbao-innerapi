package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.BaseProperty;

public interface BasePropertyMapper extends GenericMapper<BaseProperty, Long> {
    /**
     * 批量插入BaseProperty
     * 
     * @param baseProperties 插入baseProperty列表
     * @return 插入条数
     */
    public Integer batchInsert(List<BaseProperty> baseProperties);

    /**
     * 批量更新BaseProperty
     * 
     * @param baseProperties 更新baseProperty列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<BaseProperty> baseProperties);

    /**
     * 批量删除BaseProperty
     * 
     * @param basePropertyIds 删除BasePropertyId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> basePropertyIds);

    /**
     * 批量查询BaseProperty
     * 
     * @param basePropertyIds 查询BaseProperty Id 列表
     * @return BaseProperty列表
     */
    public List<BaseProperty> batchSelect(List<Long> basePropertyIds);
}
