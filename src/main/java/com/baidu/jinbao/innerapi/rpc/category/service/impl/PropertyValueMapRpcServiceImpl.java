package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.PropertyValueMap;
import com.baidu.jinbao.innerapi.category.service.PropertyValueMapService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.PropertyValueMapDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.PropertyValueMapDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.PropertyValueMapSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.service.PropertyValueMapRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class PropertyValueMapRpcServiceImpl implements PropertyValueMapRpcService {
    private static final Logger LOG = Logger.getLogger(PropertyValueMapRpcServiceImpl.class);

    @Autowired
    private PropertyValueMapService propertyValueMapService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "propertyValueMapRpcService", methodName = "getRecords")
    public PropertyValueMapSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        PropertyValueMapSearchResponse ret = new PropertyValueMapSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<PropertyValueMapDto> dtoList = new ArrayList<PropertyValueMapDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<PropertyValueMap> data = this.propertyValueMapService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (PropertyValueMap item : data) {
                    PropertyValueMapDto dto = new PropertyValueMapDto();
                    dto.setId(item.getId());
                    dto.setActive(item.getActive() & 0xff);
                    dto.setBaseVid(item.getBaseVid());
                    dto.setCmVid(item.getCmVid());
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in PropertyValueMapRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "propertyValueMapRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(PropertyValueMapDtoList data) {
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
            List<PropertyValueMapDto> dtoList = data.getDtoList();
            List<PropertyValueMap> boList = new ArrayList<PropertyValueMap>();
            for (PropertyValueMapDto dto : dtoList) {
                PropertyValueMap bo = new PropertyValueMap();
                if (dto.getBaseVid() != null && dto.getCmVid() != null) {
                    bo.setBaseVid(dto.getBaseVid());
                    bo.setCmVid(dto.getCmVid());
                } else {
                    failNum++;
                    continue;
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
                ret.setMessage("All records Failed, records failed, maybe baseVid,cmVid null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.propertyValueMapService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe baseVid,cmVid null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in PropertyValueMapRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "propertyValueMapRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(PropertyValueMapDtoList data) {
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
            List<PropertyValueMapDto> dtoList = data.getDtoList();
            List<PropertyValueMap> boList = new ArrayList<PropertyValueMap>();
            for (PropertyValueMapDto dto : dtoList) {
                PropertyValueMap bo = new PropertyValueMap();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getActive() != null) {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                bo.setBaseVid(dto.getBaseVid());
                bo.setCmVid(dto.getCmVid());
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
            Integer successNum = this.propertyValueMapService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in PropertyValueMapRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "propertyValueMapRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.propertyValueMapService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in PropertyValueMapRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
