package com.baidu.jinbao.mall.rpc.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.service.MallSkuPpsService;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDtoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.SkuPpsCondition;
import com.baidu.jinbao.mall.rpc.item.dto.SkuPpsConditionList;
import com.baidu.jinbao.mall.rpc.item.service.MallSkuPpsRpcService;
import com.baidu.jinbao.mall.rpc.util.MallSkuPpsTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MallSkuPpsRpcServiceImpl implements MallSkuPpsRpcService {

    private static final Logger LOG = Logger.getLogger(MallSkuPpsRpcServiceImpl.class);

    @Autowired
    private MallSkuPpsService mallSkuPpsService;

    @Override
    @ProtobufRPCService(serviceName = "mallSkuPpsRpcService", methodName = "updateSkuPpsInfoList")
    public ModifyResponse updateSkuPpsInfoList(MallSkuPpsDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数为空
        if (data == null || CollectionUtils.isEmpty(data.getMallSkuPpsDtoList())) {
            ret.setStatus(1);
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            ret.setMessage("dtolist is empty.");
            return ret;
        }

        // 记录更新情况
        Integer total = data.getMallSkuPpsDtoList().size();
        String message = "success";
        List<Long> failedList = new ArrayList<Long>();
        try {
            ArrayList<MallSkuPps> mallSkuPpsList = new ArrayList<MallSkuPps>();
            for (MallSkuPpsDto dto : data.getMallSkuPpsDtoList()) {
                try {
                    MallSkuPps bo = MallSkuPpsTransfer.transferToBo(dto);
                    mallSkuPpsList.add(bo);
                } catch (Exception e) {
                    LOG.error("parse error : becuuse" + e.getMessage());
                    failedList.add(dto.getSkuid());

                }
            }

            Integer successNum = this.mallSkuPpsService.batchUpdateRecord(mallSkuPpsList);
            // 全部执行成功
            if (successNum > 0) {
                // 部分成功
                if (failedList.size() > 0) {
                    ret.setStatus(2);
                    for (int i = 0; i < failedList.size() - 1; i++) {
                        message += String.valueOf(failedList.get(i)) + ";";
                    }
                    message += " failed,because : parse error ";
                    successNum = total - failedList.size();
                } else {
                    // 更新成功，则全部成功.
                    ret.setStatus(0);
                    successNum = total;
                }
            }
            // 全部失败
            else if (successNum == 0) {
                ret.setStatus(1);
                message = "failed";
            }

            ret.setFailedNum(total - successNum);
            ret.setSuccessNum(successNum);
            ret.setMessage(message);
            return ret;

        } catch (Exception e) {
            LOG.error("Exception in updateSkuPpsInfoList: ", e);

            ret.setStatus(1);
            ret.setSuccessNum(0);
            ret.setFailedNum(total);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }

    }

    @Override
    @ProtobufRPCService(serviceName = "mallSkuPpsRpcService", methodName = "getSkuPpsInfoList")
    public MallSkuPpsDtoListResponse getSkuPpsInfoList(SkuPpsConditionList data) {
        MallSkuPpsDtoListResponse ret = new MallSkuPpsDtoListResponse();
        if (data == null || data.getConditionList() == null) {
            ret.setStatus(1);
            ret.setMessage("input param is empty");
            return ret;
        }
        try {
            List<SkuPpsCondition> skuCondtions = data.getConditionList();
            List<Long> skuIdList = new ArrayList<Long>();
            List<Integer> regionIdList = new ArrayList<Integer>();
            List<Integer> deviceList = new ArrayList<Integer>();
            List<String> failedList = new ArrayList<String>();
            for (SkuPpsCondition skuPpsCondition : skuCondtions) {
                if ((skuPpsCondition.getSkuId() == null) || (skuPpsCondition.getRegionId() == null)
                        || (skuPpsCondition.getPcMobile() == null)) {
                    StringBuilder errorMsg = new StringBuilder();
                    errorMsg.append(String.valueOf(skuPpsCondition.getSkuId()));
                    errorMsg.append(",");
                    errorMsg.append(String.valueOf(skuPpsCondition.getRegionId()));
                    errorMsg.append(",");
                    errorMsg.append(String.valueOf(skuPpsCondition.getPcMobile()));
                    LOG.error("skuid or regionid or Pcmobile is null Because:" + errorMsg.toString());
                    failedList.add(errorMsg.toString());
                    continue;
                }
                skuIdList.add(skuPpsCondition.getSkuId());
                regionIdList.add(skuPpsCondition.getRegionId());
                deviceList.add(skuPpsCondition.getPcMobile());
            }
            List<MallSkuPps> voList = this.mallSkuPpsService.getSkuPpsInfoList(skuIdList, regionIdList, deviceList);

            List<MallSkuPpsDto> dtoList = new ArrayList<MallSkuPpsDto>();
            for (MallSkuPps vo : voList) {
                MallSkuPpsDto dto = MallSkuPpsTransfer.transferToDto(vo);
                dtoList.add(dto);
            }
            if (failedList.size() > 0) {
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < failedList.size() - 1; i++) {
                    message.append(failedList.get(i) + ";");
                }
                message.append(failedList.get(failedList.size() - 1));
                message.append(" failed,because : skuid or regionid or Pcmobile is null ");
                ret.setMessage(message.toString());
                ret.setStatus(2);
                ret.setDtoList(dtoList);
                return ret;

            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setDtoList(dtoList);
            return ret;
        } catch (Exception e) {
            LOG.error("Excetion in getSkuPpsInfoList: ", e);
            ret.setStatus(1);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

}
