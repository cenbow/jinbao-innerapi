package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.service.CspuLevelRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.CspuLevelTransfer;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;
import com.baidu.jinbao.innerapi.spu.service.CspuLevelService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CspuLevelRpcServiceImpl implements CspuLevelRpcService {

    private static final Logger LOG = Logger.getLogger(CspuLevelRpcServiceImpl.class);

    @Autowired
    private CspuLevelService cspuLevelService;

    @Override
    @ProtobufRPCService(serviceName = "cspuLevelRpcService", methodName = "getRecords")
    public CspuLevelSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        CspuLevelSearchResponse ret = new CspuLevelSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CspuLevelDto> dtoList = new ArrayList<CspuLevelDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<CspuLevel> data = this.cspuLevelService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CspuLevel item : data) {
                    CspuLevelDto dto = CspuLevelTransfer.transBoToDto(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CspuLevelRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuLevelRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CspuLevelDtoList data) {
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
            List<CspuLevelDto> dtoList = data.getDtoList();
            List<CspuLevel> boList = new ArrayList<CspuLevel>();
            for (CspuLevelDto dto : dtoList) {
                if ((dto == null) || !CspuLevelTransfer.checkRequiredField(dto)) {
                    continue;
                }
                CspuLevel bo = CspuLevelTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuLevelService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cspuid field is required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cspuid field is required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in CspuLevelRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuLevelRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CspuLevelDtoList data) {
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
            List<CspuLevelDto> dtoList = data.getDtoList();
            List<CspuLevel> boList = new ArrayList<CspuLevel>();
            for (CspuLevelDto dto : dtoList) {
                if ((dto == null) || (dto.getId() == null)) {
                    continue;
                }
                CspuLevel bo = CspuLevelTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuLevelService.updateRecords(boList);
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
            LOG.error("Exception in CspuLevelRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuLevelRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.cspuLevelService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CspuLevelRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
