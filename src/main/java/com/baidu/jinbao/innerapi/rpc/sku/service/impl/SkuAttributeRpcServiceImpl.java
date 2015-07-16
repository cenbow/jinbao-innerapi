package com.baidu.jinbao.innerapi.rpc.sku.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.innerapi.rpc.sku.common.ShardRouterService;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuCondition;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeSearchResponse;
import com.baidu.jinbao.innerapi.rpc.sku.service.SkuAttributeRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuAttributeTransfer;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.service.SkuAttributeService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class SkuAttributeRpcServiceImpl implements SkuAttributeRpcService {

    private static final Logger LOG = Logger.getLogger(SkuAttributeRpcServiceImpl.class);

    @Autowired
    private SkuAttributeService skuAttributeService;

    @Autowired
    private ShardRouterService shardRouterService;

    @Override
    @ProtobufRPCService(serviceName = "skuAttributeRpcService", methodName = "getRecords")
    public SkuAttributeSearchResponse getRecords(SkuCondition condition) {
        // 返回数据
        SkuAttributeSearchResponse ret = new SkuAttributeSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<SkuAttributeDto> dtoList = new ArrayList<SkuAttributeDto>();

        // 查询条件为空或者未提供相应的SkuId信息
        if ((condition == null)
                || CollectionUtils.isEmpty(condition.getSkuIdList())
                || (!CollectionUtils.isEmpty(condition.getIdList()) && (condition.getSkuIdList().size() != condition
                        .getIdList().size()))
                || (!CollectionUtils.isEmpty(condition.getSkuInnerIdList()) 
                        && (condition.getSkuIdList().size() != condition.getSkuInnerIdList().size()))) {
            ret.setStatus(1);
            ret.setMessage("Params input error. SkuIdList should not be empty."
                    + "and If IdList or skuInnerIdList is given, it's size shuld equal with size of skuIdList");
            ret.setDtoList(dtoList);
            return ret;
        }
        try {
            Map<String, SkuQueryCondition> splitInfoMap = new HashMap<String, SkuQueryCondition>();
            List<String> skuIdList = condition.getSkuIdList();
            List<Long> skuInnerIdList = condition.getSkuInnerIdList();
            List<Long> idList = condition.getIdList();
            boolean useSkuInnerId = !CollectionUtils.isEmpty(skuInnerIdList);
            boolean useId = !CollectionUtils.isEmpty(idList);
            for (int i = 0; i < condition.getSkuIdList().size(); ++i) {
                String splitDbInfo = shardRouterService.getSplitDbInfo(skuIdList.get(i));
                if (splitInfoMap.get(splitDbInfo) == null) {
                    SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
                    skuQueryCondition.setIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuInnerIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuIdList(new ArrayList<String>());
                    splitInfoMap.put(splitDbInfo, skuQueryCondition);
                }
                splitInfoMap.get(splitDbInfo).getSkuIdList().add(skuIdList.get(i));
                if (useSkuInnerId) {
                    splitInfoMap.get(splitDbInfo).getSkuInnerIdList().add(skuInnerIdList.get(i));
                }
                if (useId) {
                    splitInfoMap.get(splitDbInfo).getIdList().add(idList.get(i));
                }
            }
            for (Entry<String, SkuQueryCondition> entry : splitInfoMap.entrySet()) {
                List<SkuAttribute> data = this.skuAttributeService.getRecords(entry.getKey(), entry.getValue());
                if (!CollectionUtils.isEmpty(data)) {
                    for (SkuAttribute item : data) {
                        SkuAttributeDto dto = SkuAttributeTransfer.transBoToDto(item);
                        dtoList.add(dto);
                    }
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in SkuAttributeRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuAttributeRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(SkuAttributeDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data list should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            Map<String, List<SkuAttribute>> splitInfoMap = new HashMap<String, List<SkuAttribute>>();
            for (SkuAttributeDto dto : data.getDtoList()) {
                if ((dto == null) || !SkuAttributeTransfer.checkRequiredField(dto)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<SkuAttribute> splitDoList = new ArrayList<SkuAttribute>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                SkuAttribute bo = SkuAttributeTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<SkuAttribute>> entry : splitInfoMap.entrySet()) {
                successNum += this.skuAttributeService.insertRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId."
                        + "2.The skuid should be inserted into skuinfo table first. "
                        + "3.Skuid and merchantid fields are required. 4. Date format shoule be 'yyyy-MM-dd HH:mm:ss'"
                        + "5. The skuid exists already");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId."
                        + "2.The skuid should be inserted into skuinfo table first. "
                        + "3.Skuid and merchantid fields are required. 4. Date format shoule be 'yyyy-MM-dd HH:mm:ss'"
                        + "5. The skuid exists already");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuAttributeRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }
    
    @Override
    @ProtobufRPCService(serviceName = "skuAttributeRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(SkuAttributeDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data list should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            Map<String, List<SkuAttribute>> splitInfoMap = new HashMap<String, List<SkuAttribute>>();
            for (SkuAttributeDto dto : data.getDtoList()) {
                if ((dto.getSkuid() == null) || dto.getSkuid().isEmpty() || (dto.getId() == null)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<SkuAttribute> splitDoList = new ArrayList<SkuAttribute>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                SkuAttribute bo = SkuAttributeTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<SkuAttribute>> entry : splitInfoMap.entrySet()) {
                successNum += this.skuAttributeService.updateRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for "
                        + "the merchantId. 2.Skuid and Id fields are required. "
                        + "3. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId."
                        + "2.Skuid and Id fields are required. " + "3. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuAttributeRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuAttributeRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(SkuCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空或者未提供相应的SkuId信息
        if ((condition == null)
                || CollectionUtils.isEmpty(condition.getSkuIdList())
                || (!CollectionUtils.isEmpty(condition.getIdList()) && (condition.getSkuIdList().size() != condition
                        .getIdList().size()))
                || (!CollectionUtils.isEmpty(condition.getSkuInnerIdList()) 
                        && (condition.getSkuIdList().size() != condition.getSkuInnerIdList().size()))) {
            ret.setStatus(1);
            ret.setMessage("Params input error. SkuIdList should not be empty. "
                    + "and If IdList or skuInnerIdList is given, it's size shuld equal with size of skuIdList");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            Map<String, SkuQueryCondition> splitInfoMap = new HashMap<String, SkuQueryCondition>();
            List<String> skuIdList = condition.getSkuIdList();
            List<Long> skuInnerIdList = condition.getSkuInnerIdList();
            List<Long> idList = condition.getIdList();
            boolean useSkuInnerId = !CollectionUtils.isEmpty(skuInnerIdList);
            boolean useId = !CollectionUtils.isEmpty(idList);
            for (int i = 0; i < condition.getSkuIdList().size(); ++i) {
                String splitDbInfo = shardRouterService.getSplitDbInfo(skuIdList.get(i));
                if (splitInfoMap.get(splitDbInfo) == null) {
                    SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
                    skuQueryCondition.setIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuInnerIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuIdList(new ArrayList<String>());
                    splitInfoMap.put(splitDbInfo, skuQueryCondition);
                }
                splitInfoMap.get(splitDbInfo).getSkuIdList().add(skuIdList.get(i));
                if (useSkuInnerId) {
                    splitInfoMap.get(splitDbInfo).getSkuInnerIdList().add(skuInnerIdList.get(i));
                }
                if (useId) {
                    splitInfoMap.get(splitDbInfo).getIdList().add(idList.get(i));
                }
            }
            Integer successNum = 0;
            for (Entry<String, SkuQueryCondition> entry : splitInfoMap.entrySet()) {
                successNum += this.skuAttributeService.deleteRecords(entry.getKey(), entry.getValue());
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);
        } catch (Exception e) {
            LOG.error("Exception in SkuAttributeRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
