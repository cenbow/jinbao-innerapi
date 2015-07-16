package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.service.CspuCommentRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.CspuCommentTransfer;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.service.CspuCommentService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CspuCommentRpcServiceImpl implements CspuCommentRpcService {

    private static final Logger LOG = Logger.getLogger(CspuCommentRpcServiceImpl.class);

    @Autowired
    private CspuCommentService cspuCommentService;

    @Override
    @ProtobufRPCService(serviceName = "cspuCommentRpcService", methodName = "getRecords")
    public CspuCommentSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        CspuCommentSearchResponse ret = new CspuCommentSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CspuCommentDto> dtoList = new ArrayList<CspuCommentDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<CspuComment> data = this.cspuCommentService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CspuComment item : data) {
                    CspuCommentDto dto = CspuCommentTransfer.transBoToDTo(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CspuCommentRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuCommentRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CspuCommentDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data list should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到 BO的转换
            List<CspuCommentDto> dtoList = data.getDtoList();
            List<CspuComment> boList = new ArrayList<CspuComment>();
            for (CspuCommentDto dto : dtoList) {
                if ((dto == null) || !CspuCommentTransfer.checkRequiredField(dto)) {
                    continue;
                }
                CspuComment bo = CspuCommentTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuCommentService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cspuid field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cspuid field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in CspuCommentRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuCommentRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CspuCommentDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data list should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到 BO的转换
            List<CspuCommentDto> dtoList = data.getDtoList();
            List<CspuComment> boList = new ArrayList<CspuComment>();
            for (CspuCommentDto dto : dtoList) {
                if ((dto == null) || (dto.getId() == null)) {
                    continue;
                }
                CspuComment bo = CspuCommentTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuCommentService.updateRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Id field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Id field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in CspuCommentRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuCommentRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(SpuCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }

        try {
            // 删除IDList 关联的记录
            Integer successNum = this.cspuCommentService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CspuCommentRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
