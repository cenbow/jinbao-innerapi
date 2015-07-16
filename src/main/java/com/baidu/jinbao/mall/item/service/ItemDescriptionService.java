package com.baidu.jinbao.mall.item.service;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.ItemDescription;

public interface ItemDescriptionService extends GenericMapperService<ItemDescription, Long> {
    /**
     * 批量插入itemDescription
     * 
     * @param itemDescription 插入单条
     * @return 插入id
     */
    public Long insertRecord(ItemDescription itemDescription);

    /**
     * 批量更新itemDescription
     * 
     * @param itemDescription 更新单条
     * @return 更新条数
     */
    public int updateRecord(ItemDescription itemDescription);

    /**
     * 批量删除itemid in ItemDescription table
     * 
     * @param itemId 删除单条itemDescription
     * @return 删除条数
     */
    public int deleteRecord(Long itemId);

    /**
     * 
     * @param itemId 查询单条itemDescription by itemId
     * @return Item
     */
    public ItemDescription getRecord(Long itemId);

}
