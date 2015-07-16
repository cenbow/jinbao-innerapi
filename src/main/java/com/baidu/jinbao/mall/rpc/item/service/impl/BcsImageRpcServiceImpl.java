package com.baidu.jinbao.mall.rpc.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.service.BcsImageService;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageCondition;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDto;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDtoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageInsertResponse;
import com.baidu.jinbao.mall.rpc.item.service.BcsImageRpcService;
import com.baidu.jinbao.mall.rpc.util.BcsImageTransfer;
import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.baidu.jprotobuf.pbrpc.spring.annotation.RpcExporter;

/**
 * BcsImageRpcServiceImpl
 * 
 * @author cgd
 * @date 2015年7月6日 下午1:57:12
 */
@Component
@RpcExporter(port = "8999")
public class BcsImageRpcServiceImpl implements BcsImageRpcService {

    private static final Logger LOG = Logger.getLogger(BcsImageRpcServiceImpl.class);

    @Autowired
    private BcsImageService bcsImageService;

    @Override
    @ProtobufRPCService(serviceName = "bcsImageRpcService", methodName = "updateBcsImageInfoList")
    public ModifyResponse updateBcsImageInfoList(BcsImageDtoList data) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if (data == null || CollectionUtils.isEmpty(data.getDtoList())) {
            ret.setStatus(1);
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            ret.setMessage("failed,because:bcsImageInfoList is empty");

            return ret;
        }

        try {
            List<BcsImage> bcsImageList = new ArrayList<BcsImage>();
            for (BcsImageDto dto : data.getDtoList()) {
                BcsImage bo = BcsImageTransfer.transferToBo(dto);
                bcsImageList.add(bo);
            }
            this.bcsImageService.updateRecords(bcsImageList);

            ret.setStatus(0);
            ret.setFailedNum(0);
            ret.setSuccessNum(data.getDtoList().size());
            ret.setMessage("success");
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in updateBcsImageInfo: ", e);

            StringBuilder sb = new StringBuilder();
            for (BcsImageDto dto : data.getDtoList()) {
                sb.append(dto.getId() + ";");
            }
            ret.setStatus(1);
            ret.setFailedNum(data.getDtoList().size());
            ret.setSuccessNum(0);
            ret.setMessage(sb.toString() + "failed,because:" + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsImageRpcService", methodName = "insertBcsImageInfo")
    public BcsImageInsertResponse insertBcsImageInfo(BcsImageDto data) {
        BcsImageInsertResponse ret = new BcsImageInsertResponse();

        // 输入参数有误
        if (data == null) {
            ret.setStatus(1);
            ret.setBcsImageId(-1L);
            ret.setMessage("Params Wrong");
            return ret;
        }

        try {
            BcsImage bo = BcsImageTransfer.transferToBo(data);
            Long bcsImageId = Long.valueOf(this.bcsImageService.insertRecord(bo));

            ret.setStatus(0);
            ret.setBcsImageId(bcsImageId);
            ret.setMessage("success");
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in insertBcsImageInfo: ", e);

            ret.setStatus(1);
            ret.setBcsImageId(-1L);
            ret.setMessage("Error: " + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsImageRpcService", methodName = "deleteBcsImageInfoList")
    public ModifyResponse deleteBcsImageInfoList(BcsImageCondition condition) {
        ModifyResponse ret = new ModifyResponse();

        // 输入参数有误
        if (condition == null || CollectionUtils.isEmpty(condition.getIdList())) {
            ret.setStatus(1);
            ret.setFailedNum(0);
            ret.setSuccessNum(0);
            ret.setMessage("failed,because: IdList is empty");

            return ret;
        }

        try {
            this.bcsImageService.deleteRecords(condition.getIdList());

            ret.setStatus(0);
            ret.setFailedNum(0);
            ret.setSuccessNum(condition.getIdList().size());
            ret.setMessage("success");
            return ret;

        } catch (Exception e) {
            LOG.error("Excetion in deleteBcsImageInfo: ", e);

            StringBuilder sb = new StringBuilder();
            for (Long id : condition.getIdList()) {
                sb.append(id + ";");
            }
            ret.setStatus(1);
            ret.setFailedNum(condition.getIdList().size());
            ret.setSuccessNum(0);
            ret.setMessage(sb.toString() + "failed,because:" + e.getMessage());
            return ret;
        }
    }

    @Override
    @ProtobufRPCService(serviceName = "bcsImageRpcService", methodName = "getBcsImageRecords")
    public BcsImageDtoListResponse getBcsImageRecords(BcsImageCondition condtion) {
        BcsImageDtoListResponse ret = new BcsImageDtoListResponse();
        if (condtion == null) {
            ret.setMessage("params error !");
            ret.setStatus(1);
            return ret;
        }
        if (CollectionUtils.isEmpty(condtion.getItemIdList()) && (CollectionUtils.isEmpty(condtion.getSkuIdList()))) {
            ret.setMessage("skuid & itemid are all null !");
            ret.setStatus(1);
            return ret;
        }

        List<BcsImage> bcsImageVoList =
                this.bcsImageService.getRecordsBySkuIdOrItemId(condtion.getItemIdList(), condtion.getSkuIdList());
        List<BcsImageDto> bcsImageDtoList = new ArrayList<BcsImageDto>();
        for (BcsImage vo : bcsImageVoList) {
            bcsImageDtoList.add(BcsImageTransfer.transferToDto(vo));
        }
        ret.setMessage("success");
        ret.setStatus(0);
        ret.setDtoList(bcsImageDtoList);
        return ret;
    }

}
