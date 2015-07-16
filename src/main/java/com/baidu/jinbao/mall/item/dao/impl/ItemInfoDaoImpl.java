package com.baidu.jinbao.mall.item.dao.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemInfoDao;
import com.baidu.jinbao.mall.item.dao.mapper.ItemInfoMapper;
import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemInfoDaoImpl extends BaseDao<ItemInfo, Long> implements ItemInfoDao {

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public GenericMapper<ItemInfo, Long> getMapper() {
        return this.itemInfoMapper;
    }

    @Override
    public int update(ItemInfo itemInfo) {
        if (itemInfo == null) {
            return 0;
        }
        return this.itemInfoMapper.update(itemInfo);
    }

    @Override
    public int delete(Long itemId) {
        if (itemId == null) {
            return 0;
        }
        return this.itemInfoMapper.delete(itemId);
    }

    @Override
    public ItemInfo select(Long itemId) {
        return this.itemInfoMapper.select(itemId);
    }

    @Override
    public Long insertOne(ItemInfo itemInfo) {
        if (itemInfo == null) {
            return 0L;
        }
        this.itemInfoMapper.insertOne(itemInfo);
        return itemInfo.getItemid();
    }

    @Override
    public List<ItemInfo> selectByHashCode(Integer hashCode) {
        if (hashCode == null) {
            return new ArrayList<ItemInfo>();
        }
        return this.itemInfoMapper.selectByHashCode(hashCode);
    }

    @Override
    public int batchUpdate(List<ItemInfo> itemInfos) {
        if (CollectionUtils.isEmpty(itemInfos)) {
            return 0;
        }
        return this.itemInfoMapper.batchUpdate(itemInfos);
    }

    @Override
    public List<PageItemTotalInfoVo> selectPageItemTotalInfo(PageItemVo pageItemVo) {
        if (pageItemVo == null) {
            return new ArrayList<PageItemTotalInfoVo>();
        }
        return this.itemInfoMapper.selectPageItemTotalInfo(pageItemVo);
    }
    @Override
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList) {
//        return this.itemInfoMapper..batchSelectByItemIdList(idsList);
        return null;
    }
}
