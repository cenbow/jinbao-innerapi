package com.baidu.jinbao.mall.item.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public interface ItemCdtMapper extends GenericMapper<ItemCdt, Long> {
    /**
     * 插入itemCdt
     * 
     * @param 插入itemCdt
     * @return 插入条数
     */
    public int insertOne(ItemCdt itemCdt);

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
     * @param 删除itemId
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
     * 批量更新ItemCdt
     * 
     * @param 批量更新ItemCdt
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
     * 根据itemId查询ItemCdt
     * @param itemId
     * @return List<ItemCdt>
     */
    public List<ItemCdt> selectByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
}