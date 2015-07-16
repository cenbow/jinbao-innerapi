package com.baidu.jinbao.mall.item.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.dao.BcsImageDao;
import com.baidu.jinbao.mall.item.service.BcsImageService;

/**
 * BcsImage服务
 * 
 * @author duzeyu
 * @date 2015-7-5 下午4:20
 */

@Service("BcsImageService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class BcsImageServiceImpl extends BaseService<BcsImage, Long> implements BcsImageService {

    @Autowired
    private BcsImageDao bcsImageDao;

    @Override
    public GenericMapperDao<BcsImage, Long> getDao() {
        return bcsImageDao;
    }

    @Override
    public Long insertRecord(BcsImage bcsImage) {
        if (bcsImage == null) {
            throw new RuntimeException("bcsImage is null!");
        }
        bcsImage =
                bcsImage.checkContentMd5().checkGipsImage().checkHeight().checkImageBcsUrl().checkImageUrl()
                        .checkItemId().checkPicType().checkSequence().checkWidth().checkDefault();
        this.bcsImageDao.insertOne(bcsImage);
        return bcsImage.getId();
    }

    @Override
    public Integer updateRecord(BcsImage bcsImage) {
        if (bcsImage == null) {
            throw new RuntimeException("bcsImage is null!");
        }
        bcsImage.checkId();
        return this.bcsImageDao.update(bcsImage);
    }

    @Override
    public Long deleteRecord(Long id) {
        if (id == null) {
            throw new RuntimeException("id is null!");
        }
        this.bcsImageDao.delete(id);
        return id;
    }

    @Override
    public BcsImage getRecord(Long id) {
        if (id == null) {
            throw new RuntimeException("id is null!");
        }
        return this.bcsImageDao.select(id);
    }

    @Override
    public Integer insertRecords(List<BcsImage> bcsImageList) {
        if (CollectionUtils.isEmpty(bcsImageList)) {
            return 0;
        }
        return this.bcsImageDao.insertRecords(bcsImageList);
    }

    @Override
    public Integer updateRecords(List<BcsImage> bcsImageList) {
        if (CollectionUtils.isEmpty(bcsImageList)) {
            throw new RuntimeException("bcsImageList is null!");
        }
        return this.bcsImageDao.updateRecords(bcsImageList);
    }

    @Override
    public List<BcsImage> getRecordsBySkuIdOrItemId(List<Long> itemIdList, List<Long> skuIdList) {
        if (CollectionUtils.isEmpty(itemIdList) && (CollectionUtils.isEmpty(skuIdList))) {
            throw new RuntimeException("input are all null");
        }

        return this.bcsImageDao.getRecordsBySkuIdOrItemId(itemIdList, skuIdList);

    }

    @Override
    public Integer deleteRecords(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new RuntimeException("idList is Empty");
        }
        return this.bcsImageDao.batchDelete(idList);
    }
}
