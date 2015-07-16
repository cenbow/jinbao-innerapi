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
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.dao.MallSkuPpsDao;
import com.baidu.jinbao.mall.item.service.MallSkuPpsService;

@Service("MallSkuPpsService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class MallSkuPpsServiceImpl extends BaseService<MallSkuPps, Long> implements MallSkuPpsService {

    @Autowired
    private MallSkuPpsDao mallSkuPpsDao;

    @Override
    public GenericMapperDao<MallSkuPps, Long> getDao() {
        return mallSkuPpsDao;
    }

    @Override
    public Long insertRecord(MallSkuPps mallSkuPps) {
        if (mallSkuPps == null) {
            throw new RuntimeException("mallSkuPps is null!");
        }
        mallSkuPps.checkDiscountPrice().checkMerchantId().checkPrice().checkShopId().checkSkuId().checkStock()
                .checkDefault();
        mallSkuPps.setPpsMd5(MD5Util.getMD5Value(String.valueOf(mallSkuPps.getSkuid())
                + String.valueOf(mallSkuPps.getRegionid()) + String.valueOf(mallSkuPps.getPcMobile())));

        return this.mallSkuPpsDao.insertOne(mallSkuPps);
    }

    @Override
    public int updateRecord(MallSkuPps mallSkuPps) {
        if (mallSkuPps == null) {
            throw new RuntimeException("mallSkuPps is null!");
        }
        // sku_id, region_id, pc_mobile must input
        if (mallSkuPps.getRegionid() == null || mallSkuPps.getSkuid() == null || mallSkuPps.getPcMobile() == null) {
            return 0;

        }
        // set md5
        mallSkuPps.setPpsMd5(MD5Util.getMD5Value(String.valueOf(mallSkuPps.getSkuid())
                + String.valueOf(mallSkuPps.getRegionid()) + String.valueOf(mallSkuPps.getPcMobile())));

        return this.mallSkuPpsDao.update(mallSkuPps);
    }

    @Override
    public int deleteRecord(String ppsMd5) {
        if (ppsMd5 == null) {
            throw new RuntimeException("ppsMd5 is null!");
        }
        return this.mallSkuPpsDao.delete(ppsMd5);
    }

    @Override
    public MallSkuPps getRecord(String ppsMd5) {
        if (ppsMd5 == null) {
            throw new RuntimeException("ppsMd5 is null!");
        }
        return this.mallSkuPpsDao.select(ppsMd5);
    }

    @Override
    public int batchUpdateRecord(List<MallSkuPps> mallSkuPpsList) {
        if (CollectionUtils.isEmpty(mallSkuPpsList)) {
            return 0;
        }

        List<MallSkuPps> checkList = new ArrayList<MallSkuPps>();
        for (MallSkuPps item : mallSkuPpsList) {
            // sku_id, region_id, pc_mobile must input
            if (item.getRegionid() == null || item.getSkuid() == null || item.getPcMobile() == null) {
                throw new RuntimeException(" mallSkuPpsList skuid, regionid or pc_mobile is empty!");
            }
            // set md5
            item.setPpsMd5(MD5Util.getMD5Value(String.valueOf(item.getSkuid()) + String.valueOf(item.getRegionid())
                    + String.valueOf(item.getPcMobile())));
            checkList.add(item);
        }
        if (CollectionUtils.isNotEmpty(checkList)) {
            return this.mallSkuPpsDao.batchUpdate(checkList);
        }

        return 0;

    }

    @Override
    public int batchInsertRecord(List<MallSkuPps> mallSkuPpsList) {
        if (CollectionUtils.isEmpty(mallSkuPpsList)) {
            throw new RuntimeException(" mallSkuPpsList is empty!");
        }
        for (MallSkuPps mallSkuPps : mallSkuPpsList) {
            mallSkuPps.checkDiscountPrice().checkMerchantId().checkPrice().checkShopId().checkSkuId().checkStock()
                    .checkDefault();
            mallSkuPps.setPpsMd5(MD5Util.getMD5Value(String.valueOf(mallSkuPps.getSkuid())
                    + String.valueOf(mallSkuPps.getRegionid()) + String.valueOf(mallSkuPps.getPcMobile())));
        }

        return this.mallSkuPpsDao.batchInsert(mallSkuPpsList);
    }

    @Override
    public List<MallSkuPps> batchGetRecordsBySkuIds(List<Long> skuIdList) {
        if (skuIdList == null) {
            return new ArrayList<MallSkuPps>();
        }
        return mallSkuPpsDao.batchSelectBySkuIdList(skuIdList);
    }

    @Override
    public List<MallSkuPps> getRecordsBySkuId(Long skuid) {
        if (skuid == null) {
            return new ArrayList<MallSkuPps>();
        }
        return mallSkuPpsDao.selectBySkuId(skuid);
    }

    @Override
    public List<MallSkuPps> getSkuPpsInfoList(List<Long> skuIdList, 
            List<Integer> regionIdList, List<Integer> deviceList) {

        List<String> ppsMd5List = new ArrayList<String>();

        if (CollectionUtils.isEmpty(skuIdList) || CollectionUtils.isEmpty(regionIdList)
                || CollectionUtils.isEmpty(deviceList)) {
            throw new RuntimeException("list is empty!");
        }
        if (!(skuIdList.size() == regionIdList.size() && skuIdList.size() == deviceList.size())) {
            throw new RuntimeException("list size not equal!");
        }
        for (int i = 0; i < skuIdList.size(); i++) {
            String md5 =
                    MD5Util.getMD5Value(String.valueOf(skuIdList.get(i)) + String.valueOf(regionIdList.get(i))
                            + String.valueOf(deviceList.get(i)));
            ppsMd5List.add(md5);
        }

        return this.mallSkuPpsDao.selectByPpsMd5(ppsMd5List);

    }

}
