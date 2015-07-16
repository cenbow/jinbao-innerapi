package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.BaseCmValue;
import com.baidu.jinbao.innerapi.category.service.BaseCmValueService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmValueDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmValueSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.service.BaseCmValueRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class BaseCmValueRpcServiceImpl implements BaseCmValueRpcService {

    private static final Logger LOG = Logger.getLogger(BaseCmValueRpcServiceImpl.class);

    @Autowired
    private BaseCmValueService baseCmValueService;

    @Override
    @ProtobufRPCService(serviceName = "baseCmValueRpcService", methodName = "getRecords")
    public BaseCmValueSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        BaseCmValueSearchResponse ret = new BaseCmValueSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BaseCmValueDto> dtoList = new ArrayList<BaseCmValueDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<BaseCmValue> data = this.baseCmValueService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (BaseCmValue item : data) {
                    BaseCmValueDto dto = new BaseCmValueDto();
                    dto.setId(item.getId());
                    dto.setValue(item.getValue());
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseCmValueRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BaseCmValueDtoList data) {
        int failNum = 0;
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if (data == null || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到BO 的转换
            List<BaseCmValueDto> dtoList = data.getDtoList();
            List<BaseCmValue> boList = new ArrayList<BaseCmValue>();
            for (BaseCmValueDto dto : dtoList) {
                BaseCmValue bo = new BaseCmValue();
                if (dto.getValue() == null) {
                    failNum++;
                    continue;
                }
                bo.setValue(dto.getValue());
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, for value is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = baseCmValueService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe value is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @ProtobufRPCService(serviceName = "BaseCmValueRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BaseCmValueDtoList data) {
        int failNum = 0;
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if (data == null || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);

            return ret;
        }

        try {
            // DTO 到BO 的转换
            List<BaseCmValueDto> dtoList = data.getDtoList();
            List<BaseCmValue> boList = new ArrayList<BaseCmValue>();
            for (BaseCmValueDto dto : dtoList) {
                BaseCmValue bo = new BaseCmValue();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getValue() != null) {
                    bo.setValue(dto.getValue());
                }
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, for id is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据更新
            Integer successNum = this.baseCmValueService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @ProtobufRPCService(serviceName = "baseCmValueRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(CategoryCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }

        try {
            // 删除IDList 关联的记录
            Integer successNum = this.baseCmValueService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
