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
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuTotalInfoDto;
import com.baidu.jinbao.innerapi.rpc.sku.common.SkuTotalInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCdtDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCommentDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCspuDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuDescriptionDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoDtoList;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoSearchResponse;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuLevelDto;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuPpsDto;
import com.baidu.jinbao.innerapi.rpc.sku.service.SkuInfoRpcService;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuAttributeTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuCdtTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuCommentTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuCspuTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuDescriptionTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuInfoTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuLevelTransfer;
import com.baidu.jinbao.innerapi.rpc.utils.sku.SkuPpsTransfer;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jinbao.innerapi.sku.vo.SkuTotalInfoVo;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class SkuInfoRpcServiceImpl implements SkuInfoRpcService {

    private static final Logger LOG = Logger.getLogger(SkuInfoRpcServiceImpl.class);

    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private ShardRouterService shardRouterService;

    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "getRecords")
    public SkuInfoSearchResponse getRecords(SkuCondition condition) {
        // 返回数据
        SkuInfoSearchResponse ret = new SkuInfoSearchResponse();
        ret.setStatus(0);
        ret.setMessage("success");
        List<SkuInfoDto> dtoList = new ArrayList<SkuInfoDto>();

        // 查询条件为空或者未提供相应的SkuId信息
        if ((condition == null)
                || CollectionUtils.isEmpty(condition.getSkuIdList())
                || (!CollectionUtils.isEmpty(condition.getSkuInnerIdList()) 
                        && (condition.getSkuIdList().size() != condition.getSkuInnerIdList().size()))) {
            ret.setStatus(1);
            ret.setMessage("Params input error. SkuIdList should not be empty. "
                    + "and If IdList or skuInnerIdList is given, " + "it's size shuld equal with size of skuIdList");
            ret.setDtoList(dtoList);
            return ret;
        }
        try {
            Map<String, SkuQueryCondition> splitInfoMap = new HashMap<String, SkuQueryCondition>();
            List<String> skuIdList = condition.getSkuIdList();
            List<Long> skuInnerIdList = condition.getSkuInnerIdList();
            boolean useSkuInnerId = !CollectionUtils.isEmpty(skuInnerIdList);
            for (int i = 0; i < condition.getSkuIdList().size(); ++i) {
                String splitDbInfo = shardRouterService.getSplitDbInfo(skuIdList.get(i));
                if (splitInfoMap.get(splitDbInfo) == null) {
                    SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
                    skuQueryCondition.setSkuInnerIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuIdList(new ArrayList<String>());
                    splitInfoMap.put(splitDbInfo, skuQueryCondition);
                }
                splitInfoMap.get(splitDbInfo).getSkuIdList().add(skuIdList.get(i));
                if (useSkuInnerId) {
                    splitInfoMap.get(splitDbInfo).getSkuInnerIdList().add(skuInnerIdList.get(i));
                }
            }
            for (Entry<String, SkuQueryCondition> entry : splitInfoMap.entrySet()) {
                List<SkuInfo> data = this.skuInfoService.getRecords(entry.getKey(), entry.getValue());
                if (!CollectionUtils.isEmpty(data)) {
                    for (SkuInfo item : data) {
                        SkuInfoDto dto = SkuInfoTransfer.transBoToDto(item);
                        dtoList.add(dto);
                    }
                }
            }
            ret.setDtoList(dtoList);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:getRecords", e);
            ret.setStatus(1);
            ret.setMessage("GetRecords Error: " + e.getMessage());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "insertRecords")
    public ModifyResponse insertRecords(SkuInfoDtoList data) {
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
            Map<String, List<SkuInfo>> splitInfoMap = new HashMap<String, List<SkuInfo>>();
            for (SkuInfoDto dto : data.getDtoList()) {
                if ((dto == null) || !SkuInfoTransfer.checkRequiredField(dto)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<SkuInfo> splitDoList = new ArrayList<SkuInfo>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                SkuInfo bo = SkuInfoTransfer.transDtoToBo(dto, true);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<SkuInfo>> entry : splitInfoMap.entrySet()) {
                successNum += this.skuInfoService.insertRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Skuid, merchantid, feedid, title, outerid, starttime, endtime, signature, "
                        + "isselfopen, dataversion and inactivetime fields are required. "
                        + "3.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Skuid, merchantid, feedid, title, outerid, starttime, endtime, signature, "
                        + "isselfopen, dataversion and inactivetime fields are required. "
                        + "3.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:insertRecords", e);
            ret.setStatus(1);
            ret.setMessage("insertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    
    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "updateRecords")
    public ModifyResponse updateRecords(SkuInfoDtoList data) {
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
            Map<String, List<SkuInfo>> splitInfoMap = new HashMap<String, List<SkuInfo>>();
            for (SkuInfoDto dto : data.getDtoList()) {
                if ((dto.getSkuid() == null) || dto.getSkuid().isEmpty() || (dto.getSkuInnerid() == null)) {
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(dto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<SkuInfo> splitDoList = new ArrayList<SkuInfo>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                SkuInfo bo = SkuInfoTransfer.transDtoToBo(dto, false);
                if (bo != null) {
                    splitInfoMap.get(splitDbInfo).add(bo);
                }
            }
            Integer successNum = 0;
            for (Entry<String, List<SkuInfo>> entry : splitInfoMap.entrySet()) {
                successNum += this.skuInfoService.updateRecords(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2.Skuid and skuInnerId fields are required. 3.Date format shold be 'yyyy-MM-dd HH:mm:ss'");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Part Failed. May Caused by: 1.SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2.Skuid and skuInnerId fields are required. 3.Date format should be 'yyyy-MM-dd HH:mm:ss'");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:updateRecords", e);
            ret.setStatus(1);
            ret.setMessage("inertRecords Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "deleteRecords")
    public ModifyResponse deleteRecords(SkuCondition condition) {
        // 返回数据
        ModifyResponse ret = new ModifyResponse();

        // 查询条件为空或者未提供相应的SkuId信息
        if ((condition == null)
                || CollectionUtils.isEmpty(condition.getSkuIdList())
                || (!CollectionUtils.isEmpty(condition.getSkuInnerIdList()) 
                        && (condition.getSkuIdList().size() != condition.getSkuInnerIdList().size()))) {
            ret.setStatus(1);
            ret.setMessage("Params input error. SkuIdList should not be empty. "
                    + "and If IdList or skuInnerIdList is given, " + "it's size shuld equal with size of skuIdList");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            Map<String, SkuQueryCondition> splitInfoMap = new HashMap<String, SkuQueryCondition>();
            List<String> skuIdList = condition.getSkuIdList();
            List<Long> skuInnerIdList = condition.getSkuInnerIdList();
            boolean useSkuInnerId = !CollectionUtils.isEmpty(skuInnerIdList);
            for (int i = 0; i < condition.getSkuIdList().size(); ++i) {
                String splitDbInfo = shardRouterService.getSplitDbInfo(skuIdList.get(i));
                if (splitInfoMap.get(splitDbInfo) == null) {
                    SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
                    skuQueryCondition.setSkuInnerIdList(new ArrayList<Long>());
                    skuQueryCondition.setSkuIdList(new ArrayList<String>());
                    splitInfoMap.put(splitDbInfo, skuQueryCondition);
                }
                splitInfoMap.get(splitDbInfo).getSkuIdList().add(skuIdList.get(i));
                if (useSkuInnerId) {
                    splitInfoMap.get(splitDbInfo).getSkuInnerIdList().add(skuInnerIdList.get(i));
                }
            }
            Integer successNum = 0;
            for (Entry<String, SkuQueryCondition> entry : splitInfoMap.entrySet()) {
                successNum += this.skuInfoService.deleteRecords(entry.getKey(), entry.getValue());
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setSuccessNum(successNum);
            ret.setFailedNum(0);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:deleteRecords", e);
            ret.setStatus(1);
            ret.setMessage("deleteRecords Error: " + e.getMessage());
            ret.setFailedNum(condition.getSkuIdList().size());
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "insertSkuTotalInfo")
    public ModifyResponse insertSkuTotalInfo(SkuTotalInfoDto data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || (data.getSkuInfoDto() == null)) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        SkuInfoDto skuInfoDto = data.getSkuInfoDto();
        SkuAttributeDto skuAttributeDto = data.getSkuAttributeDto();
        SkuCdtDto skuCdtDto = data.getSkuCdtDto();
        SkuCommentDto skuCommentDto = data.getSkuCommentDto();
        SkuCspuDto skuCspuDto = data.getSkuCspuDto();
        SkuDescriptionDto skuDescriptionDto = data.getSkuDescriptionDto();
        SkuLevelDto skuLevelDto = data.getSkuLevelDto();
        SkuPpsDto skuPpsDto = data.getSkuPpsDto();
        if (!SkuInfoTransfer.checkRequiredField(skuInfoDto)
                || ((skuAttributeDto != null) && !SkuAttributeTransfer.checkRequiredField(skuAttributeDto))
                || ((skuCdtDto != null) && !SkuCdtTransfer.checkRequiredField(skuCdtDto))
                || ((skuCommentDto != null) && !SkuCommentTransfer.checkRequiredField(skuCommentDto))
                || ((skuCspuDto != null) && !SkuCspuTransfer.checkRequiredField(skuCspuDto))
                || ((skuDescriptionDto != null) && !SkuDescriptionTransfer.checkRequiredField(skuDescriptionDto))
                || ((skuLevelDto != null) && !SkuLevelTransfer.checkRequiredField(skuLevelDto))
                || ((skuPpsDto != null) && !SkuPpsTransfer.checkRequiredField(skuPpsDto))) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            String splitDbInfo = shardRouterService.getSplitDbInfo(skuInfoDto.getSkuid());
            SkuTotalInfoVo vo = new SkuTotalInfoVo();
            vo.setSkuInfo(SkuInfoTransfer.transDtoToBo(skuInfoDto, true));
            vo.setSkuAttribute(SkuAttributeTransfer.transDtoToBo(skuAttributeDto, true));
            vo.setSkuCdt(SkuCdtTransfer.transDtoToBo(skuCdtDto, true));
            vo.setSkuComment(SkuCommentTransfer.transDtoToBo(skuCommentDto, true));
            vo.setSkuCspu(SkuCspuTransfer.transDtoToBo(skuCspuDto, true));
            vo.setSkuDescription(SkuDescriptionTransfer.transDtoToBo(skuDescriptionDto, true));
            vo.setSkuLevel(SkuLevelTransfer.transDtoToBo(skuLevelDto, true));
            vo.setSkuPps(SkuPpsTransfer.transDtoToBo(skuPpsDto, true));
            List<SkuTotalInfoVo> skuTotalInfoVoList = new ArrayList<SkuTotalInfoVo>();
            skuTotalInfoVoList.add(vo);
            Integer successNum = this.skuInfoService.insertSkuTotalInfo(splitDbInfo, vo);
            if (successNum == 1) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Failed. May caused by:1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Fields: SkuInfo(Skuid, merchantid, feedid, title, outerid, starttime, endtime, "
                        + "signature, isselfopen, dataversion, inactivetime), BcsSkuImage(Skuid, gipsImage, type),"
                        + "SkuAttribute(Skuid, merchantid), SkuCdt(Skuid, leafcategoryid),SkuComment(Skuid, "
                        + "reviewcount, goodcount, medcount, badcount, score),"
                        + "SkuCspu(Skuid, cspuid, type),SkuDescription(Skuid, merchantid, skuDescOri and skuDesc),"
                        + "SkuLevel(Skuid), SkuPps(Skuid, merchantid, regionid, terminal, mUpdateTime) are required. "
                        + "3. Date format should be 'yyyy-MM-dd HH:mm:ss'"
                        + "4. The skuid exists already");
                        
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(1 - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:insertSkuTotalInfo", e);
            ret.setStatus(1);
            ret.setMessage("insertSkuTotalInfo Error: " + e.getMessage());
            ret.setFailedNum(1);
        }
        return ret;
    }

    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "insertSkuTotalInfoList")
    public ModifyResponse insertSkuTotalInfoList(SkuTotalInfoDtoList data) {
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
            Integer failedNum = 0;
            Map<String, List<SkuTotalInfoVo>> splitInfoMap = new HashMap<String, List<SkuTotalInfoVo>>();
            for (SkuTotalInfoDto toTalDto : data.getDtoList()) {
                SkuInfoDto skuInfoDto = toTalDto.getSkuInfoDto();
                SkuAttributeDto skuAttributeDto = toTalDto.getSkuAttributeDto();
                SkuCdtDto skuCdtDto = toTalDto.getSkuCdtDto();
                SkuCommentDto skuCommentDto = toTalDto.getSkuCommentDto();
                SkuCspuDto skuCspuDto = toTalDto.getSkuCspuDto();
                SkuDescriptionDto skuDescriptionDto = toTalDto.getSkuDescriptionDto();
                SkuLevelDto skuLevelDto = toTalDto.getSkuLevelDto();
                SkuPpsDto skuPpsDto = toTalDto.getSkuPpsDto();
                if (!SkuInfoTransfer.checkRequiredField(skuInfoDto)
                        || ((skuAttributeDto != null) && !SkuAttributeTransfer.checkRequiredField(skuAttributeDto))
                        || ((skuCdtDto != null) && !SkuCdtTransfer.checkRequiredField(skuCdtDto))
                        || ((skuCommentDto != null) && !SkuCommentTransfer.checkRequiredField(skuCommentDto))
                        || ((skuCspuDto != null) && !SkuCspuTransfer.checkRequiredField(skuCspuDto))
                        || ((skuDescriptionDto != null) && !SkuDescriptionTransfer
                                .checkRequiredField(skuDescriptionDto))
                        || ((skuLevelDto != null) && !SkuLevelTransfer.checkRequiredField(skuLevelDto))
                        || ((skuPpsDto != null) && !SkuPpsTransfer.checkRequiredField(skuPpsDto))) {
                    failedNum++;
                    continue;
                }
                String splitDbInfo = shardRouterService.getSplitDbInfo(skuInfoDto.getSkuid());
                if (splitInfoMap.get(splitDbInfo) == null) {
                    List<SkuTotalInfoVo> splitDoList = new ArrayList<SkuTotalInfoVo>();
                    splitInfoMap.put(splitDbInfo, splitDoList);
                }
                SkuTotalInfoVo vo = new SkuTotalInfoVo();
                vo.setSkuInfo(SkuInfoTransfer.transDtoToBo(skuInfoDto, true));
                vo.setSkuAttribute(SkuAttributeTransfer.transDtoToBo(skuAttributeDto, true));
                vo.setSkuCdt(SkuCdtTransfer.transDtoToBo(skuCdtDto, true));
                vo.setSkuComment(SkuCommentTransfer.transDtoToBo(skuCommentDto, true));
                vo.setSkuCspu(SkuCspuTransfer.transDtoToBo(skuCspuDto, true));
                vo.setSkuDescription(SkuDescriptionTransfer.transDtoToBo(skuDescriptionDto, true));
                vo.setSkuLevel(SkuLevelTransfer.transDtoToBo(skuLevelDto, true));
                vo.setSkuPps(SkuPpsTransfer.transDtoToBo(skuPpsDto, true));
                splitInfoMap.get(splitDbInfo).add(vo);
            }
            Integer successNum = 0;
            for (Entry<String, List<SkuTotalInfoVo>> entry : splitInfoMap.entrySet()) {
                successNum += this.skuInfoService.insertSkuTotalInfoList(entry.getKey(), entry.getValue());
            }
            if (successNum == 0) {
                ret.setStatus(1);
                ret.setMessage("All Failed. May caused by:1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Fields: SkuInfo(Skuid, merchantid, feedid, title, outerid, starttime, endtime, "
                        + "signature, isselfopen, dataversion, inactivetime), BcsSkuImage(Skuid, gipsImage, type),"
                        + "SkuAttribute(Skuid, merchantid), SkuCdt(Skuid, leafcategoryid),SkuComment(Skuid, "
                        + "reviewcount, goodcount, medcount, badcount, score),SkuCspu(Skuid, cspuid, type),"
                        + "SkuDescription(Skuid, merchantid, skuDescOri and skuDesc),SkuLevel(Skuid),"
                        + "SkuPps(Skuid, merchantid, regionid, terminal, mUpdateTime) "
                        + "are required. 3. Date format should be 'yyyy-MM-dd HH:mm:ss'" 
                        + "4. The skuid exists already");
            } else if (successNum == data.getDtoList().size()) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("part Failed. May caused by:1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Fields: SkuInfo(Skuid, merchantid, feedid, title, outerid, starttime, endtime, "
                        + "signature, isselfopen, dataversion, inactivetime), BcsSkuImage(Skuid, gipsImage, type),"
                        + "SkuAttribute(Skuid, merchantid), SkuCdt(Skuid, leafcategoryid),SkuComment(Skuid, "
                        + "reviewcount, goodcount, medcount, badcount, score),SkuCspu(Skuid, cspuid, type),"
                        + "SkuDescription(Skuid, merchantid, skuDescOri and skuDesc),SkuLevel(Skuid),"
                        + "SkuPps(Skuid, merchantid, regionid, terminal, mUpdateTime) "
                        + "are required. 3. Date format should be 'yyyy-MM-dd HH:mm:ss'"
                        + "4. The skuid exists already");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(data.getDtoList().size() - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:insertSkuTotalInfoList", e);
            ret.setStatus(1);
            ret.setMessage("insertSkuTotalInfoList Error: " + e.getMessage());
            ret.setFailedNum(data.getDtoList().size());
        }
        return ret;
    }
    
    @Override
    @ProtobufRPCService(serviceName = "skuInfoRpcService", methodName = "updateInsert")
    public ModifyResponse updateInsert(SkuTotalInfoDto data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if ((data == null) || (data.getSkuInfoDto() == null)) {
            ret.setStatus(1);
            ret.setMessage("Params input error. Data should not be empty.");
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            return ret;
        }
        SkuInfoDto skuInfoDto = data.getSkuInfoDto();
        SkuAttributeDto skuAttributeDto = data.getSkuAttributeDto();
        SkuCdtDto skuCdtDto = data.getSkuCdtDto();
        SkuCommentDto skuCommentDto = data.getSkuCommentDto();
        SkuCspuDto skuCspuDto = data.getSkuCspuDto();
        SkuDescriptionDto skuDescriptionDto = data.getSkuDescriptionDto();
        SkuLevelDto skuLevelDto = data.getSkuLevelDto();
        SkuPpsDto skuPpsDto = data.getSkuPpsDto();
        if (skuInfoDto == null || skuInfoDto.getSkuid() == null) {
            ret.setStatus(1);
            ret.setMessage("Params input error.");
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            return ret;
        }
        try {
            String splitDbInfo = shardRouterService.getSplitDbInfo(skuInfoDto.getSkuid());
            SkuTotalInfoVo vo = new SkuTotalInfoVo();
            vo.setSkuInfo(SkuInfoTransfer.transDtoToBo(skuInfoDto, false));
            vo.setSkuAttribute(SkuAttributeTransfer.transDtoToBo(skuAttributeDto, false));
            vo.setSkuCdt(SkuCdtTransfer.transDtoToBo(skuCdtDto, false));
            vo.setSkuComment(SkuCommentTransfer.transDtoToBo(skuCommentDto, false));
            vo.setSkuCspu(SkuCspuTransfer.transDtoToBo(skuCspuDto, false));
            vo.setSkuDescription(SkuDescriptionTransfer.transDtoToBo(skuDescriptionDto, false));
            vo.setSkuLevel(SkuLevelTransfer.transDtoToBo(skuLevelDto, false));
            vo.setSkuPps(SkuPpsTransfer.transDtoToBo(skuPpsDto, false));
            List<SkuTotalInfoVo> skuTotalInfoVoList = new ArrayList<SkuTotalInfoVo>();
            skuTotalInfoVoList.add(vo);
            Integer successNum = this.skuInfoService.updateInsert(splitDbInfo, vo);
            if (successNum == 1) {
                ret.setStatus(0);
                ret.setMessage("success");
            } else {
                ret.setStatus(1);
                ret.setMessage("Failed. May caused by:1. SkuId should be formated like 'merchantId_outerid', "
                        + "like '101_123'. And merchant_router table should has records for the merchantId. "
                        + "2. Fields: SkuInfo(Skuid, merchantid, feedid, title, outerid, starttime, endtime, "
                        + "signature, isselfopen, dataversion, inactivetime), BcsSkuImage(Skuid, gipsImage, type),"
                        + "SkuAttribute(Skuid, merchantid), SkuCdt(Skuid, leafcategoryid),SkuComment(Skuid, "
                        + "reviewcount, goodcount, medcount, badcount, score),"
                        + "SkuCspu(Skuid, cspuid, type),SkuDescription(Skuid, merchantid, skuDescOri and skuDesc),"
                        + "SkuLevel(Skuid), SkuPps(Skuid, merchantid, regionid, terminal, mUpdateTime) are required. "
                        + "3. Date format should be 'yyyy-MM-dd HH:mm:ss'"
                        + "4. The skuid exists already");
            }
            ret.setSuccessNum(successNum);
            ret.setFailedNum(1 - successNum);
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoRpcServiceImpl:updateInsert", e);
            ret.setStatus(1);
            ret.setMessage("updateInsert Error: " + e.getMessage());
            ret.setFailedNum(1);
        }
        return ret;
    }
}
