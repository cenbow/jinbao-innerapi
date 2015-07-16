package com.baidu.jinbao.mall.item.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.dao.MallSkuInfoDao;
import com.baidu.jinbao.mall.item.dao.mapper.MallSkuInfoMapper;

@Repository
public class MallSkuInfoDaoImpl extends BaseDao<MallSkuInfo, Long> implements MallSkuInfoDao {

    @Autowired
    private MallSkuInfoMapper mallSkuInfoMapper;

    @Override
    public GenericMapper<MallSkuInfo, Long> getMapper() {
        return this.mallSkuInfoMapper;
    }

    @Override
    public Integer insertOne(MallSkuInfo mallSkuInfo) {
        if (mallSkuInfo == null) {
            return 0;
        }
        return this.mallSkuInfoMapper.insertOne(mallSkuInfo);
    }

    @Override
    public int update(MallSkuInfo mallSkuInfo) {
        if (mallSkuInfo == null) {
            return 0;
        }
        return this.mallSkuInfoMapper.update(mallSkuInfo);
    }

    @Override
    public int delete(Long skuId) {
        if (skuId == null) {
            return 0;
        }
        return this.mallSkuInfoMapper.delete(skuId);
    }

    @Override
    public MallSkuInfo select(Long skuId) {
        if (skuId == null) {
            return new MallSkuInfo();
        }
        return this.mallSkuInfoMapper.select(skuId);
    }

    @Override
    public List<MallSkuInfo> selectByItemId(Long itemid) {
        return this.mallSkuInfoMapper.selectByItemId(itemid);
    }

    @Override
    public List<MallSkuInfo> batchSelectByItemIdList(List<Long> idsList) {
        return this.mallSkuInfoMapper.batchSelectByItemIdList(idsList);
    }

    @Override
    public int updateRecords(List<MallSkuInfo> mallSkuInfoList) {
        if (CollectionUtils.isEmpty(mallSkuInfoList)) {
            throw new RuntimeException("collection is empty !");
        }
        return this.mallSkuInfoMapper.updateRecords(mallSkuInfoList);
    }

}
