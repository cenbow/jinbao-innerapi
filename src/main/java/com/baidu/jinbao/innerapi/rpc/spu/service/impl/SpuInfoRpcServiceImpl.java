package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.service.SpuInfoRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.SpuInfoTransfer;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;
import com.baidu.jinbao.innerapi.spu.service.SpuInfoService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class SpuInfoRpcServiceImpl implements SpuInfoRpcService {

    private static final Logger LOG = Logger.getLogger(SpuInfoRpcServiceImpl.class);

    @Autowired
    private SpuInfoService spuInfoService;

    @Override
    @ProtobufRPCService(serviceName = "spuInfoRpcService", methodName = "getRecords")
    public SpuInfoSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        SpuInfoSearchResponse ret = new SpuInfoSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<SpuInfoDto> dtoList = new ArrayList<SpuInfoDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getSpuIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<SpuInfo> data = this.spuInfoService.getRecords(condition.getSpuIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (SpuInfo item : data) {
                    SpuInfoDto dto = SpuInfoTransfer.tranBoToDto(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in SpuInfoRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuInfoRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(SpuInfoDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data List should not be empty");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到 BO的转换
            List<SpuInfoDto> dtoList = data.getDtoList();
            List<SpuInfo> boList = new ArrayList<SpuInfo>();
            for (SpuInfoDto dto : dtoList) {
                if ((dto == null) || !SpuInfoTransfer.checkRequiredField(dto)) {
                    continue;
                }
                SpuInfo bo = SpuInfoTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.spuInfoService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cid and name fields are required. "
                        + "2. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cid and name fields are required. "
                        + "2. Date format shoule be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in SpuInfoRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuInfoRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(SpuInfoDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data List should not be empty");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到 BO的转换
            List<SpuInfoDto> dtoList = data.getDtoList();
            List<SpuInfo> boList = new ArrayList<SpuInfo>();
            for (SpuInfoDto dto : dtoList) {
                if ((dto == null) || (dto.getSpuid() == null)) {
                    continue;
                }
                SpuInfo bo = SpuInfoTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.spuInfoService.updateRecords(boList);
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
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in SpuInfoRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "spuInfoRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(SpuCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getSpuIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }

        try {
            // 删除IDList 关联的记录
            Integer successNum = this.spuInfoService.deleteRecords(condition.getSpuIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in SpuInfoRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
