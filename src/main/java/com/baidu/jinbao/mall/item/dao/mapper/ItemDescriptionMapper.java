package com.baidu.jinbao.mall.item.dao.mapper;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemDescription;

public interface ItemDescriptionMapper extends GenericMapper<ItemDescription, Long> {
    /**
     * 插入itemDescription
     * 
     * @param 插入itemDescription
     * @return 插入条数
     */
    public int insertOne(ItemDescription itemDescription);

    /**
     * 更新itemDescription
     * 
     * @param 更新itemDescription
     * @return 更新条数
     */
    public int update(ItemDescription itemDescription);

    /**
     * 删除itemDescription
     * 
     * @param 删除itemId
     * @return 删除条数
     */
    public int delete(Long itemId);

    /**
     * 查询itemDescription
     * 
     * @param 查询itemId
     * @return 查询的ItemDescription结果
     */
    public ItemDescription select(Long itemId);
}