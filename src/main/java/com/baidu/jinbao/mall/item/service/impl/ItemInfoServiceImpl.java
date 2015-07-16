package com.baidu.jinbao.mall.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.page.PageHelper;
import com.baidu.jinbao.innerapi.common.page.PageInfo;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.bo.ItemAttribute;
import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.item.bo.ItemDescription;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.bo.ItemTotalInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.item.dao.ItemInfoDao;
import com.baidu.jinbao.mall.item.service.BcsImageService;
import com.baidu.jinbao.mall.item.service.ItemAttributeService;
import com.baidu.jinbao.mall.item.service.ItemCdtService;
import com.baidu.jinbao.mall.item.service.ItemDescriptionService;
import com.baidu.jinbao.mall.item.service.ItemInfoService;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoInsertVo;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.MallSkuTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;
import com.baidu.jinbao.mall.item.service.MallSkuInfoService;
import com.baidu.jinbao.mall.item.service.MallSkuPpsService;

@Service("ItemInfoService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class ItemInfoServiceImpl extends BaseService<ItemInfo, Long> implements ItemInfoService {

    @Autowired
    private ItemInfoDao itemInfoDao;

    @Autowired
    private MallSkuPpsService mallSkuPpsService;

    @Autowired
    private MallSkuInfoService mallSkuInfoService;

    @Autowired
    private ItemAttributeService itemAttributeService;

    @Autowired
    private ItemCdtService itemCdtService;

    @Autowired
    private ItemDescriptionService itemDescriptionService;

    @Autowired
    private BcsImageService bcsImageService;

    @Override
    public GenericMapperDao<ItemInfo, Long> getDao() {
        return itemInfoDao;
    }

    @Override
    public Long insertRecord(ItemInfo itemInfo) {
        if (itemInfo == null) {
            throw new RuntimeException("itemInfo is null");
        }
        // 先插入判重
        if (itemInfo.getOuterid() != null && itemInfo.getMerchantid() != null) {
            int hashCode = (itemInfo.getMerchantid() + itemInfo.getOuterid()).hashCode();
            List<ItemInfo> tempItemInfo = itemInfoDao.selectByHashCode(hashCode);
            if (!CollectionUtils.isEmpty(tempItemInfo)) {
                // hashcode相同感觉没法标示数据重复了呀。
                throw new RuntimeException("itemInfo hashCode has repeated!");
            }
        }
        // outId 给一个default值
        if (itemInfo.getOuterid() == null) {
            itemInfo.setOuterid("");
        }
        // 计算新的Hash值
        if ((itemInfo.getMerchantid() != null) && (itemInfo.getOuterid() != null)) {
            itemInfo.setItemInfoHashcode((itemInfo.getMerchantid() + itemInfo.getOuterid()).hashCode());
        }
        // 插入数据验证
        itemInfo =
                itemInfo.checkEndTime().checkManualStatus().checkMerchantId().checkMerchantStatus().checkShopId()
                        .checkStartTime().checkTitle().checkUcId().checkDefault();
        // 计算新hashcode
        itemInfoDao.insertOne(itemInfo);
        return itemInfo.getItemid();
    }

    @Override
    public int updateRecord(ItemInfo itemInfo) {
        if (itemInfo == null) {
            throw new RuntimeException("itemInfo is null");
        }
        itemInfo.checkItemId();
        return itemInfoDao.update(itemInfo);
    }

    @Override
    public int deleteRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("itemId is null");
        }
        return itemInfoDao.delete(itemId);
    }

    @Override
    public ItemInfo getRecord(Long itemId) {
        if (itemId == null) {
            throw new RuntimeException("itemId is null");
        }
        return itemInfoDao.select(itemId);
    }

    @Override
    public int updateRecords(List<ItemInfo> itemInfos) {
        if (CollectionUtils.isEmpty(itemInfos)) {
            return 0;
        }
        for (ItemInfo itemInfo : itemInfos) {
            // Shopid 不可更新
            itemInfo.setShopid(null);
            itemInfo.checkItemId();
        }
        return itemInfoDao.batchUpdate(itemInfos);
    }

    @Override
    public List<PageItemTotalInfoVo> getPageItemTotalInfo(PageItemVo pageItemVo) {
        if (pageItemVo == null) {
            return new ArrayList<PageItemTotalInfoVo>();
        }
        PageHelper.startPage(pageItemVo.getPageNumber(), pageItemVo.getPageSize());
        List<PageItemTotalInfoVo> pageItemTotalInfoVoList = this.itemInfoDao.selectPageItemTotalInfo(pageItemVo);
        PageInfo<PageItemTotalInfoVo> page = new PageInfo<PageItemTotalInfoVo>(pageItemTotalInfoVoList);
        pageItemVo.setPageNumber(page.getPageNum());
        pageItemVo.setPageSize(page.getPageSize());
        pageItemVo.setTotalNum(page.getTotal());
        pageItemVo.setPages(page.getPages());
        return pageItemTotalInfoVoList;
    }

    @Override
    public ItemTotalInfo getItemTotalByItemId(Long itemId) {
        if (itemId == null) {
            return new ItemTotalInfo();
        }
        ItemTotalInfo itemTotalInfo = new ItemTotalInfo();
        ItemInfo itemInfo = itemInfoDao.select(itemId);
        itemTotalInfo.setItemInfo(itemInfo);
        ItemAttribute itemAttribute = itemAttributeService.getRecord(itemId);
        itemTotalInfo.setItemAttribute(itemAttribute);
        List<ItemCdt> itemCdtList = itemCdtService.getRecordsByItemId(itemId);
        itemTotalInfo.setItemCdtList(itemCdtList);
        List<MallSkuInfo> mallSkuInfoList = mallSkuInfoService.getRecordsByItemId(itemId);
        List<MallSkuTotalInfo> mallSkuTotalInfoList = new ArrayList<MallSkuTotalInfo>();
        if (mallSkuInfoList != null && mallSkuInfoList.size() != 0) {
            for (int i = 0; i < mallSkuInfoList.size(); i++) {
                MallSkuTotalInfo mallSkuTotalInfo = new MallSkuTotalInfo();
                List<MallSkuPps> mallSkuPpsList =
                        mallSkuPpsService.getRecordsBySkuId(mallSkuInfoList.get(i).getSkuid());
                mallSkuTotalInfo.setMallSkuInfo(mallSkuInfoList.get(i));
                mallSkuTotalInfo.setMallSkuPpsList(mallSkuPpsList);
                mallSkuTotalInfoList.add(mallSkuTotalInfo);
            }
        }

        itemTotalInfo.setMallSkuTotalInfoList(mallSkuTotalInfoList);
        return itemTotalInfo;
    }

    @Override
    public List<ItemInfo> getRecordsByItemIdList(List<Long> idsList) {
        if (idsList == null) {
            return new ArrayList<ItemInfo>();
        }
        return itemInfoDao.batchSelectByItemIdList(idsList);
    }

    @Override
    public ItemTotalInfoInsertVo insertItemTotalInfo(ItemTotalInfoVo itemTotalInfo) {

        ItemInfo itemInfo = itemTotalInfo.getItemInfo();
        ItemTotalInfoInsertVo insertVo = new ItemTotalInfoInsertVo();

        // 插入itemInfo 获取itemid;
        Long itemId = this.insertRecord(itemInfo);
        insertVo.setItemId(itemId);

        ItemAttribute itemAttribute = itemTotalInfo.getItemAttribute();
        if (itemAttribute != null) {
            itemAttribute.setItemid(itemId);
            this.itemAttributeService.insertRecord(itemAttribute);
        }

        ItemDescription itemDescription = itemTotalInfo.getItemDescription();
        if (itemDescription != null) {
            itemDescription.setItemid(itemId);
            this.itemDescriptionService.insertRecord(itemDescription);
        }
        List<ItemCdt> itemCdts = itemTotalInfo.getItemCdtList();
        if (CollectionUtils.isNotEmpty(itemCdts)) {
            for (ItemCdt itemCdt : itemCdts) {
                itemCdt.setItemid(itemId);
            }
            this.itemCdtService.insertRecords(itemCdts);
        }
        if (CollectionUtils.isNotEmpty(itemTotalInfo.getMallSkuTotalInfoList())) {
            List<Long> skuIdList = new ArrayList<Long>();
            for (MallSkuTotalInfoVo vo : itemTotalInfo.getMallSkuTotalInfoList()) {
                vo.getMallSkuInfo().setItemid(itemId);
                this.mallSkuInfoService.insertRecord(vo.getMallSkuInfo());
                Long skuid = vo.getMallSkuInfo().getSkuid();
                skuIdList.add(skuid);
                if (CollectionUtils.isNotEmpty(vo.getMallSkuPpsList())) {
                    for (MallSkuPps mallSkuPps : vo.getMallSkuPpsList()) {
                        mallSkuPps.setSkuid(skuid);
                    }
                    this.mallSkuPpsService.batchInsertRecord(vo.getMallSkuPpsList());
                }
            }
            insertVo.setSkuIdList(skuIdList);
        }
        if (CollectionUtils.isNotEmpty(itemTotalInfo.getBcsImageList())) {
            // 获取skuid,如果skuid List 非空
            Long skuid = 0L;
            // if (CollectionUtils.isNotEmpty(insertVo.getSkuIdList())) {
            // if (insertVo.getSkuIdList().size() > 1) {
            // throw new RuntimeException("Exception while skuInfo >1 and ImageList >1.");
            // }
            // skuid = insertVo.getSkuIdList().get(0);
            // }
            List<Long> bcsImageIdList = new ArrayList<Long>();
            for (BcsImage bcsImage : itemTotalInfo.getBcsImageList()) {
                bcsImage.setSkuid(skuid);
                bcsImage.setItemid(itemId);
                Long id = this.bcsImageService.insertRecord(bcsImage);
                bcsImageIdList.add(id);
            }
            insertVo.setImageIdList(bcsImageIdList);
        }
        return insertVo;
    }

    @Override
    public Integer updateItemTotalInfo(ItemTotalInfoVo itemTotalInfo) {

        ItemInfo itemInfo = itemTotalInfo.getItemInfo();
        // 更新4张表
        this.updateRecord(itemInfo);
        if (itemTotalInfo.getItemAttribute() != null) {
            this.itemAttributeService.updateRecord(itemTotalInfo.getItemAttribute());
        }
        if (CollectionUtils.isNotEmpty(itemTotalInfo.getItemCdtList())) {
            this.itemCdtService.updateRecords(itemTotalInfo.getItemCdtList());
        }
        if (itemTotalInfo.getItemDescription() != null) {
            this.itemDescriptionService.updateRecord(itemTotalInfo.getItemDescription());
        }
        // 更新Sku两张表
        if (CollectionUtils.isNotEmpty(itemTotalInfo.getMallSkuTotalInfoList())) {
            for (MallSkuTotalInfoVo mallSkuTotalInfoVo : itemTotalInfo.getMallSkuTotalInfoList()) {
                try {
                    this.mallSkuInfoService.updateSkuTotalInfo(mallSkuTotalInfoVo);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        // 更新Image
        if (CollectionUtils.isNotEmpty(itemTotalInfo.getBcsImageList())) {
            this.bcsImageService.updateRecords(itemTotalInfo.getBcsImageList());
        }
        return 1;
    }
}
