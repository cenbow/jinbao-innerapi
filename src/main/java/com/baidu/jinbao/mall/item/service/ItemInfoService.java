package com.baidu.jinbao.mall.item.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.bo.ItemTotalInfo;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoInsertVo;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

public interface ItemInfoService extends GenericMapperService<ItemInfo, Long> {
    /**
     * 插入itemInfo
     * 
     * @param itemInfo 插入单条
     * @return 插入后的id
     */
    public Long insertRecord(ItemInfo itemInfo);

    /**
     * 批量更新itemInfo
     * 
     * @param itemInfo 更新单条
     * @return 更新条数
     */
    public int updateRecord(ItemInfo itemInfo);

    /**
     * 批量删除itemid in ItemInfo table
     * 
     * @param itemId 删除单条itemInfo
     * @return 删除条数
     */
    public int deleteRecord(Long itemId);

    /**
     * 
     * @param itemId 查询单条itemInfo by itemId
     * @return Item
     */
    public ItemInfo getRecord(Long itemId);

    /**
     * 批量插入itemInfo
     * 
     * @param itemInfo 插入单条
     * @return 插入条数
     */
    public int updateRecords(List<ItemInfo> itemInfo);
    
    /**
     * 获取Item相关信息: 支持分页、排序（按照价格、上架时间）
     * @param pageItemVo 查询条件
     * @return PageItemTotalInfoVo
     */
    public List<PageItemTotalInfoVo> getPageItemTotalInfo(PageItemVo pageItemVo);

    /**
     * 获取Item相关信息: ItemId
     * @param itemId
     * @return ItemTotalInfo
     */
    public ItemTotalInfo getItemTotalByItemId(Long itemId);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList);
    
    
    /**
     * 插入ItemTotalInfo
     * @param ItemTotalInfoVo
     * @return 返回信息 ItemTotalInfoInsertVo
     */
    public ItemTotalInfoInsertVo insertItemTotalInfo(ItemTotalInfoVo itemTotalInfo);
    
    /**
     * 更新ItemTotalInfo
     * @param ItemTotalInfoVo
     * @return 成功个数
     */
    public Integer updateItemTotalInfo(ItemTotalInfoVo itemTotalInfo);
    
}
