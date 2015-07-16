package com.baidu.jinbao.innerapi.rpc.category.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.CategoryMap;
import com.baidu.jinbao.innerapi.category.service.CategoryMapService;
import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryCondition;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryMapDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryMapDtoList;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryMapSearchResponse;
import com.baidu.jinbao.innerapi.rpc.category.service.CategoryMapRpcService;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class CategoryMapRpcServiceImpl implements CategoryMapRpcService {

    private static final Logger LOG = Logger.getLogger(CategoryMapRpcServiceImpl.class);

    @Autowired
    private CategoryMapService categoryMapService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @ProtobufRPCService(serviceName = "categoryMapRpcService", methodName = "getRecords")
    public CategoryMapSearchResponse getRecords(CategoryCondition condition) {
        // 返回数据
        CategoryMapSearchResponse ret = new CategoryMapSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<CategoryMapDto> dtoList = new ArrayList<CategoryMapDto>();

        // 查询条件为空
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setDtoList(dtoList);
            return ret;
        }

        try {
            // 获取idlist的查询记录
            List<CategoryMap> data = this.categoryMapService.getRecords(condition.getIdList());
            if (!CollectionUtils.isEmpty(data)) {
                for (CategoryMap item : data) {
                    CategoryMapDto dto = new CategoryMapDto();
                    dto.setId(item.getId());
                    dto.setCmCid(item.getCmCid());
                    dto.setBaseCid(item.getBaseCid());
                    dto.setActive(item.getActive() & 0xff);
                    dto.setUpdatetime(sdf.format(item.getUpdatetime()));
                    dtoList.add(dto);
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryMapRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(CategoryMapDtoList data) {
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
            List<CategoryMapDto> dtoList = data.getDtoList();
            List<CategoryMap> boList = new ArrayList<CategoryMap>();
            for (CategoryMapDto dto : dtoList) {
                CategoryMap bo = new CategoryMap();
                if (dto.getBaseCid() != null && dto.getCmCid() != null) {
                    bo.setCmCid(dto.getCmCid());
                    bo.setBaseCid(dto.getBaseCid());
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
                ret.setMessage("All records Failed, for baseCid, cmCid, maybe null.");
                ret.setFailedNum(failNum);
                ret.setSuccessNum(0);
                return ret;
            }

            // 数据插入
            Integer successNum = this.categoryMapService.insertRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, baseCid, cmCid maybe null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:inertRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryMapRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(CategoryMapDtoList data) {
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
            List<CategoryMapDto> dtoList = data.getDtoList();
            List<CategoryMap> boList = new ArrayList<CategoryMap>();
            for (CategoryMapDto dto : dtoList) {
                CategoryMap bo = new CategoryMap();
                if (dto.getId() == null) {
                    failNum++;
                    continue;
                }
                bo.setId(dto.getId());
                bo.setCmCid(dto.getCmCid());
                bo.setBaseCid(dto.getBaseCid());
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
            Integer successNum = this.categoryMapService.updateRecords(boList);
            ret.setStatus(0);
            if (failNum == 0) {
                ret.setMessage("success");
            } else {
                ret.setMessage("Part success; " + failNum + " records failed, id is null!");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(failNum);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "categoryMapRpcService", methodName = "deleteRecords")
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
            Integer successNum = this.categoryMapService.deleteRecords(condition.getIdList());
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);

        } catch (Exception e) {
            LOG.error("Exception in CategoryMapRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
