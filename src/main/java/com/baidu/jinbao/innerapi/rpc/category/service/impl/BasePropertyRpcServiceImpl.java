package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.BaseProperty;
import com.baidu.jinbao.innerapi.category.service.BasePropertyService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.service.BasePropertyRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class BasePropertyRpcServiceImpl implements BasePropertyRpcService {

    private static final Logger LOG = Logger.getLogger(BasePropertyRpcServiceImpl.class);

    @Autowired
    private BasePropertyService basePropertyService;

    @Override
    @ProtobufRPCService(serviceName = "basePropertyRpcService", methodName = "getRecords")
    public BasePropertySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        BasePropertySearchResponse ret = new BasePropertySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BasePropertyDto> dtoList = new ArrayList<BasePropertyDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<BaseProperty> data = this.basePropertyService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (BaseProperty item : data) {
                    BasePropertyDto dto = new BasePropertyDto();
                    dto.setId(item.getId());
                    dto.setEnName(item.getEnName());
                    dto.setPropertyName(item.getPropertyName());
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in BasePropertyRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "basePropertyRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BasePropertyDtoList data) {
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
            List<BasePropertyDto> dtoList = data.getDtoList();
            List<BaseProperty> boList = new ArrayList<BaseProperty>();
            for (BasePropertyDto dto : dtoList) {
                BaseProperty bo = new BaseProperty();
                if (dto.getPropertyName() != null) {
                    bo.setPropertyName(dto.getPropertyName());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getEnName() == null) {
                    bo.setEnName("");
                } else {
                    bo.setEnName(dto.getEnName());
                }
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, for propertyName maybe null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.basePropertyService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, propertyName maybe null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BasePropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "basePropertyRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BasePropertyDtoList data) {
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
            List<BasePropertyDto> dtoList = data.getDtoList();
            List<BaseProperty> boList = new ArrayList<BaseProperty>();
            for (BasePropertyDto dto : dtoList) {
                BaseProperty bo = new BaseProperty();
                if (dto.getId() == null) {
                    failNum ++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setEnName(dto.getEnName());
                bo.setPropertyName(dto.getPropertyName());
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
            Integer successNum = this.basePropertyService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            }
            else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BasePropertyRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "basePropertyRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.basePropertyService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in BasePropertyRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
