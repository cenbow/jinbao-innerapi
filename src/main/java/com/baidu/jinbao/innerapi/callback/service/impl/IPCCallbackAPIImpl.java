package com.baidu.jinbao.innerapi.callback.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackFailedTaskRequest;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackFailedTaskRequest.ErrorMessage;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackResponse;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackSuccessTaskRequest;
import com.baidu.adrc.ipc.api_interface.server.dto.imagebo.ImageBOR;
import com.baidu.dpop.frame.core.context.SpringContextUtil;
import com.baidu.jinbao.innerapi.callback.constant.CallBackConstants;
import com.baidu.jinbao.innerapi.callback.service.IPCCallbackAPI;
import com.baidu.jinbao.innerapi.callback.utils.CallBackUtils;
import com.baidu.jinbao.innerapi.rpc.sku.common.ShardRouterService;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.service.BcsSkuImageService;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.service.BcsCspuImageService;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.service.BcsImageService;

/**
 * IPCCallbackAPI 实现
 * 
 * @author cgd
 * @date 2015年6月18日 上午11:40:44
 */
public class IPCCallbackAPIImpl implements IPCCallbackAPI {

    private static final Logger LOG = Logger.getLogger(IPCCallbackAPIImpl.class);

    @Override
    public IPCCallbackResponse callbackSuccessTasks(IPCCallbackSuccessTaskRequest request) {
        // return 信息
        IPCCallbackResponse ret = new IPCCallbackResponse();
        Set<String> failedUrlSet = new HashSet<String>();

        // 输入数据有误
        if (request == null || CollectionUtils.isEmpty(request.getImages())) {
            ret.setFailedURls(failedUrlSet);
            ret.setError_code("0");
            ret.setErrorMsg("");
            return ret;
        }

        // sku image 对应分片信息Map
        Map<String, List<BcsSkuImage>> skuSplitImageMap = new HashMap<String, List<BcsSkuImage>>();
        // cspu image 信息list
        List<BcsCspuImage> cspuImageList = new ArrayList<BcsCspuImage>();
        // mall image 信息list
        List<BcsImage> mallImageList = new ArrayList<BcsImage>();

        // 遍历归类sku && cspu错误image相关信息
        List<ImageBOR> data = request.getImages();
        for (ImageBOR item : data) {
            try {
                String callerMsg = item.getCaller_msg();
                if (StringUtils.isEmpty(callerMsg)) {
                    failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
                    continue;
                }

                // 百度惠SKU图片处理
                if (CallBackUtils.getProductImageType(callerMsg) == CallBackConstants.PRODUCT_HUI_SKU_IMAGE_TYPE) {
                    this.setHuiSkuSuccessImageBo(skuSplitImageMap, failedUrlSet, item);
                }
                // 百度惠CSPU图片处理
                else if (CallBackUtils.getProductImageType(callerMsg) 
                        == CallBackConstants.PRODUCT_HUI_CSPU_IMAGE_TYPE) {
                    this.setHuiCspuSuccessImageBo(cspuImageList, failedUrlSet, item);
                }
                // 百度Mall图片处理
                else if (CallBackUtils.getProductImageType(callerMsg) == CallBackConstants.PRODUCT_MALL_IMAGE_TYPE) {
                    this.setMallSuccessImageBo(mallImageList, failedUrlSet, item);
                }

            } catch (Exception e) {
                LOG.error("callbackFailedTasks foreach error: ", e);
                failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
            }
        }

        // 保存各产品线图片数据
        if (skuSplitImageMap.size() > 0) {
            this.saveHuiSkuImages(skuSplitImageMap, failedUrlSet);
        }
        if (CollectionUtils.isNotEmpty(cspuImageList)) {
            this.saveHuiCspuImages(cspuImageList, failedUrlSet);
        }
        if (CollectionUtils.isNotEmpty(mallImageList)) {
            this.updateMallImages(mallImageList, failedUrlSet);
        }

        ret.setError_code("0");
        ret.setErrorMsg("success");
        ret.setFailedURls(failedUrlSet);
        return ret;
    }

