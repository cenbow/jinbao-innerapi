package com.baidu.jinbao.mall.item.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.item.dao.ItemDescriptionDao;
import com.baidu.jinbao.mall.item.dao.mapper.ItemDescriptionMapper;

@Repository
public class ItemDescriptionDaoImpl extends BaseDao<ItemDescription, Long> implements ItemDescriptionDao {

    @Autowired
    private ItemDescriptionMapper itemDescriptionMapper;

    @Override
    public GenericMapper<ItemDescription, Long> getMapper() {
        return this.itemDescriptionMapper;
    }

    @Override
    public Long insertOne(ItemDescription itemDescription) {
        if (itemDescription == null) {
            return 0L;
        }
        this.itemDescriptionMapper.insertOne(itemDescription);
        return itemDescription.getId();
    }

    @Override
    public int update(ItemDescription itemDescription) {
        if (itemDescription == null) {
            return 0;
        }
        return this.itemDescriptionMapper.update(itemDescription);
    }

    @Override
    public int delete(Long itemId) {
        if (itemId == null) {
            return 0;
        }
        return this.itemDescriptionMapper.delete(itemId);
    }

    @Override
    public ItemDescription select(Long itemId) {
        if (itemId == null) {
            return new ItemDescription();
        }
        return this.itemDescriptionMapper.select(itemId);
    }

}
