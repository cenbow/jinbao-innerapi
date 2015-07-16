package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.dao.SpuCommentDao;
import com.baidu.jinbao.innerapi.spu.service.SpuCommentService;

@Service("spuCommentService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class SpuCommentServiceImpl extends BaseService<SpuComment, Long> implements SpuCommentService {
    @Autowired
    private SpuCommentDao spuCommentDao;

    @Override
    public GenericMapperDao<SpuComment, Long> getDao() {
        return this.spuCommentDao;
    }

    @Override
    public Integer insertRecords(List<SpuComment> spuCommentList) {
        return this.spuCommentDao.batchInsert(spuCommentList);
    }

    @Override
    public Integer updateRecords(List<SpuComment> spuCommentList) {
        return this.spuCommentDao.batchUpdate(spuCommentList);
    }

    @Override
    public Integer deleteRecords(List<Long> spuCommentIdList) {
        return this.spuCommentDao.batchDelete(spuCommentIdList);
    }

    @Override
    public List<SpuComment> getRecords(List<Long> spuCommentIdList) {
        return this.spuCommentDao.batchSelect(spuCommentIdList);
    }
}