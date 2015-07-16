package com.baidu.jinbao.mall.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemAttributeDao;
import com.baidu.jinbao.mall.item.service.ItemAttributeService;

/**
 * ItemAttribute服务
 * 
 * @author duzeyu
 * @date 2015-7-2 下午4:20
 */

@Service("ItemAttributeService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class ItemAttributeServiceImpl extends BaseService<ItemAttribute, Long> implements ItemAttributeService {

    @Autowired
    private ItemAttributeDao itemAttributeDao;

    @Override
    public GenericMapperDao<ItemAttribute, Long> getDao() {
        return itemAttributeDao;
    }

    @Override
    public Long insertRecord(ItemAttribute itemAttribute) {
        if (itemAttribute == null) {
            throw new RuntimeException("itemAttribute is null!");
        }
        itemAttribute = itemAttribute.checkItemId().checkPropertyId().checkPropertyValues().checkDefault();
        itemAttribute.setPropertyMd5(MD5Util.getMD5Value(itemAttribute.getPropertyId()
                + itemAttribute.getPropertyValues()));
        this.itemAttributeDao.insert(itemAttribute);
        return itemAttribute.getItemid();
    }

    @Override
    public Long updateRecord(ItemAttribute itemAttribute) {
        if (itemAttribute == null) {
            throw new RuntimeException("itemAttribute is null!");
        }
        itemAttribute = itemAttribute.checkItemId().checkPropertyValues();

        // 有改变重新计算MD5
        if (itemAttribute.getPropertyId() != null || itemAttribute.getPropertyValues() != null) {
            itemAttribute.setPropertyMd5(MD5Util.getMD5Value(itemAttribute.getPropertyId()
                    + itemAttribute.getPropertyValues()));
        }
        this.itemAttributeDao.update(itemAttribute);
        return itemAttribute.getItemid();

    }

    @Override
    public Long deleteRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("itemid is null");
        }
        itemAttributeDao.delete(itemId);
        return itemId;
    }

    @Override
    public ItemAttribute getRecord(Long itemId) {
        if (itemId == null) {
            return new ItemAttribute();
        }
        return this.itemAttributeDao.selectByItemId(itemId);
    }
    
    @Override
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList) {
        if (idsList != null) {
            return new ArrayList<ItemInfo>();
        }
        return this.itemAttributeDao.batchSelectByItemIdList(idsList);
    }

}
