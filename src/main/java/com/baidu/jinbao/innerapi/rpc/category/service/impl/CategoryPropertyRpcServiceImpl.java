package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CategoryProperty;
import com.baidu.jinbao.innerapi.category.service.CategoryPropertyService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDtoList;
import com.baidu.jinbao.innerapi.rpc.category.service.CategoryPropertyRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CategoryPropertyRpcServiceImpl implements CategoryPropertyRpcService {

    private static final Logger LOG = Logger.getLogger(CategoryPropertyRpcServiceImpl.class);

    @Autowired
    private CategoryPropertyService categoryPropertyService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyRpcService", methodName = "getRecords")
    public CategoryPropertySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CategoryPropertySearchResponse ret = new CategoryPropertySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CategoryPropertyDto> dtoList = new ArrayList<CategoryPropertyDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CategoryProperty> data = this.categoryPropertyService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CategoryProperty item : data) {
                    CategoryPropertyDto dto = new CategoryPropertyDto();
                    dto.setId(item.getId());
                    dto.setActive(item.getActive() & 0xff);
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dto.setcId(item.getCId());
                    dto.setIsleaf(item.getIsleaf() & 0xff);
                    dto.setIsRequired(item.getIsRequired() & 0xff);
                    dto.setIsSelfdefine(item.getIsSelfdefine() & 0xff);
                    dto.setName(item.getName());
                    dto.setPid(item.getPid());
                    dto.setPropType(item.getPropType() & 0xff);
                    dto.setSequence(item.getSequence());
                    dto.setType(item.getType() & 0xff);
                    dto.setValueRange(item.getValueRange());
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CategoryPropertyDtoList data) {
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
            List<CategoryPropertyDto> dtoList = data.getDtoList();
            List<CategoryProperty> boList = new ArrayList<CategoryProperty>();
            for (CategoryPropertyDto dto : dtoList) {
                CategoryProperty bo = new CategoryProperty();
                if (dto.getcId() != null && dto.getName() != null
                        && dto.getPid() != null && dto.getPropType() != null
                        && dto.getValueRange() != null) {
                    bo.setCId(dto.getcId());
                    bo.setName(dto.getName());
                    bo.setPid(dto.getPid());
                    bo.setPropType(Byte.valueOf(dto.getPropType().toString()));
                    bo.setValueRange(dto.getValueRange());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getIsSelfdefine() == null) {
                    bo.setIsSelfdefine(Byte.valueOf("0"));
                } else {
                    bo.setIsSelfdefine(Byte.valueOf(dto.getIsSelfdefine().toString()));
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
                if (dto.getIsRequired() == null) {
                    bo.setIsRequired(Byte.valueOf("0"));
                } else {
                    bo.setIsRequired(Byte.valueOf(dto.getIsRequired().toString()));
                }
                if (dto.getType() == null) {
                    bo.setType(Byte.valueOf("2"));
                } else {
                    bo.setType(Byte.valueOf(dto.getType().toString()));
                }
                if (dto.getSequence() == null) {
                    bo.setSequence(9999);
                } else {
                    bo.setSequence(dto.getSequence());
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
                ret.setMessage("All records Failed, maybe cId,valueRange... null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.categoryPropertyService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe cId,valueRange, ... null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CategoryPropertyDtoList data) {
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
            List<CategoryPropertyDto> dtoList = data.getDtoList();
            List<CategoryProperty> boList = new ArrayList<CategoryProperty>();
            for (CategoryPropertyDto dto : dtoList) {
                CategoryProperty bo = new CategoryProperty();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getcId() != null) {
                    bo.setCId(dto.getcId());
                }
                if (dto.getIsleaf() != null) {
                    bo.setIsleaf(Byte.valueOf(dto.getIsleaf().toString()));
                }
                if (dto.getIsRequired() != null) {
                    bo.setIsRequired(Byte.valueOf(dto.getIsRequired().toString()));
                }
                if (dto.getIsSelfdefine() != null) {
                    bo.setIsSelfdefine(Byte.valueOf(dto.getIsSelfdefine().toString()));
                }
                bo.setName(dto.getName());
                bo.setPid(dto.getPid());
                if (dto.getPropType() != null) {
                    bo.setPropType(Byte.valueOf(dto.getPropType().toString()));
                }
                bo.setSequence(dto.getSequence());
                if (dto.getType() != null) {
                    bo.setType(Byte.valueOf(dto.getType().toString()));
                }
                bo.setValueRange(dto.getValueRange());
                if (dto.getActive() != null) {
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
                ret.setMessage("All records Failed, for id is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据更新
            Integer successNum = this.categoryPropertyService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryPropertyRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.categoryPropertyService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryPropertyRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
