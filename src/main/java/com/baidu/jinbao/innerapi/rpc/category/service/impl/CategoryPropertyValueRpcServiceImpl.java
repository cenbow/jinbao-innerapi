package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CategoryPropertyValue;
import com.baidu.jinbao.innerapi.category.service.CategoryPropertyValueService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDtoList;
import com.baidu.jinbao.innerapi.rpc.category.service.CategoryPropertyValueRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CategoryPropertyValueRpcServiceImpl implements CategoryPropertyValueRpcService {

    private static final Logger LOG = Logger.getLogger(CategoryPropertyValueRpcServiceImpl.class);

    @Autowired
    private CategoryPropertyValueService categoryPropertyValueService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyValueRpcService", methodName = "getRecords")
    public CategoryPropertyValueSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CategoryPropertyValueSearchResponse ret = new CategoryPropertyValueSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CategoryPropertyValueDto> dtoList = new ArrayList<CategoryPropertyValueDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CategoryPropertyValue> data = this.categoryPropertyValueService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CategoryPropertyValue item : data) {
                    CategoryPropertyValueDto dto = new CategoryPropertyValueDto();
                    dto.setAlias(item.getAlias());
                    dto.setcId(item.getCId());
                    dto.setId(item.getId());
                    dto.setImageUrl(item.getImageUrl());
                    dto.setIsleaf(item.getIsleaf() & 0xff);
                    dto.setPid(item.getPid());
                    dto.setVid(item.getVid());
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dto.setActive(item.getActive() & 0xff);
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyValueRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyValueRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CategoryPropertyValueDtoList data) {
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
            List<CategoryPropertyValueDto> dtoList = data.getDtoList();
            List<CategoryPropertyValue> boList = new ArrayList<CategoryPropertyValue>();
            for (CategoryPropertyValueDto dto : dtoList) {
                CategoryPropertyValue bo = new CategoryPropertyValue();
                if (dto.getcId() != null && dto.getPid() != null && dto.getVid() != null) {
                    bo.setCId(dto.getcId());
                    bo.setPid(dto.getPid());
                    bo.setVid(dto.getVid());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getImageUrl() == null) {
                    bo.setImageUrl("");
                } else {
                    bo.setImageUrl(dto.getImageUrl());
                }
                if (dto.getAlias() == null) {
                    bo.setAlias("");
                } else {
                    bo.setAlias(dto.getAlias());
                }
                if (dto.getActive() == null) {
                    bo.setActive(Byte.valueOf("1"));
                } else {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                if (dto.getIsleaf() == null) {
                    bo.setIsleaf(Byte.valueOf("0"));
                } else {
                    bo.setIsleaf(Byte.valueOf(dto.getIsleaf().toString()));
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
                ret.setMessage("All records Failed, maybe active,alias,cid,imageUrl,isLeaf,pid,vid null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.categoryPropertyValueService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum
                        + " records failed, maybe active,alias,cid,imageUrl,isLeaf,pid,vid null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyValueRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyValueRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CategoryPropertyValueDtoList data) {
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
            List<CategoryPropertyValueDto> dtoList = data.getDtoList();
            List<CategoryPropertyValue> boList = new ArrayList<CategoryPropertyValue>();
            for (CategoryPropertyValueDto dto : dtoList) {
                CategoryPropertyValue bo = new CategoryPropertyValue();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getActive() != null) {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                bo.setAlias(dto.getAlias());
                bo.setCId(dto.getcId());
                bo.setImageUrl(dto.getImageUrl());
                if (dto.getIsleaf() != null) {
                    bo.setIsleaf(Byte.valueOf(dto.getIsleaf().toString()));
                }
                bo.setPid(dto.getPid());
                bo.setVid(dto.getVid());
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
            Integer successNum = this.categoryPropertyValueService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyValueRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyValueRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.categoryPropertyValueService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyValueRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
