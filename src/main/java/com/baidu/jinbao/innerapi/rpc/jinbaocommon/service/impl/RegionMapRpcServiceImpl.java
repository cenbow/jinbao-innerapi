package com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.RegionMap;
import com.baidu.jinbao.innerapi.jinbaocommon.service.RegionMapService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.JinbaoCommonCondition;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.RegionMapDto;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.RegionMapDtoList;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto.RegionMapSearchResponse;
import com.baidu.jinbao.innerapi.rpc.jinbaocommon.service.RegionMapRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class RegionMapRpcServiceImpl implements RegionMapRpcService {
    private static final Logger LOG = Logger.getLogger(RegionMapRpcServiceImpl.class);

    @Autowired
    private RegionMapService regionMapService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "regionMapRpcService", methodName = "getRecords")
    public RegionMapSearchResponse getRecords(JinbaoCommonCondition condition) {
        // 返回数据
        RegionMapSearchResponse ret = new RegionMapSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<RegionMapDto> dtoList = new ArrayList<RegionMapDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<RegionMap> data = this.regionMapService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (RegionMap item : data) {
                    RegionMapDto dto = new RegionMapDto();
                    dto.setId(item.getId());
                    dto.setCityid(item.getCityid());
                    dto.setCityname(item.getCityname());
                    dto.setRegionid(item.getRegionid());
                    dto.setRegionname(item.getRegionname());
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in RegionMapRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "regionMapRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(RegionMapDtoList data) {
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
            List<RegionMapDto> dtoList = data.getDtoList();
            List<RegionMap> boList = new ArrayList<RegionMap>();
            for (RegionMapDto dto : dtoList) {
                RegionMap bo = new RegionMap();
                if (dto.getCityid() != null && dto.getRegionid() != null && dto.getCityname() != null
                        && dto.getRegionname() != null) {
                    bo.setCityid(dto.getCityid());
                    bo.setRegionid(dto.getRegionid());
                    bo.setCityname(dto.getCityname());
                    bo.setRegionname(dto.getRegionname());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getUpdatetime() != null) {
                    bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
                } else {
                    bo.setUpdatetime(new Date());
                    ;
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
                ret.setMessage("All records Failed, maybe cityId,cityName,regionId,regionName null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.regionMapService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum
                        + " records failed, maybe cityId,cityName,regionId,regionName null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in RegionMapRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "regionMapRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(RegionMapDtoList data) {
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
            List<RegionMapDto> dtoList = data.getDtoList();
            List<RegionMap> boList = new ArrayList<RegionMap>();
            for (RegionMapDto dto : dtoList) {
                RegionMap bo = new RegionMap();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setCityid(dto.getCityid());
                bo.setRegionid(dto.getRegionid());
                if (dto.getCityname() != null) {
                    bo.setCityname(dto.getCityname());
                }
                if (dto.getRegionname() != null) {
                    bo.setRegionname(dto.getRegionname());
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
            Integer successNum = this.regionMapService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in RegionMapRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "RegionMapRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.regionMapService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in RegionMapRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
