package com.baidu.jinbao.mall.rpc.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.item.service.MallSkuInfoService;
import com.baidu.jinbao.mall.item.vo.MallSkuTotalInfoVo;
import com.baidu.jinbao.mall.rpc.item.dto.ItemConditionList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuInfoDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.SkuTotalInsertResponse;
import com.baidu.jinbao.mall.rpc.item.service.MallSkuInfoRpcService;
import com.baidu.jinbao.mall.rpc.util.MallSkuInfoTransfer;
import com.baidu.jinbao.mall.rpc.util.MallSkuPpsTransfer;
import com.baidu.jinbao.mall.rpc.util.MallSkuTotalTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

@Component
@RpcExporter(port = "8999")
public class MallSkuInfoRpcServiceImpl implements MallSkuInfoRpcService {
    private static final Logger LOG = Logger.getLogger(MallSkuInfoRpcServiceImpl.class);

    @Autowired
    MallSkuInfoService mallSkuInfoService;

    @Override
    @ProtobufRPCService(serviceName = "mallSkuInfoRpcService", methodName = "updateSkuTotalInfo")
    public ModifyResponse updateSkuTotalInfo(MallSkuTotalInfoDto data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if (data == null || data.getMallSkuInfoDto() == null || data.getMallSkuInfoDto().getSkuid() == null) {
            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("Params Wrong");

            return ret;
        }

        try {
            MallSkuTotalInfoVo vo = new MallSkuTotalInfoVo();
            vo.setMallSkuInfo(MallSkuInfoTransfer.transferToBo(data.getMallSkuInfoDto()));
            if (data.getMallSkuPpsDtoList() != null) {
                List<MallSkuPps> mallSkuPpsList = new ArrayList<MallSkuPps>();
                for (MallSkuPpsDto dto : data.getMallSkuPpsDtoList()) {
                    mallSkuPpsList.add(MallSkuPpsTransfer.transferToBo(dto));
                }
                vo.setMallSkuPpsList(mallSkuPpsList);
            }
            Integer successNum = this.mallSkuInfoService.updateSkuTotalInfo(vo);
            ret.setStatus(0);
            ret.setFailedNum(1 - successNum);
            ret.setSuccessNum(successNum);
            ret.setMessage("success");
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in updateSkuTotalInfo: ", e);

            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallSkuInfoRpcService", methodName = "insertSkuTotalInfo")
    public SkuTotalInsertResponse insertSkuTotalInfo(MallSkuTotalInfoDto data) {
        SkuTotalInsertResponse ret = new SkuTotalInsertResponse();

        // 输入参数有误
        if (data == null || data.getMallSkuInfoDto() == null) {
            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("Params Wrong");

            return ret;
        }

        try {
            MallSkuTotalInfoVo vo = new MallSkuTotalInfoVo();
            vo.setMallSkuInfo(MallSkuInfoTransfer.transferToBo(data.getMallSkuInfoDto()));
            if (data.getMallSkuPpsDtoList() != null) {
                List<MallSkuPps> mallSkuPpsList = new ArrayList<MallSkuPps>();
                for (MallSkuPpsDto dto : data.getMallSkuPpsDtoList()) {
                    mallSkuPpsList.add(MallSkuPpsTransfer.transferToBo(dto));
                }
                vo.setMallSkuPpsList(mallSkuPpsList);
            }
            Long skuid = this.mallSkuInfoService.insertSkuTotalInfo(vo);

            ret.setStatus(0);
            ret.setFailedNum(0);
            ret.setSuccessNum(1);
            ret.setMessage("success");
            ret.setSkuId(skuid);
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in insertSkuTotalInfo: ", e);

            ret.setStatus(1);
            ret.setFailedNum(1);
            ret.setSuccessNum(0);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "mallSkuInfoRpcService", methodName = "getSkuTotalInfoList")
    public MallSkuTotalInfoListResponse getSkuTotalInfoList(ItemConditionList data) {
        MallSkuTotalInfoListResponse ret = new MallSkuTotalInfoListResponse();
        if ((data == null) || (data.getItemIdList() == null)) {
            ret.setStatus(1);
            ret.setMessage("输入参数为null");
            return ret;
        }
        try {
            List<MallSkuTotalInfoDto> mallSkuTotalDtoList = new ArrayList<MallSkuTotalInfoDto>();
            List<MallSkuTotalInfo> boResults = mallSkuInfoService.getSkuTotalInfoByItemIdList(data.getItemIdList());
            for (int i = 0; i < boResults.size(); i++) {
                MallSkuTotalInfoDto mallSkuTotalDto = MallSkuTotalTransfer.transferToDto(boResults.get(i));
                mallSkuTotalDtoList.add(mallSkuTotalDto);
            }
            ret.setStatus(0);
            ret.setMessage("success");
            ret.setDtoList(mallSkuTotalDtoList);
            return ret;
        } catch (Exception e) {
            LOG.error("Exception in getItemTotalInfo: ", e);
            ret.setStatus(1);
            ret.setMessage("Error:" + e.getMessage());
            return ret;
        }

    }

    @Override
    @ProtobufRPCService(serviceName = "mallSkuInfoRpcService", methodName = "updateSkuInfoRecords")
    public ModifyResponse updateSkuInfoRecords(MallSkuInfoDtoList data) {
        ModifyResponse ret = new ModifyResponse();
        List<Long> failedIdList = new ArrayList<Long>();

        if ((data == null) || (CollectionUtils.isEmpty(data.getDtoList()))) {
            ret.setStatus(1);
            ret.setMessage("输入参数为null");
            return ret;
        } else {
            try {
                List<MallSkuInfo> skuInfoList = new ArrayList<MallSkuInfo>();
                for (MallSkuInfoDto dto : data.getDtoList()) {
                    try {
                        skuInfoList.add(MallSkuInfoTransfer.transferToBo(dto));
                    } catch (Exception e) {
                        failedIdList.add(dto.getSkuid());
                        LOG.error(e.getMessage() + " skuid = " + dto.getSkuid());
                    }
                }
                int num = this.mallSkuInfoService.updateRecords(skuInfoList);
                ret.setStatus(0);
                ret.setSuccessNum(num);

                if (num == data.getDtoList().size()) {
                    ret.setMessage("success");
                    ret.setFailedNum(data.getDtoList().size() - num);
                } else {
                    String msg = "";
                    if (failedIdList.size() > 0) {
                        msg += failedIdList.toArray().toString();
                    }
                    ret.setFailedNum(data.getDtoList().size() - num);
                    ret.setMessage("part success : " + msg + " parse error");
                }
                return ret;

            } catch (Exception e) {
                LOG.error("Exception in updateSkuInfoRecords: ", e);

                ret.setStatus(1);
                ret.setMessage("Error:" + e.getMessage());
                return ret;
            }
        }
    }
}
