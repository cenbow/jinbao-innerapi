package com.baidu.jinbao.mall.item.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemCdtDao;
import com.baidu.jinbao.mall.item.dao.mapper.ItemCdtMapper;

@Repository
public class ItemCdtDaoImpl extends BaseDao<ItemCdt, Long> implements ItemCdtDao {

    @Autowired
    private ItemCdtMapper itemCdtMapper;

    @Override
    public GenericMapper<ItemCdt, Long> getMapper() {
        return this.itemCdtMapper;
    }

    @Override
    public Long insertOne(ItemCdt itemCdt) {
        if (itemCdt == null) {
            return 0L;
        }
        this.itemCdtMapper.insertOne(itemCdt);
        return itemCdt.getId();
    }

    @Override
    public int delete(Long itemId) {
        if (itemId == null) {
            return 0;
        }
        return this.itemCdtMapper.delete(itemId);
    }

    @Override
    public ItemCdt select(String cdtMd5) {
        if (cdtMd5 == null) {
            return new ItemCdt();
        }
        return this.itemCdtMapper.select(cdtMd5);
    }

    @Override
    public int update(ItemCdt itemCdt) {
        if (itemCdt == null) {
            return 0;
        }
        return this.itemCdtMapper.update(itemCdt);
    }

    @Override
    public int batchUpdate(List<ItemCdt> itemCdts) {
        if (CollectionUtils.isEmpty(itemCdts)) {
            return 0;
        }
        return this.itemCdtMapper.batchUpdate(itemCdts);
    }

    @Override
    public int batchInsert(List<ItemCdt> itemCdts) {
        if (CollectionUtils.isEmpty(itemCdts)) {
            return 0;
        }
        return this.itemCdtMapper.batchInsert(itemCdts);
    }

    @Override
    public List<ItemCdt> selectByItemId(Long itemId) {
        return this.itemCdtMapper.selectByItemId(itemId);
    }

    @Override
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList) {
        return this.itemCdtMapper.batchSelectByItemIdList(idsList);
    }

}
