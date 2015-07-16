package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantInfoDto;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantInfoSearchResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.MerchantInfoRpcService;
import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantInfo;
import com.baidu.jinbao.innerapi.jinbaocommon.service.MerchantInfoService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MerchantInfoRpcServiceImpl implements MerchantInfoRpcService {
    private static final Logger LOG = Logger.getLogger(MerchantInfoRpcServiceImpl.class);

    @Autowired
    private MerchantInfoService merchantInfoService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "merchantInfoRpcService", methodName = "getRecords")
    public MerchantInfoSearchResponse getRecords(JinbaoCommonCondition condition) {
        // 返回数据
        MerchantInfoSearchResponse ret = new MerchantInfoSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<MerchantInfoDto> dtoList = new ArrayList<MerchantInfoDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<MerchantInfo> data = this.merchantInfoService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (MerchantInfo item : data) {
                    MerchantInfoDto dto = new MerchantInfoDto();
                    dto.setId(item.getId());
                    dto.setName(item.getName());
                    dto.setUcid(item.getUcid());
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in MerchantInfoRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantInfoRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(MerchantInfoDtoList data) {
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
            List<MerchantInfoDto> dtoList = data.getDtoList();
            List<MerchantInfo> boList = new ArrayList<MerchantInfo>();
            for (MerchantInfoDto dto : dtoList) {
                MerchantInfo bo = new MerchantInfo();
                if (dto.getName() == null || dto.getName().isEmpty()) {
                    failNum ++;
                    continue;
                } 
                bo.setName(dto.getName());
                if (dto.getUcid() != null) {
                    bo.setUcid(dto.getUcid());
                } else {
                    bo.setUcid(0L);
                }
                if (dto.getUpdatetime() != null) {
                    bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
                } else {
                    bo.setUpdatetime(new Date());
                }
                if (dto.getAddtime() != null) {
                    bo.setAddtime(sdf.parse(dto.getAddtime()));
                } else {
                    bo.setAddtime(new Date());
                }
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
            Integer successNum = this.merchantInfoService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe name is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in MerchantInfoRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantInfoRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(MerchantInfoDtoList data) {
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
            List<MerchantInfoDto> dtoList = data.getDtoList();
            List<MerchantInfo> boList = new ArrayList<MerchantInfo>();
            for (MerchantInfoDto dto : dtoList) {
                MerchantInfo bo = new MerchantInfo();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setName(dto.getName());
                if (dto.getUcid() != null) {
                    bo.setUcid(dto.getUcid());
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
                ret.setMessage("All records Failed, for id is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据更新
            Integer successNum = this.merchantInfoService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in MerchantInfoRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantInfoRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(JinbaoCommonCondition condition) {
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
            Integer successNum = this.merchantInfoService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in MerchantInfoRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }

}
