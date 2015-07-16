package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.dao.BcsCspuImageDao;
import com.baidu.jinbao.innerapi.spu.service.BcsCspuImageService;

@Service("bcsCspuImageService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class BcsCspuImageServiceImpl extends BaseService<BcsCspuImage, Long> implements BcsCspuImageService {
    @Autowired
    private BcsCspuImageDao bcsCspuImageDao;

    @Override
    public GenericMapperDao<BcsCspuImage, Long> getDao() {
        return this.bcsCspuImageDao;
    }

    @Override
    public Integer insertRecords(List<BcsCspuImage> bcsCspuImageList) {
        return this.bcsCspuImageDao.batchInsert(bcsCspuImageList);
    }

    @Override
    public Integer updateRecords(List<BcsCspuImage> bcsCspuImageList) {
        return this.bcsCspuImageDao.batchUpdate(bcsCspuImageList);
    }

    @Override
    public Integer deleteRecords(List<Long> bcsCspuImageIdList) {
        return this.bcsCspuImageDao.batchDelete(bcsCspuImageIdList);
    }

    @Override
    public List<BcsCspuImage> getRecords(List<Long> bcsCspuImageIdList) {
        return this.bcsCspuImageDao.batchSelect(bcsCspuImageIdList);
    }
}