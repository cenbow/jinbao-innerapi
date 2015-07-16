package com.baidu.jinbao.mall.item.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

public interface ItemCdtService extends GenericMapperService<ItemCdt, Long> {
    /**
     * 批量插入itemCdt
     * 
     * @param itemCdt 插入单条
     * @return 插入条数
     */
    public Long insertRecord(ItemCdt itemCdt);

    /**
     * 更新itemCdt
     * 
     * @param itemCdt 更新单条
     * @return 更新条数
     */
    public int updateRecord(ItemCdt itemCdt);

    /**
     * 批量删除itemid in ItemCdt table
     * 
     * @param itemCdtId 删除单条itemid
     * @return 删除条数
     */
    public int deleteRecord(Long itemId);

    /**
     * 
     * @param cdtMd5 查询单条itemcdt by cdtMd5
     * @return Item
     */
    public ItemCdt getRecord(Long cdtMd5);

    /**
     * 批量更新itemCdt
     * 
     * @param itemCdt 更新单条
     * @return 更新条数
     */
    public int updateRecords(List<ItemCdt> itemCdts);
    /**
     * 批量插入itemCdt
     * 
     * @param itemCdt
     * @return 插入条数
     */
    public int insertRecords(List<ItemCdt> itemCdts);
    
    /**
     * 根据itemid获取itemCdt List
     * @param itemId
     * @return List<ItemCdt>
     */
    public List<ItemCdt> getRecordsByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList);

}
