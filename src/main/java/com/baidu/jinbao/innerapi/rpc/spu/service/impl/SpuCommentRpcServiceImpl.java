package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.service.SpuCommentRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.SpuCommentTransfer;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.service.SpuCommentService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class SpuCommentRpcServiceImpl implements SpuCommentRpcService {

    private static final Logger LOG = Logger.getLogger(SpuCommentRpcServiceImpl.class);

    @Autowired
    private SpuCommentService spuCommentService;

    @Override
    @ProtobufRPCService(serviceName = "spuCommentRpcService", methodName = "getRecords")
    public SpuCommentSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        SpuCommentSearchResponse ret = new SpuCommentSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<SpuCommentDto> dtoList = new ArrayList<SpuCommentDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<SpuComment> data = this.spuCommentService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (SpuComment item : data) {
                    SpuCommentDto dto = SpuCommentTransfer.transBoToDto(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in SpuCommentRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuCommentRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(SpuCommentDtoList data) {
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
            List<SpuCommentDto> dtoList = data.getDtoList();
            List<SpuComment> boList = new ArrayList<SpuComment>();
            for (SpuCommentDto dto : dtoList) {
                if ((dto == null) || !SpuCommentTransfer.checkRequiredField(dto)) {
                    continue;
                }
                SpuComment bo = SpuCommentTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.spuCommentService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Spuid field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Spuid field is required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in SpuCommentRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuCommentRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(SpuCommentDtoList data) {
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
            List<SpuCommentDto> dtoList = data.getDtoList();
            List<SpuComment> boList = new ArrayList<SpuComment>();
            for (SpuCommentDto dto : dtoList) {
                if ((dto == null) || (dto.getId() == null)) {
                    continue;
                }
                SpuComment bo = SpuCommentTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.spuCommentService.updateRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Id field is required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Id field is required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in SpuCommentRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuCommentRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.spuCommentService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in SpuCommentRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
