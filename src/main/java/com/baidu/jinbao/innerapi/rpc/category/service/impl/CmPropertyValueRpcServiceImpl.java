package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CmPropertyValue;
import com.baidu.jinbao.innerapi.category.service.CmPropertyValueService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyValueDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyValueSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.service.CmPropertyValueRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CmPropertyValueRpcServiceImpl implements CmPropertyValueRpcService {

    private static final Logger LOG = Logger.getLogger(CmPropertyValueRpcServiceImpl.class);

    @Autowired
    private CmPropertyValueService cmPropertyValueService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyValueRpcService", methodName = "getRecords")
    public CmPropertyValueSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CmPropertyValueSearchResponse ret = new CmPropertyValueSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CmPropertyValueDto> dtoList = new ArrayList<CmPropertyValueDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CmPropertyValue> data = this.cmPropertyValueService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CmPropertyValue item : data) {
                    CmPropertyValueDto dto = new CmPropertyValueDto();
                    dto.setId(item.getId());
                    dto.setActive(item.getActive() & 0xff);
                    dto.setCid(item.getCid());
                    dto.setCmPid(item.getCmPid());
                    dto.setDicVid(item.getDicVid());
                    dto.setPos(item.getPos());
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CmPropertyValueRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyValueRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CmPropertyValueDtoList data) {
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
            List<CmPropertyValueDto> dtoList = data.getDtoList();
            List<CmPropertyValue> boList = new ArrayList<CmPropertyValue>();
            for (CmPropertyValueDto dto : dtoList) {
                CmPropertyValue bo = new CmPropertyValue();
                if (dto.getCid() != null && dto.getCmPid() != null && dto.getDicVid() != null) {
                    bo.setCid(dto.getCid());
                    bo.setCmPid(dto.getCmPid());
                    bo.setDicVid(dto.getDicVid());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getPos() == null) {
                    bo.setPos(9999);
                } else {
                    bo.setPos(dto.getPos());
                }
                if (dto.getActive() == null) {
                    bo.setActive(Byte.valueOf("1"));
                } else {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                if (dto.getUpdatetime() != null) {
                    bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
                } else {
                    bo.setUpdatetime(new Date());
                }
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, maybe cid,cmPid,dicVid null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.cmPropertyValueService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe cid,cmPid,dicVid null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyValueRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyValueRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CmPropertyValueDtoList data) {
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
            List<CmPropertyValueDto> dtoList = data.getDtoList();
            List<CmPropertyValue> boList = new ArrayList<CmPropertyValue>();
            for (CmPropertyValueDto dto : dtoList) {
                CmPropertyValue bo = new CmPropertyValue();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getActive() != null) {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                bo.setCid(dto.getCid());
                bo.setCmPid(dto.getCmPid());
                bo.setDicVid(dto.getDicVid());
                bo.setPos(dto.getPos());
                if (dto.getUpdatetime() != null) {
                    bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
                } else {
                    bo.setUpdatetime(new Date());
                    ;
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
            Integer successNum = this.cmPropertyValueService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyValueRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyValueRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.cmPropertyValueService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyValueRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
