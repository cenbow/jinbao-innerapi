package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.service.MerchantRouterService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantRouterDto;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantRouterDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.MerchantRouterSearchResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.MerchantRouterRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MerchantRouterRpcServiceImpl implements MerchantRouterRpcService {
    private static final Logger LOG = Logger.getLogger(MerchantRouterRpcServiceImpl.class);

    @Autowired
    private MerchantRouterService merchantRouterService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "merchantRouterRpcService", methodName = "getRecords")
    public MerchantRouterSearchResponse getRecords(JinbaoCommonCondition condition) {
        // 返回数据
        MerchantRouterSearchResponse ret = new MerchantRouterSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<MerchantRouterDto> dtoList = new ArrayList<MerchantRouterDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<MerchantRouter> data = this.merchantRouterService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (MerchantRouter item : data) {
                    MerchantRouterDto dto = new MerchantRouterDto();
                    dto.setId(item.getId());
                    dto.setMerchantAmountType(item.getMerchantAmountType() & 0xff);
                    dto.setMerchantId(item.getMerchantId());
                    dto.setMerchantRouterStat(item.getMerchantRouterStat() & 0xff);
                    dto.setUsedShards(item.getUsedShards());
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in MerchantRouterRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantRouterRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(MerchantRouterDtoList data) {
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
            List<MerchantRouterDto> dtoList = data.getDtoList();
            List<MerchantRouter> boList = new ArrayList<MerchantRouter>();
            for (MerchantRouterDto dto : dtoList) {
                MerchantRouter bo = new MerchantRouter();
                if (dto.getMerchantId() != null && dto.getMerchantAmountType() != null && dto.getUsedShards() != null) {
                    bo.setMerchantId(dto.getMerchantId());
                    bo.setUsedShards(dto.getUsedShards());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getMerchantAmountType() == null) {
                    bo.setMerchantAmountType(Byte.valueOf("0"));
                } else {
                    bo.setMerchantAmountType(Byte.valueOf(dto.getMerchantAmountType().toString()));
                }
                if (dto.getMerchantRouterStat() != null) {
                    bo.setMerchantRouterStat(Byte.valueOf(dto.getMerchantRouterStat().toString()));
                } else {
                    bo.setMerchantRouterStat(Byte.valueOf("0"));
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
                ret.setMessage("All records Failed, maybe usedShards,merchantAmountType,merchantId null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.merchantRouterService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum
                        + " records failed, maybe usedShards,merchantAmountType,merchantId null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in MerchantRouterRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantRouterRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(MerchantRouterDtoList data) {
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
            List<MerchantRouterDto> dtoList = data.getDtoList();
            List<MerchantRouter> boList = new ArrayList<MerchantRouter>();
            for (MerchantRouterDto dto : dtoList) {
                MerchantRouter bo = new MerchantRouter();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setMerchantId(dto.getMerchantId());
                if (dto.getMerchantAmountType() != null) {
                    bo.setMerchantAmountType(Byte.valueOf(dto.getMerchantAmountType().toString()));
                }
                bo.setUsedShards(dto.getUsedShards());
                if (dto.getMerchantRouterStat() != null) {
                    bo.setMerchantRouterStat(Byte.valueOf(dto.getMerchantRouterStat().toString()));
                }
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
            Integer successNum = this.merchantRouterService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in MerchantRouterRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "merchantRouterRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.merchantRouterService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in MerchantRouterRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
