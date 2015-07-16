package com.baidu.jinbao.mall.item.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.dao.MallSkuPpsDao;
import com.baidu.jinbao.mall.item.dao.mapper.MallSkuPpsMapper;

@Repository
public class MallSkuPpsDaoImpl extends BaseDao<MallSkuPps, Long> implements MallSkuPpsDao {

    @Autowired
    private MallSkuPpsMapper mallSkuPpsMapper;

    @Override
    public GenericMapper<MallSkuPps, Long> getMapper() {
        return this.mallSkuPpsMapper;
    }

    @Override
    public Long insertOne(MallSkuPps mallSkuPps) {
        if (mallSkuPps == null) {
            return 0L;
        }
        this.mallSkuPpsMapper.insertOne(mallSkuPps);
        return mallSkuPps.getId();
    }

    @Override
    public int update(MallSkuPps mallSkuPps) {
        if (mallSkuPps == null) {
            return 0;
        }
        return this.mallSkuPpsMapper.update(mallSkuPps);
    }

    @Override
    public int delete(String ppsMd5) {
        if (ppsMd5 == null) {
            return 0;
        }
        return this.mallSkuPpsMapper.delete(ppsMd5);
    }

    @Override
    public MallSkuPps select(String ppsMd5) {
        if (ppsMd5 == null) {
            return new MallSkuPps();
        }
        return this.mallSkuPpsMapper.select(ppsMd5);
    }

    @Override
    public int batchUpdate(List<MallSkuPps> mallSkuPpsList) {
        if (CollectionUtils.isEmpty(mallSkuPpsList)) {
            return 0;
        }
        return this.mallSkuPpsMapper.batchUpdate(mallSkuPpsList);
    }

    @Override
    public int batchInsert(List<MallSkuPps> mallSkuPpsList) {
        if (mallSkuPpsList == null) {
            return 0;
        }
        if (mallSkuPpsList.size() == 0) {
            return 0;
        }
        return this.mallSkuPpsMapper.batchInsert(mallSkuPpsList);
    }

    @Override
    public List<MallSkuPps> batchSelectBySkuIdList(List<Long> skuIdList) {
        return this.mallSkuPpsMapper.batchSelectBySkuIdList(skuIdList);
    }

    @Override
    public List<MallSkuPps> selectBySkuId(Long skuid) {
        return this.mallSkuPpsMapper.selectBySkuId(skuid);
    }

    @Override
    public List<MallSkuPps> selectByPpsMd5(List<String> ppsMd5List) {
        return this.mallSkuPpsMapper.selectByPpsMd5(ppsMd5List);
    }

}
