package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.service.CspuInfoRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.CspuInfoTransfer;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.service.CspuInfoService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CspuInfoRpcServiceImpl implements CspuInfoRpcService {

    private static final Logger LOG = Logger.getLogger(CspuInfoRpcServiceImpl.class);

    @Autowired
    private CspuInfoService cspuInfoService;

    @Override
    @ProtobufRPCService(serviceName = "cspuInfoRpcService", methodName = "getRecords")
    public CspuInfoSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        CspuInfoSearchResponse ret = new CspuInfoSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CspuInfoDto> dtoList = new ArrayList<CspuInfoDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getSpuIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<CspuInfo> data = this.cspuInfoService.getRecords(condition.getSpuIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CspuInfo item : data) {
                    CspuInfoDto dto = CspuInfoTransfer.transBoToDto(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CspuInfoRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuInfoRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CspuInfoDtoList data) {
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
            List<CspuInfoDto> dtoList = data.getDtoList();
            List<CspuInfo> boList = new ArrayList<CspuInfo>();
            for (CspuInfoDto dto : dtoList) {
                if ((dto == null) || !CspuInfoTransfer.checkRequiredField(dto)) {
                    continue;
                }
                CspuInfo bo = CspuInfoTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuInfoService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cid, name, bigField, cspuFrom fields are required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cid, name, bigField, cspuFrom fields are required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in CspuInfoRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuInfoRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CspuInfoDtoList data) {
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
            List<CspuInfoDto> dtoList = data.getDtoList();
            List<CspuInfo> boList = new ArrayList<CspuInfo>();
            for (CspuInfoDto dto : dtoList) {
                if ((dto == null) || (dto.getCspuid() == null)) {
                    continue;
                }
                CspuInfo bo = CspuInfoTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.cspuInfoService.updateRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cspuid field is required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'"
                        + "3.Machine data(cspuFrom = 1) can't overwrite human data(cspuFrom = 0)");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cspuid field required. "
                        + "2.Date format hould be 'yyyy-MM-dd HH:mm:ss'"
                        + "3.Machine data(cspuFrom = 1) can't overwrite human data(cspuFrom = 0)");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in CspuInfoRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cspuInfoRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(SpuCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getCspuidList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Cspuid List should not be empty");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }

        try {
            // 删除IDList 关联的记录
            Integer successNum = this.cspuInfoService.deleteRecords(condition.getCspuidList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CspuInfoRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
