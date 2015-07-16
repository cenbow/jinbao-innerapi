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
import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageSearchResponse;
import com.baidu.jinbao.innerapi.rpc.sku.service.BcsSkuImageRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.sku.BcsSkuImageTransfer;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.service.BcsSkuImageService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class BcsSkuImageRpcServiceImpl implements BcsSkuImageRpcService {

    private static final Logger LOG = Logger.getLogger(BcsSkuImageRpcServiceImpl.class);

    @Autowired
    private BcsSkuImageService bcsSkuImageService;

    @Autowired
    private ShardRouterService shardRouterService;

    @Override
    @ProtobufRPCService(serviceName = "bcsSkuImageRpcService", methodName = "getRecords")
    public BcsSkuImageSearchResponse getRecords(SkuCondition condition) {
        // 返回数据
        BcsSkuImageSearchResponse ret = new BcsSkuImageSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<BcsSkuImageDto> dtoList = new ArrayList<BcsSkuImageDto>();

        // 查询条件为空或者未提供相应的SkuId信息
        if ((condition == null)
                || CollectionUtils.isEmpty(condition.getSkuIdList())
                || (!CollectionUtils.isEmpty(condition.getIdList()) 
                        && (condition.getSkuIdList().size() != condition.getIdList().size()))
                || (!CollectionUtils.isEmpty(condition.getSkuInnerIdList()) 
                        && (condition.getSkuIdList().size() != condition.getSkuInnerIdList().size()))) {
            ret.setStatus(1);
            ret.setMessage("Params input error. SkuIdList should not be empty. "
                    + "And if IdList or skuInnerIdList is given, it's size shuld equal with size of skuIdList");
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
                List<BcsSkuImage> data = this.bcsSkuImageService.getRecords(entry.getKey(), entry.getValue());
                if (!CollectionUtils.isEmpty(data)) {
                    for (BcsSkuImage item : data) {
                        BcsSkuImageDto dto = BcsSkuImageTransfer.transBoToDto(item);
                        dtoList.add(dto);
                    }
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in BcsSkuImageRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsSkuImageRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(BcsSkuImageDtoList data) {
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
            Map<String, List<BcsSkuImage>> splitInfoMap = new HashMap<String, List<BcsSkuImage>>();
            for (BcsSkuImageDto dto : data.getDtoList()) {
                if ((dto == null) || !BcsSkuImageTransfer.checkRequiredField(dto)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<BcsSkuImage> splitDoList = new ArrayList<BcsSkuImage>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                BcsSkuImage bo = BcsSkuImageTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<BcsSkuImage>> entry : splitInfoMap.entrySet()) {
                successNum += this.bcsSkuImageService.insertRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by: 1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2.The skuid should be inserted into skuinfo table first. "
                        + "3.Skuid, gipsImage and type fields are required. "
                        + "4. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May caused by: 1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId."
                        + "2.The skuid should be inserted into skuinfo table first. "
                        + "3.Skuid, gipsImage and type fields are required. "
                        + "4. Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in BcsSkuImageRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }
    
    @Override
    @ProtobufRPCService(serviceName = "bcsSkuImageRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(BcsSkuImageDtoList data) {
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
            Map<String, List<BcsSkuImage>> splitInfoMap = new HashMap<String, List<BcsSkuImage>>();
            for (BcsSkuImageDto dto : data.getDtoList()) {
                if ((dto.getSkuid() == null) || dto.getSkuid().isEmpty() || (dto.getId() == null)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<BcsSkuImage> splitDoList = new ArrayList<BcsSkuImage>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                BcsSkuImage bo = BcsSkuImageTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<BcsSkuImage>> entry : splitInfoMap.entrySet()) {
                successNum += this.bcsSkuImageService.updateRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2.Skuid and Id fields are required. " + "3.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId."
                        + "2.Skuid and Id fields are required. " + "3. Date format is 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in BcsSkuImageRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("updateRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsSkuImageRpcService", methodName = "deleteRecords")
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
            ret.setMessage("Params input error. SkuIdList should not be empty."
                    + "And if IdList or skuInnerIdList is given, it's size shuld equal with size of skuIdList");
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
                successNum += this.bcsSkuImageService.deleteRecords(entry.getKey(), entry.getValue());
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);
        } catch (Exception e) {
            LOG.error("Exception in BcsSkuImageRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getIdList().size());
        }
        return ret;
    }
}
