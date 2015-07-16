package com.baidu.jinbao.mall.rpc.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.bo.ItemTotalInfo;
import com.baidu.jinbao.mall.item.service.BcsImageService;
import com.baidu.jinbao.mall.item.service.ItemAttributeService;
import com.baidu.jinbao.mall.item.service.ItemCdtService;
import com.baidu.jinbao.mall.item.service.ItemDescriptionService;
import com.baidu.jinbao.mall.item.service.ItemInfoService;
import com.baidu.jinbao.mall.item.service.MallSkuInfoService;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoInsertVo;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;
import com.baidu.jinbao.mall.rpc.item.dto.ItemCondition;
import com.baidu.jinbao.mall.rpc.item.dto.ItemConditionList;
import com.baidu.jinbao.mall.rpc.item.dto.ItemInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemInfoDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInsertResponse;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemCondition;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemTotalInfoListResponse;
import com.baidu.jinbao.mall.rpc.item.service.ItemInfoRpcService;
import com.baidu.jinbao.mall.rpc.util.ItemAttributeTransfer;
import com.baidu.jinbao.mall.rpc.util.ItemCdtTransfer;
import com.baidu.jinbao.mall.rpc.util.ItemInfoTransfer;
import com.baidu.jinbao.mall.rpc.util.ItemTotalInfoTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class ItemInfoRpcServiceImpl implements ItemInfoRpcService {
    private static final Logger LOG = Logger.getLogger(ItemInfoRpcServiceImpl.class);
    @Autowired
    private ItemInfoService itemInfoService;

    /**
     * Item 相关全量数据更新
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "updateItemTotalInfo")
    public ModifyResponse updateItemTotalInfo(ItemTotalInfoDto data) {
        ModifyResponse ret = new ModifyResponse();
        if (data == null) {
            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("dtolist is empty");
            return ret;
        }
        try {
            ItemTotalInfoVo vo = ItemTotalInfoTransfer.transferToVo(data);
            if (vo.getItemInfo() == null) {
                ret.setStatus(1);
                ret.setFailedNum(1);
                ret.setSuccessNum(0);
                ret.setMessage("itemInfo is null ! tatalinfo must contains it .");
                return ret;
            }
            this.itemInfoService.updateItemTotalInfo(vo);
            ret.setStatus(0);
            ret.setFailedNum(0);
            ret.setSuccessNum(1);
            ret.setMessage("update success !");
            return ret;

        } catch (Exception e) {
            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("update failed ! " + e.getMessage());
            return ret;

        }

    }

    /**
     * Item 相关全量数据插入
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "insertItemTotalInfo")
    public ItemTotalInsertResponse insertItemTotalInfo(ItemTotalInfoDto data) {
        ItemTotalInsertResponse ret = new ItemTotalInsertResponse();

        try {
            if (data == null) {
                ret.setStatus(1);
                ret.setFailedNum(0);
                ret.setSuccessNum(0);
                ret.setMessage("dtolist is empty");
                return ret;
            }
            ItemTotalInfoVo vo = ItemTotalInfoTransfer.transferToVo(data);

            if (vo.getItemInfo() == null) {
                ret.setStatus(1);
                ret.setFailedNum(0);
                ret.setSuccessNum(0);
                ret.setMessage("itemInfo is empty");
                return ret;
            } else {
                ItemTotalInfoInsertVo insertVo = this.itemInfoService.insertItemTotalInfo(vo);
                ret.setStatus(0);
                ret.setFailedNum(0);
                ret.setSuccessNum(1);
                ret.setMessage("success !");
                ret.setImageIdList(insertVo.getImageIdList());
                ret.setItemId(insertVo.getItemId());
                ret.setSkuIdList(insertVo.getSkuIdList());
                return ret;

            }

        } catch (Exception e) {
            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            LOG.error(e.getMessage());
            ret.setMessage("insert failed ! " + e.getMessage());
            return ret;
        }

    }

    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "updateItemInfoList")
    public ModifyResponse updateItemInfoList(ItemInfoDtoList dtoList) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数为空
        if (CollectionUtils.isEmpty(dtoList.getDtoList())) {
            ret.setStatus(1);
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            ret.setMessage("dtolist is empty");
            return ret;
        }

        // 更新情况记录
        String message = "success";
        String paramErrorMsg = "";
        String parseErrorMsg = "";
        Integer total = dtoList.getDtoList().size();
        List<Long> failedList = new ArrayList<Long>();
        try {
            List<ItemInfo> itemInfoList = new ArrayList<ItemInfo>();
            for (ItemInfoDto dto : dtoList.getDtoList()) {
                // itemid不能为空
                if (dto.getItemid() == null) {
                    paramErrorMsg = "param error itemid is null .";
                    failedList.add(dto.getItemid());
                } else {
                    try {
                        ItemInfo bo = ItemInfoTransfer.transferToBo(dto);
                        itemInfoList.add(bo);
                    } catch (Exception e) {
                        LOG.error("parse error -" + e.getMessage() + dto.getItemid());
                        parseErrorMsg = "parse error -" + e.getMessage();
                        failedList.add(dto.getItemid());
                    }
                }
            }

            // 批量更新
            Integer successNum = this.itemInfoService.updateRecords(itemInfoList);

            // 全部执行成功
            if (successNum > 0) {
                ret.setStatus(0);
                // 部分失败(Parse失败)
                if (failedList.size() > 0) {
                    successNum = total - failedList.size();
                    ret.setStatus(2);
                    for (int i = 0; i < failedList.size() - 1; i++) {
                        message += String.valueOf(failedList.get(i)) + ";";
                    }
                    message += String.valueOf(failedList.get(failedList.size() - 1));
                    message += " failed,because : " + paramErrorMsg + parseErrorMsg;
                    ret.setMessage(message);
                } else {
                    ret.setStatus(0);
                    // 改变successNum,update成功则全部成功
                    successNum = total;
                    message = "success";
                }
            } else if (successNum == 0) {
                ret.setStatus(1);
                message = "failed";
            }

            ret.setFailedNum(total - successNum);
            ret.setSuccessNum(successNum);
            ret.setMessage(message);
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in updateItemInfoList: ", e);

            ret.setStatus(1);
            ret.setFailedNum(total);
            ret.setSuccessNum(0);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "getItemTotalInfo")
    public ItemTotalInfoResponse getItemTotalInfo(ItemCondition condition) {
        ItemTotalInfoResponse ret = new ItemTotalInfoResponse();
        if ((condition == null) || (condition.getItemId() == null)) {
            ret.setStatus(1);
            ret.setMessage("输入参数为null");
            return ret;
        }
        try {
            Long itemId = condition.getItemId();
            ItemTotalInfo boResult = itemInfoService.getItemTotalByItemId(itemId);
            ItemTotalInfoDto result = ItemTotalInfoTransfer.transferToDto(boResult);
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setDto(result);
            return ret;
        } catch (Exception e) {
            LOG.error("Exception in getItemTotalInfo: ", e);
            ret.setStatus(1);
            ret.setMessage("Error:" + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "getItemTotalInfoList")
    public ItemTotalInfoListResponse getItemTotalInfoList(ItemConditionList data) {
        ItemTotalInfoListResponse ret = new ItemTotalInfoListResponse();
        if ((data == null) || (data.getItemIdList() == null)) {
            ret.setStatus(1);
            ret.setMessage("输入参数为null");
            return ret;
        }
        try {
            List<ItemTotalInfoDto> itemTotalInfoDtoList = new ArrayList<ItemTotalInfoDto>();
            for (int i = 0; i < data.getItemIdList().size(); i++) {
                ItemTotalInfo boResult = itemInfoService.getItemTotalByItemId(data.getItemIdList().get(i));
                ItemTotalInfoDto result = ItemTotalInfoTransfer.transferToDto(boResult);
                itemTotalInfoDtoList.add(result);
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setDtoList(itemTotalInfoDtoList);
            return ret;
        } catch (Exception e) {
            LOG.error("Exception in getItemTotalInfo: ", e);
            ret.setStatus(1);
            ret.setMessage("Error:" + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallItemInfoRpcService", methodName = "getPageItemTotalInfo")
    public PageItemTotalInfoListResponse getPageItemTotalInfo(PageItemCondition condition) {
        PageItemTotalInfoListResponse ret = new PageItemTotalInfoListResponse();
        if (condition == null || CollectionUtils.isEmpty(condition.getShopIdList()) || condition.getRank() == null
                || condition.getPageNumber() == null || condition.getPageSize() == null) {
            ret.setStatus(1);
            ret.setMessage("输入参数为错误");
            return ret;
        }
        try {
            PageItemVo pageItemVo = new PageItemVo();
            pageItemVo.setShopIdList(condition.getShopIdList());
            pageItemVo.setLeafcategoryidList(condition.getLeafcategoryidList());
            if (condition.getManualStatus() != null) {
                pageItemVo.setManualStatus(condition.getManualStatus().byteValue());
            }
            if (condition.getMerchantStatus() != null) {
                pageItemVo.setMerchantStatus(condition.getMerchantStatus().byteValue());
            }
            pageItemVo.setPageNumber(condition.getPageNumber());
            pageItemVo.setPageSize(condition.getPageSize());
            pageItemVo.setRank(condition.getRank());
            List<PageItemTotalInfoVo> voList = this.itemInfoService.getPageItemTotalInfo(pageItemVo);
            List<PageItemTotalInfoDto> pageItemTotalInfoDtoList = new ArrayList<PageItemTotalInfoDto>();
            for (PageItemTotalInfoVo vo : voList) {
                PageItemTotalInfoDto dto = new PageItemTotalInfoDto();
                dto.setItemInfoDto(ItemInfoTransfer.transferToDto(vo.getItemInfo()));
                dto.setItemAttributeDto(ItemAttributeTransfer.transferToDto(vo.getItemAttribute()));
                dto.setItemCdtDto(ItemCdtTransfer.transferToDto(vo.getItemCdt()));
                pageItemTotalInfoDtoList.add(dto);
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setPageNumber(pageItemVo.getPageNumber());
            ret.setPageSize(pageItemVo.getPageSize());
            ret.setTotalNum(pageItemVo.getTotalNum());
            ret.setPages(pageItemVo.getPages());
            ret.setDtoList(pageItemTotalInfoDtoList);
            return ret;
        } catch (Exception e) {
            LOG.error("Exception in getPageItemTotalInfo: ", e);
            ret.setStatus(1);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

}
