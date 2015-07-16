package com.baidu.jinbao.mall.item.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public interface ItemAttributeMapper extends GenericMapper<ItemAttribute, Long> {
    /**
     * 插入itemAttribute
     * 
     * @param 插入itemAttribute
     * @return 插入条数
     */
    public int insertOne(ItemAttribute itemAttribute);

    /**
     * 更新itemAttribute
     * 
     * @param 更新itemAttribute
     * @return 更新条数
     */
    public int update(ItemAttribute itemAttribute);

    /**
     * 删除itemAttribute
     * 
     * @param 删除itemId
     * @return 删除条数
     */
    public int delete(Long itemId);

 
    /**
     * 查询itemAttribute
     * 
     * @param 查询itemId
     * @return 查询的ItemAttribute结果
     */
    public ItemAttribute selectByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
    
}