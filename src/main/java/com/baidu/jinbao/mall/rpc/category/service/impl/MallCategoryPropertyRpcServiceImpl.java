package com.baidu.jinbao.mall.rpc.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.category.dto.BasePropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.BaseValDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyDto;
import com.baidu.jinbao.innerapi.rpc.category.dto.CategoryPropertyValueDto;
import com.baidu.jinbao.mall.category.service.MallCategoryPropertyService;
import com.baidu.jinbao.mall.category.service.MallCategoryPropertyValueService;
import com.baidu.jinbao.mall.category.vo.PropertyIdCondition;
import com.baidu.jinbao.mall.category.vo.PropertyInfo;
import com.baidu.jinbao.mall.category.vo.PropertyValueInfo;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyId;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyIdList;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyInfoDto;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyInfoResponse;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyValueInfoDto;
import com.baidu.jinbao.mall.rpc.category.dto.PropertyValueInfoResponse;
import com.baidu.jinbao.mall.rpc.category.service.MallCategoryPropertyRpcService;
import com.baidu.jinbao.mall.rpc.category.utils.BasePropertyTransfer;
import com.baidu.jinbao.mall.rpc.category.utils.BaseValTransfer;
import com.baidu.jinbao.mall.rpc.category.utils.CategoryPropertyTransfer;
import com.baidu.jinbao.mall.rpc.category.utils.CategoryPropertyValueTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MallCategoryPropertyRpcServiceImpl implements MallCategoryPropertyRpcService {
    private static final Logger LOG = Logger.getLogger(MallCategoryPropertyRpcServiceImpl.class);
    @Autowired
    MallCategoryPropertyService mallCategoryPropertyService;

    @Autowired
    MallCategoryPropertyValueService mallCategoryPropertyValueService;

    @Override
    @ProtobufRPCService(serviceName = "mallCategoryPropertyRpcService", methodName = "getPropertyInfo")
    public PropertyInfoResponse getPropertyInfo(PropertyIdList propertyIdList) {

        PropertyInfoResponse ret = new PropertyInfoResponse();
        List<PropertyInfoDto> dtoList = new ArrayList<PropertyInfoDto>();

        List<PropertyId> propertyIds = propertyIdList.getPropertyIdList();
        // 输入参数有误
        if (propertyIdList != null && CollectionUtils.isEmpty(propertyIds)) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            return ret;
        }
        try {
            List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
            for (PropertyId propertyId : propertyIds) {
                if (propertyId.getCid() != null) {
                    PropertyIdCondition propertyIdCondition = new PropertyIdCondition();
                    propertyIdCondition.setCid(propertyId.getCid());
                    propertyIdConditionList.add(propertyIdCondition);
                }
            }
            if (CollectionUtils.isEmpty(propertyIdConditionList)) {
                ret.setStatus(1);
                ret.setMessage("getPropertyInfo Error: cid is required");
                return ret;
            }
            List<PropertyInfo> propertyInfoList =
                    this.mallCategoryPropertyService.getPropertyInfo(propertyIdConditionList);
            if (CollectionUtils.isNotEmpty(propertyInfoList)) {
                for (PropertyInfo propertyInfo : propertyInfoList) {
                    CategoryPropertyDto categoryPropertyDto =
                            CategoryPropertyTransfer.transBoToDto(propertyInfo.getCategoryProperty());
                    BasePropertyDto basePropertyDto = BasePropertyTransfer.transBoToDto(propertyInfo.getBaseProperty());
                    PropertyInfoDto dto = new PropertyInfoDto();
                    dto.setBasePropertyDto(basePropertyDto);
                    dto.setCategoryPropertyDto(categoryPropertyDto);
                    dtoList.add(dto);
                }
            }

            ret.setStatus(0);
            ret.setMessage("success");
            ret.setPropertyInfoList(dtoList);
            return ret;

        } catch (Exception e) {
            LOG.error("Exception in MallCategoryPropertyRpcServiceImpl:getPropertyInfo", e);

            ret.setStatus(1);
            ret.setMessage("getPropertyInfo Error: " + e.getMessage());
            return ret;
        }

    }

    @Override
    @ProtobufRPCService(serviceName = "mallCategoryPropertyRpcService", methodName = "getPropertyValueInfo")
    public PropertyValueInfoResponse getPropertyValueInfo(PropertyIdList propertyIdList) {
        PropertyValueInfoResponse ret = new PropertyValueInfoResponse();
        List<PropertyValueInfoDto> dtoList = new ArrayList<PropertyValueInfoDto>();

        List<PropertyId> propertyIds = propertyIdList.getPropertyIdList();
        // 输入参数有误
        if (propertyIdList != null && CollectionUtils.isEmpty(propertyIds)) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            return ret;
        }
        try {
            List<PropertyIdCondition> propertyIdConditionList = new ArrayList<PropertyIdCondition>();
            for (PropertyId propertyId : propertyIds) {
                if (propertyId.getCid() != null && propertyId.getPid() != null) {
                    PropertyIdCondition propertyIdCondition = new PropertyIdCondition();
                    propertyIdCondition.setCid(propertyId.getCid());
                    propertyIdCondition.setPid(propertyId.getPid());
                    propertyIdConditionList.add(propertyIdCondition);
                }
            }
            if (CollectionUtils.isEmpty(propertyIdConditionList)) {
                ret.setStatus(1);
                ret.setMessage("getPropertyValueInfo Error: cid and pid is required");
                return ret;
            }
            List<PropertyValueInfo> propertyValueInfoList =
                    this.mallCategoryPropertyValueService.getPropertyValueInfo(propertyIdConditionList);
            for (PropertyValueInfo propertyValueInfo : propertyValueInfoList) {
                CategoryPropertyValueDto categoryPropertyValueDto =
                        CategoryPropertyValueTransfer.transBoToDto(propertyValueInfo.getCategoryPropertyValue());
                BaseValDto baseValDto = BaseValTransfer.transBoToDto(propertyValueInfo.getBaseVal());
                PropertyValueInfoDto dto = new PropertyValueInfoDto();
                dto.setBaseValDto(baseValDto);
                dto.setCategoryPropertyValueDto(categoryPropertyValueDto);
                dtoList.add(dto);
            }

            ret.setStatus(0);
            ret.setPropertyValueInfoList(dtoList);
            ret.setMessage("success");
            return ret;

        } catch (Exception e) {
            LOG.error("Exception in MallCategoryPropertyRpcServiceImpl:getPropertyValueInfo", e);

            ret.setStatus(1);
            ret.setPropertyValueInfoList(dtoList);
            ret.setMessage("getPropertyValueInfo Error: " + e.getMessage());
            return ret;
        }

    }

}
