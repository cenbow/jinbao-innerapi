package com.baidu.jinbao.mall.item.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemAttributeDao;
import com.baidu.jinbao.mall.item.dao.mapper.ItemAttributeMapper;

@Repository
public class ItemAttributeDaoImpl extends BaseDao<ItemAttribute, Long> implements ItemAttributeDao {

    @Autowired
    private ItemAttributeMapper itemAttributeMapper;

    @Override
    public GenericMapper<ItemAttribute, Long> getMapper() {
        return this.itemAttributeMapper;
    }

    @Override
    public Long insertOne(ItemAttribute itemAttribute) {
        if (itemAttribute == null) {
            return 0L;
        }
        this.itemAttributeMapper.insertOne(itemAttribute);
        return itemAttribute.getId();
    }

    @Override
    public int update(ItemAttribute itemAttribute) {
        if (itemAttribute == null) {
            return 0;
        }

        return this.itemAttributeMapper.update(itemAttribute);
    }

    @Override
    public int delete(Long itemId) {
        if (itemId == null) {
            return 0;
        }
        return this.itemAttributeMapper.delete(itemId);
    }

    @Override
    public ItemAttribute selectByItemId(Long itemId) {
        if (itemId == null) {
            return new ItemAttribute();
        }
        return this.itemAttributeMapper.selectByItemId(itemId);
    }
    
    @Override
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList) {
        return this.itemAttributeMapper.batchSelectByItemIdList(idsList);
    }

}
