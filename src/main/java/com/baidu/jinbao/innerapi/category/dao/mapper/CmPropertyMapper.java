package com.baidu.jinbao.innerapi.category.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.category.bo.CmProperty;

public interface CmPropertyMapper extends GenericMapper<CmProperty, Long> {
    /**
     * 批量插入CmProperty
     * 
     * @param cmProperties 插入cmProperty列表
     * @return 插入条数
     */
    public Integer batchInsert(List<CmProperty> cmProperties);

    /**
     * 批量更新CmProperty
     * 
     * @param cmProperties 更新cmProperty列表
     */
    public Integer batchUpdate(List<CmProperty> cmProperties);

    /**
     * 批量删除CmProperty
     * 
     * @param cmPropertyIds 删除CmPropertyId列表
     */

    public Integer batchDelete(List<Long> cmPropertyIds);

    /**
     * 批量查询CmProperty
     * 
     * @param cmPropertyIds 查询CmProperty Id 列表
     * @return CmProperty列表
     */
    public List<CmProperty> batchSelect(List<Long> cmPropertyIds);
}