    @Override
    public IPCCallbackResponse callbackFailedTasks(IPCCallbackFailedTaskRequest request) {
        // return 信息
        IPCCallbackResponse ret = new IPCCallbackResponse();
        Set<String> failedUrlSet = new HashSet<String>();

        // 输入数据有误
        if (request == null || CollectionUtils.isEmpty(request.getErrorMsgs())) {
            ret.setFailedURls(failedUrlSet);
            ret.setError_code("0");
            ret.setErrorMsg("");
            return ret;
        }

        // sku image 对应分片信息Map
        Map<String, List<BcsSkuImage>> skuSplitImageMap = new HashMap<String, List<BcsSkuImage>>();
        // cspu image 信息list
        List<BcsCspuImage> cspuImageList = new ArrayList<BcsCspuImage>();
        // mall image 信息list
        List<BcsImage> mallImageList = new ArrayList<BcsImage>();

        // 遍历归类sku && cspu错误image相关信息
        List<ErrorMessage> errorMsgList = request.getErrorMsgs();
        for (ErrorMessage item : errorMsgList) {
            try {
                String callerMsg = item.getCaller_msg();
                if (StringUtils.isEmpty(callerMsg)) {
                    failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
                    continue;
                }

                // 百度惠SKU图片处理
                if (CallBackUtils.getProductImageType(callerMsg) == CallBackConstants.PRODUCT_HUI_SKU_IMAGE_TYPE) {
                    this.setHuiSkuErrorImageBo(skuSplitImageMap, failedUrlSet, item);
                }
                // 百度惠CSPU图片处理
                else if (CallBackUtils.getProductImageType(callerMsg) 
                        == CallBackConstants.PRODUCT_HUI_CSPU_IMAGE_TYPE) {
                    this.setHuiCspuErrorImageBo(cspuImageList, failedUrlSet, item);
                }
                // 百度Mall图片处理
                else if (CallBackUtils.getProductImageType(callerMsg) == CallBackConstants.PRODUCT_MALL_IMAGE_TYPE) {
                    this.setMallErrorImageBo(mallImageList, failedUrlSet, item);
                }

            } catch (Exception e) {
                LOG.error("callbackFailedTasks foreach error: ", e);
                failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
            }
        }

        // 保存各产品线图片数据
        if (skuSplitImageMap.size() > 0) {
            this.saveHuiSkuImages(skuSplitImageMap, failedUrlSet);
        }
        if (CollectionUtils.isNotEmpty(cspuImageList)) {
            this.saveHuiCspuImages(cspuImageList, failedUrlSet);
        }
        if (CollectionUtils.isNotEmpty(mallImageList)) {
            this.updateMallImages(mallImageList, failedUrlSet);
        }

        ret.setError_code("0");
        ret.setErrorMsg("success");
        ret.setFailedURls(failedUrlSet);
        return ret;
    }

    /**
     * SET百度惠SKU处理成功的图片信息
     * */
    private void setHuiSkuSuccessImageBo(Map<String, List<BcsSkuImage>> skuSplitImageMap, Set<String> failedUrlSet,
            ImageBOR item) {

        String skuid = CallBackUtils.getHuiCallerMsgValue(item.getCaller_msg());
        ;
        ShardRouterService shardRouterService = SpringContextUtil.getBean(ShardRouterService.class);
        String splitDbInfo = shardRouterService.getSplitDbInfo(skuid);
        if (StringUtils.isEmpty(splitDbInfo)) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }

