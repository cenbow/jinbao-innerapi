package com.baidu.jinbao.mall.item.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.item.dao.ItemDescriptionDao;
import com.baidu.jinbao.mall.item.service.ItemDescriptionService;

@Service("ItemDescriptionService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class ItemDescriptionServiceImpl extends BaseService<ItemDescription, Long> implements ItemDescriptionService {

    @Autowired
    private ItemDescriptionDao itemDescriptionDao;

    private static final Logger LOG = Logger.getLogger(ItemDescriptionServiceImpl.class);

    @Override
    public GenericMapperDao<ItemDescription, Long> getDao() {
        return itemDescriptionDao;
    }

    @Override
    public Long insertRecord(ItemDescription itemDescription) {
        if (itemDescription == null) {
            throw new RuntimeException("itemDescription is null!");
        }
        itemDescription.checkItemId().checkShopId().checkMerchantId().checkItemDescOri().checkItemDesc().checkDefault();
        // Byte to String to MD5
        itemDescription.setPdMd5(MD5Util.getMD5Value(String.valueOf(itemDescription.getItemDescOri())));

        return this.itemDescriptionDao.insertOne(itemDescription);
    }

    @Override
    public int updateRecord(ItemDescription itemDescription) {
        if (itemDescription == null) {
            throw new RuntimeException("itemDescription is null!");
        }
        itemDescription.checkItemId().checkItemDescOri();
        // 没有改变则重新计算
        if (itemDescription.getItemDescOri() != null) {
            itemDescription.setPdMd5(MD5Util.getMD5Value(String.valueOf(itemDescription.getItemDescOri())));
        }

        return this.itemDescriptionDao.update(itemDescription);
    }

    @Override
    public int deleteRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("itemid is null");
        }
        return this.itemDescriptionDao.delete(itemId);
    }

    @Override
    public ItemDescription getRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("itemid is null");
        }
        return this.itemDescriptionDao.select(itemId);
    }
}
