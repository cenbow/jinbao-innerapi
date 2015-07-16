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
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.item.dao.MallSkuInfoDao;
import com.baidu.jinbao.mall.item.service.MallSkuInfoService;
import com.baidu.jinbao.mall.item.service.MallSkuPpsService;
import com.baidu.jinbao.mall.item.vo.MallSkuTotalInfoVo;

@Service("MallSkuInfoService")
@SplitModule(moduleName = PdsConstants.MALL_ITEM_MODULE_DATASOURCE_KEY)
public class MallSkuInfoServiceImpl extends BaseService<MallSkuInfo, Long> implements MallSkuInfoService {

    @Autowired
    private MallSkuInfoDao mallSkuInfoDao;

    @Autowired
    private MallSkuPpsService mallSkuPpsService;

    @Override
    public GenericMapperDao<MallSkuInfo, Long> getDao() {
        return mallSkuInfoDao;
    }

    @Override
    public Integer insertRecord(MallSkuInfo mallSkuInfo) {
        if (mallSkuInfo == null) {
            throw new RuntimeException("mall skuinfo is null");
        }
        mallSkuInfo.checkItemId().checkPropertyValues().checkSellAttribute().checkDefault();
        return this.mallSkuInfoDao.insertOne(mallSkuInfo);
    }

    @Override
    public int updateRecord(MallSkuInfo mallSkuInfo) {
        if (mallSkuInfo == null) {
            throw new RuntimeException("mall skuinfo is null");
        }
        mallSkuInfo.checkUpdateTime().checkSkuId();
        return this.mallSkuInfoDao.update(mallSkuInfo);
    }

    @Override
    public int deleteRecord(Long skuId) {
        if (skuId == null) {
            throw new RuntimeException("skuid is null!");
        }
        return mallSkuInfoDao.delete(skuId);
    }

    @Override
    public MallSkuInfo getRecord(Long skuId) {
        if (skuId == null) {
            throw new RuntimeException("skuid is null!");
        }
        return mallSkuInfoDao.select(skuId);
    }

    @Override
    public Integer updateSkuTotalInfo(MallSkuTotalInfoVo vo) throws Exception {
        Long skuid = vo.getMallSkuInfo().getSkuid();
        this.updateRecord(vo.getMallSkuInfo());
        if (CollectionUtils.isNotEmpty(vo.getMallSkuPpsList())) {
            for (MallSkuPps mallSkuPps : vo.getMallSkuPpsList()) {
                if (mallSkuPps.getSkuid() != skuid) {
                    throw new Exception("skuid is wrong");
                }
            }
        }
        this.mallSkuPpsService.batchUpdateRecord(vo.getMallSkuPpsList());
        return 1;
    }

    @Override
    public Long insertSkuTotalInfo(MallSkuTotalInfoVo vo) throws Exception {
        this.insertRecord(vo.getMallSkuInfo());
        Long skuid = vo.getMallSkuInfo().getSkuid();
        for (MallSkuPps mallSkuPps : vo.getMallSkuPpsList()) {
            mallSkuPps.setSkuid(skuid);
        }
        this.mallSkuPpsService.batchInsertRecord(vo.getMallSkuPpsList());
        return skuid;
    }

    @Override
    public List<MallSkuInfo> getRecordsByItemId(Long itemId) {
        if (itemId == null) {
            return new ArrayList<MallSkuInfo>();
        }
        return mallSkuInfoDao.selectByItemId(itemId);
    }

    @Override
    public List<MallSkuInfo> getRecordsByItemIdList(List<Long> idsList) {
        if (idsList == null) {
            return new ArrayList<MallSkuInfo>();
        }
        return mallSkuInfoDao.batchSelectByItemIdList(idsList);
    }

    @Override
    public List<MallSkuTotalInfo> getSkuTotalInfoByItemIdList(List<Long> itemIdsList) {
        if (itemIdsList == null) {
            return new ArrayList<MallSkuTotalInfo>();
        }
        List<MallSkuInfo> mallSkuInfoList = mallSkuInfoDao.batchSelectByItemIdList(itemIdsList);
        List<MallSkuTotalInfo> mallSkuTotalInfoList = new ArrayList<MallSkuTotalInfo>();
        for (int i = 0; i < mallSkuInfoList.size(); i++) {
            MallSkuTotalInfo mallSkuTotalInfo = new MallSkuTotalInfo();
            List<MallSkuPps> mallSkuPpsList = mallSkuPpsService.getRecordsBySkuId(mallSkuInfoList.get(i).getSkuid());
            mallSkuTotalInfo.setMallSkuInfo(mallSkuInfoList.get(i));
            mallSkuTotalInfo.setMallSkuPpsList(mallSkuPpsList);
            mallSkuTotalInfoList.add(mallSkuTotalInfo);
        }
        return mallSkuTotalInfoList;
    }

    @Override
    public int updateRecords(List<MallSkuInfo> mallSkuInfos) {
        if (CollectionUtils.isEmpty(mallSkuInfos)) {
            return 0;
        }
        return this.mallSkuInfoDao.updateRecords(mallSkuInfos);
    }
}
