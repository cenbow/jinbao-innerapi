package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.dao.CspuCommentDao;
import com.baidu.jinbao.innerapi.spu.service.CspuCommentService;

@Service("cspuCommentService")
@SplitModule(moduleName = PdsConstants.SPU_MODULE_DATASOURCE_KEY)
public class CspuCommentServiceImpl extends BaseService<CspuComment, Long> implements CspuCommentService {
    @Autowired
    private CspuCommentDao cspuCommentDao;

    @Override
    public GenericMapperDao<CspuComment, Long> getDao() {
        return this.cspuCommentDao;
    }

    @Override
    public Integer insertRecords(List<CspuComment> cspuCommentList) {
        return this.cspuCommentDao.batchInsert(cspuCommentList);
    }

    @Override
    public Integer updateRecords(List<CspuComment> cspuCommentList) {
        return this.cspuCommentDao.batchUpdate(cspuCommentList);
    }

    @Override
    public Integer deleteRecords(List<Long> cspuCommentIdList) {
        return this.cspuCommentDao.batchDelete(cspuCommentIdList);
    }

    @Override
    public List<CspuComment> getRecords(List<Long> cspuCommentIdList) {
        return this.cspuCommentDao.batchSelect(cspuCommentIdList);
    }
}