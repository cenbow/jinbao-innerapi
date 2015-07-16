package com.baidu.jinbao.innerapi.sku.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.dao.BcsSkuImageDao;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.BcsSkuImageService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Service("bcsSkuImageService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class BcsSkuImageServiceImpl extends BaseService<BcsSkuImage, Long> implements BcsSkuImageService {

    @Autowired
    private BcsSkuImageDao bcsSkuImageDao;
    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public GenericMapperDao<BcsSkuImage, Long> getDao() {
        return bcsSkuImageDao;
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(bcsSkuImageList)) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.bcsSkuImageDao.batchInsert(splitNumber, setSkuInnerId(splitNumber, bcsSkuImageList));
    }

    @Override
    public Integer insertRecordsForCallback(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(bcsSkuImageList)) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        return this.bcsSkuImageDao.batchInsert(splitNumber, setSkuInnerIdForCallback(splitNumber, bcsSkuImageList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<BcsSkuImage> bcsSkuImageList) {
        Assert.notNull(splitDbInfo);
        if (CollectionUtils.isEmpty(bcsSkuImageList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.bcsSkuImageDao.batchUpdate(splitNumber, bcsSkuImageList);
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.bcsSkuImageDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<BcsSkuImage> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        Assert.notNull(splitDbInfo);
        Assert.notNull(condition);

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.bcsSkuImageDao.batchSelect(splitNumber, condition);
    }

    private List<BcsSkuImage> setSkuInnerId(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        for (BcsSkuImage bcsSkuImage : bcsSkuImageList) {
            skuHashKeyList.add((long) bcsSkuImage.getSkuid().hashCode());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }

        List<BcsSkuImage> ret = new ArrayList<BcsSkuImage>();
        for (BcsSkuImage bcsSkuImage : bcsSkuImageList) {
            if (skuInfoMap.containsKey(bcsSkuImage.getSkuid())) {
                bcsSkuImage.setSkuInnerid(skuInfoMap.get(bcsSkuImage.getSkuid()));
                ret.add(bcsSkuImage);
            }
        }
        return ret;
    }

    private List<BcsSkuImage> setSkuInnerIdForCallback(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList) {
        List<Long> skuHashKeyList = new ArrayList<Long>();
        for (BcsSkuImage bcsSkuImage : bcsSkuImageList) {
            skuHashKeyList.add((long) bcsSkuImage.getSkuid().hashCode());
        }
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
        if (CollectionUtils.isEmpty(skuInfoList)) {
            throw new RuntimeException("sku innerids not found");
        }
        if (skuInfoList.size() != skuHashKeyList.size()) {
            throw new RuntimeException("some sku innerids not found");

        }

        Map<String, Long> skuInfoMap = new HashMap<String, Long>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo.getSkuInnerid());
        }

        List<BcsSkuImage> ret = new ArrayList<BcsSkuImage>();
        for (BcsSkuImage bcsSkuImage : bcsSkuImageList) {
            if (skuInfoMap.containsKey(bcsSkuImage.getSkuid())) {
                bcsSkuImage.setSkuInnerid(skuInfoMap.get(bcsSkuImage.getSkuid()));
                ret.add(bcsSkuImage);
            }
        }
        return ret;
    }
}
