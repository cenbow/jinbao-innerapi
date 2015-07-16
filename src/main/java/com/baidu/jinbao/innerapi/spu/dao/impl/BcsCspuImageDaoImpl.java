package com.baidu.jinbao.innerapi.spu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.dao.BcsCspuImageDao;
import com.baidu.jinbao.innerapi.spu.dao.mapper.BcsCspuImageMapper;

@Repository
public class BcsCspuImageDaoImpl extends BaseDao<BcsCspuImage, Long> implements BcsCspuImageDao {
    @Autowired
    private BcsCspuImageMapper bcsCspuImageMapper;

    @Override
    public GenericMapper<BcsCspuImage, Long> getMapper() {
        return this.bcsCspuImageMapper;
    }

    @Override
    public Integer batchInsert(List<BcsCspuImage> bcsCspuImages) {
        if (CollectionUtils.isEmpty(bcsCspuImages)) {
            return 0;
        }
        return this.bcsCspuImageMapper.batchInsert(bcsCspuImages);
    }

    @Override
    public Integer batchUpdate(List<BcsCspuImage> bcsCspuImages) {
        if (CollectionUtils.isEmpty(bcsCspuImages)) {
            return 0;
        }
        return this.bcsCspuImageMapper.batchUpdate(bcsCspuImages);
    }

    @Override
    public Integer batchDelete(List<Long> bcsCspuImageIds) {
        if (CollectionUtils.isEmpty(bcsCspuImageIds)) {
            return 0;
        }
        return this.bcsCspuImageMapper.batchDelete(bcsCspuImageIds);
    }

    @Override
    public List<BcsCspuImage> batchSelect(List<Long> bcsCspuImageIds) {
        if (CollectionUtils.isEmpty(bcsCspuImageIds)) {
            return new ArrayList<BcsCspuImage>();
        }
        return this.bcsCspuImageMapper.batchSelect(bcsCspuImageIds);
    }
}
