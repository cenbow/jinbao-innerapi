package com.baidu.jinbao.innerapi.rpc.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageDto;
import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageDtoList;
import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageSearchResponse;
import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCondition;
import com.baidu.jinbao.innerapi.rpc.spu.service.BcsCspuImageRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.spu.BcsCspuImageTransfer;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.service.BcsCspuImageService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class BcsCspuImageRpcServiceImpl implements BcsCspuImageRpcService {

    private static final Logger LOG = Logger.getLogger(BcsCspuImageRpcServiceImpl.class);

    @Autowired
    private BcsCspuImageService bcsCspuImageService;

    @Override
    @ProtobufRPCService(serviceName = "bcsCspuImageRpcService", methodName = "getRecords")
    public BcsCspuImageSearchResponse getRecords(SpuCondition condition) {
        // 返回数据
        BcsCspuImageSearchResponse ret = new BcsCspuImageSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BcsCspuImageDto> dtoList = new ArrayList<BcsCspuImageDto>();

        // 查询条件为空
        if ((condition == null) || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Id List should not be empty");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<BcsCspuImage> data = this.bcsCspuImageService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (BcsCspuImage item : data) {
                    BcsCspuImageDto dto = BcsCspuImageTransfer.transBoToDto(item);
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in BcsCspuImageRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsCspuImageRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BcsCspuImageDtoList data) {
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
            List<BcsCspuImageDto> dtoList = data.getDtoList();
            List<BcsCspuImage> boList = new ArrayList<BcsCspuImage>();
            for (BcsCspuImageDto dto : dtoList) {
                if ((dto == null) || !BcsCspuImageTransfer.checkRequiredField(dto)) {
                    continue;
                }
                BcsCspuImage bo = BcsCspuImageTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.bcsCspuImageService.insertRecords(boList);
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1.Cspuid and imageType fields are required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1.Cspuid and imageType fields are required. "
                        + "2.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in BcsCspuImageRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsCspuImageRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BcsCspuImageDtoList data) {
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
            List<BcsCspuImageDto> dtoList = data.getDtoList();
            List<BcsCspuImage> boList = new ArrayList<BcsCspuImage>();
            for (BcsCspuImageDto dto : dtoList) {
                if ((dto == null) || (dto.getId() == null)) {
                    continue;
                }
                BcsCspuImage bo = BcsCspuImageTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    boList.add(bo);
                }
            }

            // 数据插入
            Integer successNum = this.bcsCspuImageService.updateRecords(boList);
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
            LOG.error("Exception in BcsCspuImageRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsCspuImageRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.bcsCspuImageService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(condition.getIdList().size() - successNum);

        } catch (Exception e) {
            LOG.error("Exception in BcsCspuImageRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
