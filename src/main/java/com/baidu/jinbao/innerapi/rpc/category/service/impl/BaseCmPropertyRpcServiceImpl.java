package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.BaseCmProperty;
import com.baidu.jinbao.innerapi.category.service.BaseCmPropertyService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmPropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseCmPropertySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.service.BaseCmPropertyRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

/**
 * BaseCmPropertyRpcServiceImpl
 * 
 * @author cgd
 * @date 2015年6月10日 下午8:51:43
 */
@Component
@RpcExporter(port = "8999")
public class BaseCmPropertyRpcServiceImpl implements BaseCmPropertyRpcService {

    private static final Logger LOG = Logger.getLogger(BaseCmPropertyRpcServiceImpl.class);

    @Autowired
    private BaseCmPropertyService baseCmPropertyService;

    @Override
    @ProtobufRPCService(serviceName = "baseCmPropertyRpcService", methodName = "getRecords")
    public BaseCmPropertySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        BaseCmPropertySearchResponse ret = new BaseCmPropertySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BaseCmPropertyDto> dtoList = new ArrayList<BaseCmPropertyDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取IDList 关联的记录
            List<BaseCmProperty> data = this.baseCmPropertyService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (BaseCmProperty item : data) {
                    BaseCmPropertyDto dto = new BaseCmPropertyDto();
                    dto.setId(item.getId());
                    dto.setName(item.getName());
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in BaseCmPropertyRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseCmPropertyRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BaseCmPropertyDtoList data) {
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
            // DTO 到 BO的转换
            List<BaseCmPropertyDto> dtoList = data.getDtoList();
            List<BaseCmProperty> boList = new ArrayList<BaseCmProperty>();
            for (BaseCmPropertyDto dto : dtoList) {
                BaseCmProperty bo = new BaseCmProperty();
                if (dto.getName() == null) {
                    failNum++;
                    continue;
                }
                bo.setName(dto.getName());
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, for name is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.baseCmPropertyService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe name is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BaseCmPropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseCmPropertyRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BaseCmPropertyDtoList data) {
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
            // DTO 到 BO的转换
            List<BaseCmPropertyDto> dtoList = data.getDtoList();
            List<BaseCmProperty> boList = new ArrayList<BaseCmProperty>();
            for (BaseCmPropertyDto dto : dtoList) {
                BaseCmProperty bo = new BaseCmProperty();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getName() != null) {
                    bo.setName(dto.getName());
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
            Integer successNum = this.baseCmPropertyService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in BaseCmPropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }

        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "baseCmPropertyRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.baseCmPropertyService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in BaseCmPropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }

        return ret;
    }

}
