package com.baidu.jinbao.mall.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.dao.ItemCdtDao;
import com.baidu.jinbao.mall.item.service.ItemCdtService;

/**
 * ItemCdt服务
 * 
 * @author duzeyu
 * @date 2015-7-2 下午8:00
 */

@Service("ItemCdtService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class ItemCdtServiceImpl extends BaseService<ItemCdt, Long> implements ItemCdtService {

    @Autowired
    private ItemCdtDao itemCdtDao;

    @Override
    public GenericMapperDao<ItemCdt, Long> getDao() {
        return itemCdtDao;
    }

    @Override
    public Long insertRecord(ItemCdt itemCdt) {
        if (itemCdt == null) {
            throw new RuntimeException("item cdt is null!");
        }
        itemCdt = itemCdt.checkItemId().checkLeafcategoryId().checkOperator().checkDefault();
        itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf(itemCdt.getClassificationtype())));
        itemCdtDao.insert(itemCdt);
        return itemCdt.getId();
    }

    @Override
    public int updateRecord(ItemCdt itemCdt) {
        if (itemCdt == null) {
            throw new RuntimeException("item cdt is null!");
        }
        itemCdt.checkItemId().checkClassificationType();

        // 取得相同 cdtMd5的 itemCdt 用于更新逻辑
        String classifiZero = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 0));
        String classifiOne = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 1));
        String classifTwo = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 2));
        // 是机器学习 需要验证是否存在
        if (itemCdt.getClassificationtype().toString().equals("2")) {

            ItemCdt classOneItemCdt = itemCdtDao.select(classifiOne);
            ItemCdt classZeroItemCdt = itemCdtDao.select(classifiZero);
            ItemCdt classTwoCdt = itemCdtDao.select(classifTwo);

            // 运营修改和商家修改都没有,更新或插入
            if ((classOneItemCdt == null) && (classZeroItemCdt == null)) {
                // 机器学习也没有,插入，
                if (classTwoCdt == null) {
                    itemCdt.setCdtMd5(classifTwo);
                    itemCdtDao.insertOne(itemCdt);
                    return 1;
                } else {
                    // 否则更新
                    itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid()
                            + String.valueOf(itemCdt.getClassificationtype())));
                    return itemCdtDao.update(itemCdt);
                }
            } else {
                throw new RuntimeException("machine learning is lower level!");
            }
        }

        // 计算MD5
        if ((itemCdt.getItemid() != null || itemCdt.getClassificationtype() != null)) {
            itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid() 
                    + String.valueOf(itemCdt.getClassificationtype())));
        }

        // 是运营或商家修改的话。
        ItemCdt targetCdt = itemCdtDao.select(classifiOne);
        if (targetCdt == null) {
            targetCdt = itemCdtDao.select(classifiZero);
        }
        // 不存在，插入
        if (targetCdt == null) {
            targetCdt = itemCdtDao.select(classifTwo);
            // 人工覆盖机器学习
            if (targetCdt != null) {
                return this.itemCdtDao.update(itemCdt);
            }
            this.itemCdtDao.insertOne(itemCdt);
            return 1;
        }
        // 如果数据早于更新数据
        if (targetCdt.getUpdatetime().compareTo(itemCdt.getUpdatetime()) < 0) {
            return itemCdtDao.update(itemCdt);
        } else {
            throw new RuntimeException("upload time is earlier... can not update!");
        }

    }

    @Override
    public int deleteRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("item id is null");
        }
        return itemCdtDao.delete(itemId);
    }

    @Override
    public ItemCdt getRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("item id is null");
        }
        return itemCdtDao.selectByPrimaryKey(itemId);
    }

    @Override
    public int updateRecords(List<ItemCdt> itemCdts) {
        // 需要对带更新itemCdt区分
        ArrayList<ItemCdt> updateItemCdts = new ArrayList<ItemCdt>();
        ArrayList<ItemCdt> insertItemCdts = new ArrayList<ItemCdt>();
        for (ItemCdt itemCdt : itemCdts) {
            if (itemCdt == null) {
                throw new RuntimeException("item cdt is null!");
            }
            itemCdt.checkItemId().checkClassificationType();
            // 取得相同 cdtMd5的 itemCdt 用于更新逻辑
            String classifiZero = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 0));
            String classifiOne = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 1));
            String classifTwo = MD5Util.getMD5Value(itemCdt.getItemid() + String.valueOf((byte) 2));

            // 是机器学习 需要验证是否存在
            if (itemCdt.getClassificationtype().toString().equals("2")) {
                // 取出可能存在数据
                ItemCdt classOneItemCdt = itemCdtDao.select(classifiOne);
                ItemCdt classZeroItemCdt = itemCdtDao.select(classifiZero);
                ItemCdt classTwoCdt = itemCdtDao.select(classifTwo);
                // 运营修改和商家修改都没有,更新或插入
                if ((classOneItemCdt == null) && (classZeroItemCdt == null)) {
                    // 机器学习也没有,插入，
                    if (classTwoCdt == null) {
                        // 加入插入列表
                        itemCdt.setCdtMd5(classifTwo);
                        insertItemCdts.add(itemCdt);
                    } else {
                        // 加入更新列表
                        itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid()
                                + String.valueOf(itemCdt.getClassificationtype())));
                        updateItemCdts.add(itemCdt);
                    }
                } else {
                    // 机器学习不覆盖人工
                    continue;
                }

            } else {
                // 计算MD5
                if ((itemCdt.getItemid() != null || itemCdt.getClassificationtype() != null)) {
                    itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid()
                            + String.valueOf(itemCdt.getClassificationtype())));
                }

                // 是运营或商家修改的话。
                ItemCdt targetCdt = itemCdtDao.select(classifiOne);
                if (targetCdt == null) {
                    targetCdt = itemCdtDao.select(classifiZero);
                }
                if (targetCdt == null) {
                    targetCdt = itemCdtDao.select(classifTwo);
                    // 人工覆盖机器学习
                    if (targetCdt != null) {
                        updateItemCdts.add(itemCdt);
                        continue;
                    }
                    insertItemCdts.add(itemCdt);
                    continue;
                }
                // 如果数据早于更新数据,人工和人工进行比较
                if (targetCdt.getUpdatetime().compareTo(itemCdt.getUpdatetime()) < 0) {
                    updateItemCdts.add(itemCdt);
                } else {
                    // 不早与，这条不更新
                    continue;
                }
            }
        }
        // 分别对待更新和待插入的部分处理。
        int insertNum = 0;
        if (!CollectionUtils.isEmpty(insertItemCdts)) {
            insertNum = this.itemCdtDao.batchInsert(insertItemCdts);
        }
        int updateNum = 0;
        if (!CollectionUtils.isEmpty(updateItemCdts)) {
            updateNum = this.itemCdtDao.batchUpdate(updateItemCdts);
        }
        // 更新成功数目＝更新数目＋插入数目
        return insertNum + updateNum;
    }

    @Override
    public int insertRecords(List<ItemCdt> itemCdts) {
        if (CollectionUtils.isEmpty(itemCdts)) {
            throw new RuntimeException("itemCdts is null");
        }
        // checkDefault
        for (ItemCdt itemCdt : itemCdts) {
            itemCdt = itemCdt.checkItemId().checkLeafcategoryId().checkOperator().checkDefault();
            itemCdt.setCdtMd5(MD5Util.getMD5Value(itemCdt.getItemid() 
                    + String.valueOf(itemCdt.getClassificationtype())));
        }
        return this.itemCdtDao.batchInsert(itemCdts);
    }

    @Override
    public List<ItemCdt> getRecordsByItemId(Long itemId) {
        if (itemId == null) {
            return new ArrayList<ItemCdt>();
        }
        return itemCdtDao.selectByItemId(itemId);
    }

    @Override
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList) {
        if (idsList == null) {
            return new ArrayList<ItemInfo>();
        }
        return itemCdtDao.batchSelectByItemIdList(idsList);
    }
}
