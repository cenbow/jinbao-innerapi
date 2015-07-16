package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.category.service.CategoryService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategorySearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.service.CategoryRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CategoryRpcServiceImpl implements CategoryRpcService {

    private static final Logger LOG = Logger.getLogger(CategoryRpcServiceImpl.class);

    @Autowired
    private CategoryService categoryService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "categoryRpcService", methodName = "getRecords")
    public CategorySearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CategorySearchResponse ret = new CategorySearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CategoryDto> dtoList = new ArrayList<CategoryDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getCategoryIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<Category> data = this.categoryService.getRecords(condition.getCategoryIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (Category item : data) {
                    CategoryDto dto = new CategoryDto();
                    dto.setCategoryid(item.getCategoryid());
                    dto.setParentid(item.getParentid());
                    dto.setIsleaf(item.getIsleaf() & 0xff);
                    dto.setName(item.getName());
                    dto.setPos(item.getPos());
                    dto.setDeleted(item.getDeleted() & 0xff);
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dto.setAddtime(sdf.format(item.getAddtime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CategoryRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CategoryDtoList data) {
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
            List<CategoryDto> dtoList = data.getDtoList();
            List<Category> boList = new ArrayList<Category>();
            for (CategoryDto dto : dtoList) {
                Category bo = new Category();
                if (dto.getName() != null) {
                    bo.setName(dto.getName());
                } else {
                    failNum++;
                    continue;
                }
                if (dto.getDeleted() == null) {
                    bo.setDeleted(Byte.valueOf("0"));
                } else {
                    bo.setDeleted(Byte.valueOf(dto.getDeleted().toString()));
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
                if (dto.getParentid() == null) {
                    bo.setParentid(0L);
                } else {
                    bo.setParentid(dto.getParentid());
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
                ret.setMessage("All records Failed, for name may be null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.categoryService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " name may be null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CategoryDtoList data) {
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
            List<CategoryDto> dtoList = data.getDtoList();
            List<Category> boList = new ArrayList<Category>();
            for (CategoryDto dto : dtoList) {
                Category bo = new Category();
                if (dto.getCategoryid() == null) {
                    failNum++;
                    continue;
                }
                bo.setCategoryid(dto.getCategoryid());
                bo.setParentid(dto.getParentid());
                if (dto.getIsleaf() != null) {
                    bo.setIsleaf(Byte.valueOf(dto.getIsleaf().toString()));
                }
                if (dto.getDeleted() != null) {
                    bo.setDeleted(Byte.valueOf(dto.getDeleted().toString()));
                }
                bo.setName(dto.getName());
                bo.setPos(dto.getPos());
                if (dto.getUpdatetime() != null) {
                    bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
                } else {
                    bo.setUpdatetime(new Date());
                }
                boList.add(bo);
            }

            if (failNum == dtoList.size()) {
                ret.setStatus(1);
                ret.setMessage("All records Failed, for Categoryid is null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据更新
            Integer successNum = this.categoryService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, Categoryid is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(CategoryCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getCategoryIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }

        try {
            // 删除IDList 关联的记录
            Integer successNum = this.categoryService.deleteRecords(condition.getCategoryIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
