package com.baidu.jinbao.mall.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.category.bo.Category;
import com.baidu.jinbao.innerapi.rpc.base.EmptyRequest;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryDto;
import com.baidu.jinbao.mall.category.service.MallCategoryService;
import com.baidu.jinbao.mall.rpc.category.dto.CategoryDtoListResponse;
import com.baidu.jinbao.mall.rpc.category.dto.CategoryId;
import com.baidu.jinbao.mall.rpc.category.service.MallCategoryRpcService;
import com.baidu.jinbao.mall.rpc.category.utils.CategoryTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MallCategoryRpcServiceImpl implements MallCategoryRpcService {
    private static final Logger LOG = Logger.getLogger(MallCategoryRpcServiceImpl.class);
    @Autowired
    MallCategoryService mallCategoryService;

    @Override
    @ProtobufRPCService(serviceName = "mallCategoryRpcService", methodName = "getAllCategoryInfo")
    public CategoryDtoListResponse getAllCategoryInfo(EmptyRequest emptyRequest) {
        CategoryDtoListResponse ret = new CategoryDtoListResponse();
        List<CategoryDto> dtoList = new ArrayList<CategoryDto>();

        try {
            // 获取表所有数据
            List<Category> categoryList = this.mallCategoryService.getAllRecords();
            if (CollectionUtils.isNotEmpty(categoryList)) {
                for (Category category : categoryList) {
                    CategoryDto dto = CategoryTransfer.transBoToDto(category);
                    dtoList.add(dto);
                }
            }

            ret.setStatus(0);
            ret.setCategoryDtoList(dtoList);
            ret.setMessage("success");

            return ret;

        } catch (Exception e) {
            LOG.error("Exception in MallCategoryRpcServiceImpl:getAllCategoryInfo", e);
            ret.setStatus(1);
            ret.setCategoryDtoList(dtoList);
            ret.setMessage("getAllCategoryInfo Error: " + e.getMessage());

            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallCategoryRpcService", methodName = "getCategoryInfo")
    public CategoryDtoListResponse getCategoryInfo(CategoryId categoryId) {
        CategoryDtoListResponse ret = new CategoryDtoListResponse();
        List<CategoryDto> dtoList = new ArrayList<CategoryDto>();

        // 输入参数有误
        if (categoryId == null || categoryId.getParentId() == null) {
            ret.setStatus(1);
            ret.setCategoryDtoList(dtoList);
            ret.setMessage("Params input error.");
            return ret;
        }

        try {
            // 查询父类下的所有下一级子类目信息
            Long parentid = categoryId.getParentId();
            List<Category> categoryList = this.mallCategoryService.getRecordByParentid(parentid);
            if (CollectionUtils.isNotEmpty(categoryList)) {
                for (Category category : categoryList) {
                    CategoryDto dto = CategoryTransfer.transBoToDto(category);
                    dtoList.add(dto);
                }
            }

            ret.setStatus(0);
            ret.setCategoryDtoList(dtoList);
            ret.setMessage("success");

            return ret;

        } catch (Exception e) {
            LOG.error("Exception in MallCategoryRpcServiceImpl:getAllCategoryInfo", e);
            ret.setStatus(1);
            ret.setCategoryDtoList(dtoList);
            ret.setMessage("getAllCategoryInfo Error: " + e.getMessage());

            return ret;
        }

    }
}
