package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.BaseVal;
import com.baidu.jinbao.innerapi.category.service.BaseValService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDto;
import com.baidu.jinbao.innerapi.rpc.category.service.BaseValRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class BaseValRpcServiceImpl implements BaseValRpcService {
    private static final Logger LOG = Logger.getLogger(BaseValRpcServiceImpl.class);

    @Autowired
    private BaseValService baseValService;

    @Override
    @ProtobufRPCService(serviceName = "baseValRpcService", methodName = "getRecords")
    public BaseValSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        BaseValSearchResponse ret = new BaseValSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BaseValDto> dtoList = new ArrayList<BaseValDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<BaseVal> data = this.baseValService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (BaseVal item : data) {
                    BaseValDto dto = new BaseValDto();
                    dto.setId(item.getId());
                    dto.setValue(item.getValue());
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in BaseValRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseValRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BaseValDtoList data) {
        int failNum = 0;
        // 返回数据
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
            List<BaseValDto> dtoList = data.getDtoList();
            List<BaseVal> boList = new ArrayList<BaseVal>();
            for (BaseValDto dto : dtoList) {
                BaseVal bo = new BaseVal();
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
            Integer successNum = this.baseValService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe value is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BaseValRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseValRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BaseValDtoList data) {
        int failNum = 0;
        // 返回数据
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
            List<BaseValDto> dtoList = data.getDtoList();
            List<BaseVal> boList = new ArrayList<BaseVal>();
            for (BaseValDto dto : dtoList) {
                BaseVal bo = new BaseVal();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setValue(dto.getValue());
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
            Integer successNum = this.baseValService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BaseValRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseValRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.baseValService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in BaseValRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
