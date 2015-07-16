package com.baidu.jinbao.innerapi.sku.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.dao.BcsSkuImageDao;
import com.baidu.jinbao.innerapi.sku.dao.mapper.BcsSkuImageMapper;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;

@Repository
public class BcsSkuImageDaoImpl extends BaseDao<BcsSkuImage, Long> implements BcsSkuImageDao {

    @Autowired
    private BcsSkuImageMapper bcsSkuImageMapper;

    @Override
    public GenericMapper<BcsSkuImage, Long> getMapper() {
        return this.bcsSkuImageMapper;
    }

    @Override
    public Integer batchInsert(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(bcsSkuImageList)) {
            return 0;
        }

        return this.bcsSkuImageMapper.batchInsert(splitNumber, bcsSkuImageList);
    }

    @Override
    public Integer batchUpdate(Integer splitNumber, List<BcsSkuImage> bcsSkuImageList) {
        if ((splitNumber == null) || CollectionUtils.isEmpty(bcsSkuImageList)) {
            return 0;
        }

        return this.bcsSkuImageMapper.batchUpdate(splitNumber, bcsSkuImageList);
    }

    @Override
    public Integer batchDelete(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return 0;
        }

        return this.bcsSkuImageMapper.batchDelete(splitNumber, condition);
    }

    @Override
    public List<BcsSkuImage> batchSelect(Integer splitNumber, SkuQueryCondition condition) {
        if ((splitNumber == null) || (condition == null)) {
            return new ArrayList<BcsSkuImage>();
        }

        return this.bcsSkuImageMapper.batchSelect(splitNumber, condition);
    }
}
