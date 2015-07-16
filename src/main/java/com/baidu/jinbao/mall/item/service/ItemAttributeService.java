package com.baidu.jinbao.mall.item.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

/**
 * ItemAttributeService接口
 * @author duzeyu
 * @date 2015-7-2 下午1:48
 */
public interface ItemAttributeService extends GenericMapperService<ItemAttribute, Long> {

    /**
     * 批量插入itemAttribute
     * 
     * @param itemAttribute 插入单条
     * @return 插入条数
     */
    public Long insertRecord(ItemAttribute itemAttribute);

    /**
     * 批量更新itemAttribute
     * 
     * @param itemAttribute 更新单条
     * @return 更新条数
     */
    public Long updateRecord(ItemAttribute itemAttribute);

    /**
     * 批量删除itemAttribute
     * 
     * @param itemId 删除单条itemAttributeId
     * @return 删除条数
     */
    public Long deleteRecord(Long itemId);

    /**
     * 
     * @param itemId查询单条 itemId
     * @return ItemAttribute
     */
    public ItemAttribute getRecord(Long itemId);
    
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList);

}
