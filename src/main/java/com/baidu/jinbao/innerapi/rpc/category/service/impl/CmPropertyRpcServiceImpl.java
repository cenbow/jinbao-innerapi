package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CmProperty;
import com.baidu.jinbao.innerapi.category.service.CmPropertyService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmPropertySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.service.CmPropertyRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CmPropertyRpcServiceImpl implements CmPropertyRpcService {
    private static final Logger LOG = Logger.getLogger(CmPropertyRpcServiceImpl.class);

    @Autowired
    private CmPropertyService cmPropertyService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyRpcService", methodName = "getRecords")
    public CmPropertySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CmPropertySearchResponse ret = new CmPropertySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CmPropertyDto> dtoList = new ArrayList<CmPropertyDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CmProperty> data = this.cmPropertyService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CmProperty item : data) {
                    CmPropertyDto dto = new CmPropertyDto();
                    dto.setId(item.getId());
                    dto.setCmCid(item.getCmCid() & 0xff);
                    dto.setActive(item.getActive() & 0xff);
                    dto.setBasePid(item.getBasePid());
                    dto.setDicPid(item.getDicPid());
                    dto.setPos(item.getPos());
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CmPropertyRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CmPropertyDtoList data) {
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
            List<CmPropertyDto> dtoList = data.getDtoList();
            List<CmProperty> boList = new ArrayList<CmProperty>();
            for (CmPropertyDto dto : dtoList) {
                CmProperty bo = new CmProperty();
                if (dto.getCmCid() != null && dto.getBasePid() != null && dto.getDicPid() != null) {
                    bo.setCmCid(dto.getCmCid());
                    bo.setBasePid(dto.getBasePid());
                    bo.setDicPid(dto.getDicPid());
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
                ret.setMessage("All records Failed, maybe cmCid,basePid,dicPid null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.cmPropertyService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe cmCid,basePid,dicPid null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CmPropertyDtoList data) {
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
            List<CmPropertyDto> dtoList = data.getDtoList();
            List<CmProperty> boList = new ArrayList<CmProperty>();
            for (CmPropertyDto dto : dtoList) {
                CmProperty bo = new CmProperty();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setCmCid(dto.getCmCid());
                if (dto.getActive() != null) {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                bo.setBasePid(dto.getBasePid());
                bo.setDicPid(dto.getDicPid());
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
            Integer successNum = this.cmPropertyService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmPropertyRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.cmPropertyService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CmPropertyRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
