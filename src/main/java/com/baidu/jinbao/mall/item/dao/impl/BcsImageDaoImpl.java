package com.baidu.jinbao.mall.item.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.dao.BcsImageDao;
import com.baidu.jinbao.mall.item.dao.mapper.BcsImageMapper;

@Repository
public class BcsImageDaoImpl extends BaseDao<BcsImage, Long> implements BcsImageDao {

    @Autowired
    private BcsImageMapper bcsImageMapper;

    @Override
    public GenericMapper<BcsImage, Long> getMapper() {
        return this.bcsImageMapper;
    }

    @Override
    public Long insertOne(BcsImage bcsImage) {
        if (bcsImage == null) {
            return 0L;
        }
        this.bcsImageMapper.insertOne(bcsImage);
        return bcsImage.getId();
    }

    @Override
    public Integer update(BcsImage bcsImage) {
        if (bcsImage == null) {
            return 0;
        }
        return  bcsImageMapper.update(bcsImage);
    }

    @Override
    public Integer delete(Long id) {
        if (id == null) {
            return 0;
        }
        return bcsImageMapper.delete(id);
    }

    @Override
    public BcsImage select(Long id) {
        return bcsImageMapper.select(id);
    }

    @Override
    public int insertRecords(List<BcsImage> bcsImageList) {
        if (CollectionUtils.isEmpty(bcsImageList)) {
            return 0;
        }
        return this.bcsImageMapper.insertRecords(bcsImageList);
    }

    @Override
    public int updateRecords(List<BcsImage> bcsImageList) {
        if (CollectionUtils.isEmpty(bcsImageList)) {
            return 0;
        }
        return this.bcsImageMapper.updateRecords(bcsImageList);
    }

    @Override
    public List<BcsImage> getRecordsBySkuIdOrItemId(List<Long> itemIdList, List<Long> skuIdList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemid", itemIdList);
        map.put("skuid", skuIdList);
        return this.bcsImageMapper.getRecordsBySkuIdOrItemId(map);
    }

    @Override
    public Integer batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }
        return this.bcsImageMapper.batchDelete(idList);
    }

}
