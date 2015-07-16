package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;

public interface CmPropertyValueMapper extends GenericMapper<CmPropertyValue, Long> {
    /**
     * 批量插入CmPropertyValue
     * 
     * @param cmPropertyValues 插入cmPropertyValue列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CmPropertyValue> cmPropertyValues);

    /**
     * 批量更新CmPropertyValue
     * 
     * @param cmPropertyValues 更新cmPropertyValue列表
     */
    public Integer batchUpdate(List<CmPropertyValue> cmPropertyValues);

    /**
     * 批量删除CmPropertyValue
     * 
     * @param cmPropertyValueIds 删除CmPropertyValueId列表
     */

    public Integer batchDelete(List<Long> cmPropertyValueIds);

    /**
     * 批量查询CmPropertyValue
     * 
     * @param cmPropertyValueIds 查询CmPropertyValue Id 列表
     * @return CmPropertyValue列表
     */
    public List<CmPropertyValue> batchSelect(List<Long> cmPropertyValueIds);
}
