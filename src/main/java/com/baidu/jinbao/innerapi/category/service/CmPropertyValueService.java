package com.baidu.jinbao.innerapi.category.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;

public interface CmPropertyValueService extends GenericMapperService<CmPropertyValue, Long> {

    /**
     * 批量插入CmPropertyValue
     * 
     * @param cmPropertyValues 插入cmPropertyValue列表
     * @return 插入条数
     */
    public Integer insertRecords(List<CmPropertyValue> cmPropertyValueList);

    /**
     * 批量更新CmPropertyValue
     * 
     * @param cmPropertyValues 更新cmPropertyValue列表
     */
    public Integer updateRecords(List<CmPropertyValue> cmPropertyValueList);

    /**
     * 批量删除CmPropertyValue
     * 
     * @param cmPropertyValueIds 删除CmPropertyValueId列表
     */

    public Integer deleteRecords(List<Long> cmPropertyValueIdList);

    /**
     * 批量查询CmPropertyValue
     * 
     * @param cmPropertyValueIds 查询CmPropertyValue Id 列表
     * @return CmPropertyValue列表
     */
    public List<CmPropertyValue> getRecords(List<Long> cmPropertyValueIdList);
}