        BcsSkuImage bo = CallBackUtils.transToHuiSkuImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        List<BcsSkuImage> skuImageList = skuSplitImageMap.get(splitDbInfo);
        if (skuImageList == null) {
            skuImageList = new ArrayList<BcsSkuImage>();
        }
        skuImageList.add(bo);
        skuSplitImageMap.put(splitDbInfo, skuImageList);

    }

    /**
     * SET百度惠CSPU处理成功的图片信息
     * */
    private void setHuiCspuSuccessImageBo(List<BcsCspuImage> cspuImageList, Set<String> failedUrlSet, ImageBOR item) {
        BcsCspuImage bo = CallBackUtils.transToHuiCspuImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        cspuImageList.add(bo);
    }

    /**
     * SET百度Mall处理成功的图片信息
     * */
    private void setMallSuccessImageBo(List<BcsImage> mallImageList, Set<String> failedUrlSet, ImageBOR item) {
        BcsImage bo = CallBackUtils.transToMallImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        mallImageList.add(bo);
    }

    /**
     * SET百度惠SKU处理失败的图片信息
     * */
    private void setHuiSkuErrorImageBo(Map<String, List<BcsSkuImage>> skuSplitImageMap, Set<String> failedUrlSet,
            ErrorMessage item) {

        // 解析callerMsg (skuid=1001_201)
        String skuid = CallBackUtils.getHuiCallerMsgValue(item.getCaller_msg());
        if (StringUtils.isEmpty(skuid)) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }

        ShardRouterService shardRouterService = SpringContextUtil.getBean(ShardRouterService.class);
        String splitDbInfo = shardRouterService.getSplitDbInfo(skuid);
        if (StringUtils.isEmpty(splitDbInfo)) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }

        BcsSkuImage bo = CallBackUtils.transToHuiSkuImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        List<BcsSkuImage> skuImageList = skuSplitImageMap.get(splitDbInfo);
        if (skuImageList == null) {
            skuImageList = new ArrayList<BcsSkuImage>();
        }
        skuImageList.add(bo);
        skuSplitImageMap.put(splitDbInfo, skuImageList);
    }

    /**
     * SET百度惠CSPU处理失败的图片信息
     * */
    private void setHuiCspuErrorImageBo(List<BcsCspuImage> cspuImageList, Set<String> failedUrlSet, ErrorMessage item) {
        // 数据转换
        BcsCspuImage bo = CallBackUtils.transToHuiCspuImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        cspuImageList.add(bo);
    }

    /**
     * SET百度Mall处理失败的图片信息
     * */
    private void setMallErrorImageBo(List<BcsImage> mallImageList, Set<String> failedUrlSet, ErrorMessage item) {
        BcsImage bo = CallBackUtils.transToMallImageBo(item);
        if (bo == null) {
            failedUrlSet.add(item.getOrginUrl() == null ? "EmptyURL" : item.getOrginUrl());
        }
        mallImageList.add(bo);
    }

    /**
     * 保存百度惠SKU处理失败的图片信息
     * */
    private void saveHuiSkuImages(Map<String, List<BcsSkuImage>> skuSplitImageMap, Set<String> failedUrlSet) {
        for (String splitDbInfo : skuSplitImageMap.keySet()) {
            List<BcsSkuImage> skuImageList = skuSplitImageMap.get(splitDbInfo);
            if (CollectionUtils.isNotEmpty(skuImageList)) {
                try {
                    BcsSkuImageService bcsSkuImageService = SpringContextUtil.getBean(BcsSkuImageService.class);
                    bcsSkuImageService.insertRecords(splitDbInfo, skuImageList);
                } catch (Exception e) {
                    LOG.error("callbackFailedTasks sku image insert error: ", e);
                    for (BcsSkuImage skuImage : skuImageList) {
                        failedUrlSet.add(skuImage.getImageUrl());
                    }
                }
            }
        }
    }

    /**
     * 保存百度惠CSPU处理失败的图片信息
     * */
    private void saveHuiCspuImages(List<BcsCspuImage> cspuImageList, Set<String> failedUrlSet) {
        try {
            BcsCspuImageService bcsCspuImageService = SpringContextUtil.getBean(BcsCspuImageService.class);
            bcsCspuImageService.insertRecords(cspuImageList);
        } catch (Exception e) {
            LOG.error("callbackFailedTasks sku image insert error: ", e);
            for (BcsCspuImage cspuImage : cspuImageList) {
                failedUrlSet.add(cspuImage.getImageUrl());
            }
        }
    }

    /**
     * 保存百度Mall处理失败的图片信息
     * */
    private void updateMallImages(List<BcsImage> mallImageList, Set<String> failedUrlSet) {
        try {
            BcsImageService bcsImageService = SpringContextUtil.getBean(BcsImageService.class);
            bcsImageService.updateRecords(mallImageList);
        } catch (Exception e) {
            LOG.error("callbackFailedTasks sku image insert error: ", e);
            for (BcsImage bcsImage : mallImageList) {
                failedUrlSet.add(bcsImage.getImageurl());
            }
        }
    }

}
