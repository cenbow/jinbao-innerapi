package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CmCategory;
import com.baidu.jinbao.innerapi.category.service.CmCategoryService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmCategoryDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmCategorySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CmCategoryDtoList;
import com.baidu.jinbao.innerapi.rpc.category.service.CmCategoryRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CmCategoryRpcServiceImpl implements CmCategoryRpcService {

    private static final Logger LOG = Logger.getLogger(CmCategoryRpcServiceImpl.class);

    @Autowired
    private CmCategoryService cmCategoryService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "cmCategoryRpcService", methodName = "getRecords")
    public CmCategorySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CmCategorySearchResponse ret = new CmCategorySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CmCategoryDto> dtoList = new ArrayList<CmCategoryDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CmCategory> data = this.cmCategoryService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CmCategory item : data) {
                    CmCategoryDto dto = new CmCategoryDto();
                    dto.setId(item.getId());
                    dto.setActive(item.getActive() & 0xff);
                    dto.setIsleaf(item.getIsleaf() & 0xff);
                    dto.setName(item.getName());
                    dto.setParentid(item.getParentid());
                    dto.setPos(item.getPos());
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CmCategoryRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmCategoryRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CmCategoryDtoList data) {
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
            List<CmCategoryDto> dtoList = data.getDtoList();
            List<CmCategory> boList = new ArrayList<CmCategory>();
            for (CmCategoryDto dto : dtoList) {
                CmCategory bo = new CmCategory();
                if (dto.getName() != null) {
                    bo.setName(dto.getName());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getParentid() == null) {
                    bo.setParentid(-1);
                } else {
                    bo.setParentid(dto.getParentid());
                }
                if (dto.getActive() == null) {
                    bo.setActive(Byte.valueOf("1"));
                } else {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                if (dto.getPos() == null) {
                    bo.setPos(9999);
                } else {
                    bo.setPos(dto.getPos());
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
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, maybe name null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.cmCategoryService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, maybe name null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmCategoryRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmCategoryRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CmCategoryDtoList data) {
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
            List<CmCategoryDto> dtoList = data.getDtoList();
            List<CmCategory> boList = new ArrayList<CmCategory>();
            for (CmCategoryDto dto : dtoList) {
                CmCategory bo = new CmCategory();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                if (dto.getActive() != null) {
                    bo.setActive(Byte.valueOf(dto.getActive().toString()));
                }
                if (dto.getIsleaf() != null) {
                    bo.setIsleaf(Byte.valueOf(dto.getIsleaf().toString()));
                }
                bo.setName(dto.getName());
                bo.setParentid(dto.getParentid());
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
            Integer successNum = this.cmCategoryService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CmCategoryRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "cmCategoryRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.cmCategoryService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CmCategoryRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
