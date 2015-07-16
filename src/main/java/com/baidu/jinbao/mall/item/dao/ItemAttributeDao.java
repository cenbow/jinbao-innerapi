package com.baidu.jinbao.mall.item.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public interface ItemAttributeDao extends GenericMapperDao<ItemAttribute, Long> {
    /**
     * 插入itemAttribute
     * 
     * @param 插入itemAttribute
     * @return 插入后获得自增id
     */
    public Long insertOne(ItemAttribute itemAttribute);

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
     * @return 查询到的ItemAttribute结果
     */

    public ItemAttribute selectByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @returnList<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
}
