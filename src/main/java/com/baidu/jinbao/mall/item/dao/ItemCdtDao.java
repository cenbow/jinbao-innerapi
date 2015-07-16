package com.baidu.jinbao.mall.item.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public interface ItemCdtDao extends GenericMapperDao<ItemCdt, Long> {
    /**
     * 插入itemCdt
     * 
     * @param 插入itemCdt
     * @return 插入后获得自增id
     */
    public Long insertOne(ItemCdt itemCdt);

    /**
     * 更新itemCdt
     * 
     * @param 更新itemCdt
     * @return 更新条数
     */
    public int update(ItemCdt itemCdt);

    /**
     * 删除itemCdt
     * 
     * @param 删除itemCdtId
     * @return 删除条数
     */
    public int delete(Long itemId);

    /**
     * 查询itemCdt
     * 
     * @param 查询cdtMd5
     * @return 查询的ItemCdt结果
     */
    public ItemCdt select(String cdtMd5);

    
    /**
     * 批量更新itemCdt
     * 
     * @param 更新itemCdt
     * @return 更新条数
     */
    public int batchUpdate(List<ItemCdt> itemCdts);
    
    
    /**
     * 批量插入itemCdt
     * 
     * @param 插入itemCdt
     * @return 插入
     */
    public int batchInsert(List<ItemCdt> itemCdts);
    
    /**
     * 查询ItemCdt by itemId
     * @param itemId
     * @returnList<ItemCdt>
     */
    public List<ItemCdt> selectByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @returnList<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
}
