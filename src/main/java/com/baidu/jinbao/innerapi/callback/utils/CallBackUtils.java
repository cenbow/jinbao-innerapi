package com.baidu.jinbao.innerapi.callback.utils;

import java.util.Date;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.baidu.adrc.ipc.api_interface.server.dto.IPCCallbackFailedTaskRequest.ErrorMessage;
import com.baidu.adrc.ipc.api_interface.server.dto.imagebo.ImageBOR;
import com.baidu.jinbao.innerapi.callback.constant.CallBackConstants;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;
import com.baidu.jinbao.innerapi.sku.constant.BcsSkuImageStatus;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.constant.BcsCspuImageStatus;
import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.item.constant.BcsImageStatus;

/**
 * CallBack相关的utils方法
 * 
 * @author cgd
 * @date 2015年6月18日 下午5:54:44
 */
public class CallBackUtils {
    
    private static final Logger LOG = Logger.getLogger(CallBackUtils.class);

    /**
     * 确认图片所属产品线
     * 
     * @param callerMsg skuid=1001
     * @return 图片对应产品线类型（百度惠、百度mall）
     * */
    public static Integer getProductImageType(String callerMsg) {
        if (callerMsg == null) {
            return CallBackConstants.PRODUCT_NOT_FOUND_IMAGE_TYPE;
        }

        if (callerMsg.indexOf(CallBackConstants.HUI_SKU_IMAGE_KEY) == 0) {
            return CallBackConstants.PRODUCT_HUI_SKU_IMAGE_TYPE;
        } else if (callerMsg.indexOf(CallBackConstants.HUI_CSPU_IMAGE_KEY) == 0) {
            return CallBackConstants.PRODUCT_HUI_CSPU_IMAGE_TYPE;
        } else if (callerMsg.indexOf(CallBackConstants.MALL_ITEM_IMAGE_KEY) == 0) {
            return CallBackConstants.PRODUCT_MALL_IMAGE_TYPE;
        }

        return CallBackConstants.PRODUCT_NOT_FOUND_IMAGE_TYPE;
    }
    
    /**
     * 获取callerMsg中的业务表ID对应的描述
     * 
     * @param callerMsg skuid=1001
     * @return 业务表ID对应的描述（eg: skuid）
     * */
    public static String getHuiCallerMsgKey(String callerMsg) {
        if (StringUtils.isEmpty(callerMsg)) {
            return null;
        }

        return callerMsg.split("=")[0];
    }

    /**
     * 获取callerMsg中的业务表ID
     * 
     * @param callerMsg skuid=1001
     * @return 业务表ID对应的描述（eg: skuid）
     * */
    public static String getHuiCallerMsgValue(String callerMsg) {
        if (StringUtils.isEmpty(callerMsg)) {
            return null;
        }

        String[] msgArr = callerMsg.split("=");
        if (msgArr.length != 2) {
            return null;
        }

        return callerMsg.split("=")[1];
    }
    
    /**
     * 获取Mall图片callerMsg中的itemID
     * 
     * @param callerMsg itemid=1001,skuid=201,pictype=0
     * @return itemId
     * */
    public static String getMallImageId(String callerMsg) {
        if (StringUtils.isEmpty(callerMsg)) {
            return null;
        }
        
        String[] attributeArr = callerMsg.split(",");
        return attributeArr[0].split("=")[1];
    }
    
    /**
     * 将 ImageBOR 转成 sku image bo对象存储
     * 
     * @param imageBor IPC处理成功的图片信息
     * @return imageBor转成的ImageBO
     * 
     * */
    public static BcsSkuImage transToHuiSkuImageBo(ImageBOR imageBor) {

        BcsSkuImage bo = new BcsSkuImage();

        try {
            // set skuid
            String skuid = getHuiCallerMsgValue(imageBor.getCaller_msg());
            bo.setSkuid(skuid);

            bo.setImageUrl(imageBor.getImageSource().getImage_source_url());
            bo.setImageBcsUrl(imageBor.getImageSource().getImage_bcs_url());
            bo.setStatus(BcsSkuImageStatus.DOWNLOAD_TO_LOCAL.getId());
            bo.setAddtime(new Date());
            bo.setUpdatetime(new Date());
            bo.setErrormessage("");
            bo.setHeight(imageBor.getImageSource().getHeight());
            bo.setWidth(imageBor.getImageSource().getWidth());
            bo.setContentMd5(imageBor.getImageSource().getContent_md5());
            bo.setSequence(1);

            String gipsImageJsonStr = JSON.toJSONString(imageBor.getImageGipsTn());
            bo.setGipsImage(gipsImageJsonStr);
            bo.setType((byte) 0);

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToHuiSkuImageBo: ", e);
            return null;
        }

    }

    /**
     * 将 ErrorMsg 转成 sku image bo对象存储
     * 
     * @param errorMsg IPC处理失败的图片信息
     * @return errorMsg转成的ImageBO
     * 
     * */
    public static BcsSkuImage transToHuiSkuImageBo(ErrorMessage errorMsg) {
        BcsSkuImage bo = new BcsSkuImage();
        try {
            // set skuid
            String skuid = getHuiCallerMsgValue(errorMsg.getCaller_msg());
            bo.setSkuid(skuid);
            bo.setSkuInnerid(1L);

            bo.setImageUrl(errorMsg.getOrginUrl());
            bo.setImageBcsUrl("");
            bo.setStatus(BcsSkuImageStatus.DOWNLOAD_ERROR.getId());
            bo.setAddtime(new Date());
            bo.setUpdatetime(new Date());
            bo.setErrormessage(errorMsg.getErrorReason() == null ? "" : errorMsg.getErrorReason());
            bo.setHeight(0);
            bo.setWidth(0);
            bo.setContentMd5("");
            bo.setSequence(1);
            bo.setGipsImage("");
            bo.setType((byte) 0);

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToHuiSkuImageBo: ", e);
            return null;
        }
    }

    /**
     * 将 ImageBOR 转成 cspu image bo对象存储
     * 
     * @param imageBor IPC处理成功的图片信息
     * @return imageBor转成的ImageBO
     * 
     * */
    public static BcsCspuImage transToHuiCspuImageBo(ImageBOR imageBor) {
        BcsCspuImage bo = new BcsCspuImage();

        try {
            // cspuid
            Long cspuid = Long.valueOf(getHuiCallerMsgValue(imageBor.getCaller_msg()));
            bo.setCspuid(cspuid);

            bo.setImageUrl(imageBor.getImageSource().getImage_source_url());
            bo.setImageBcsUrl(imageBor.getImageSource().getImage_bcs_url());
            bo.setStatus(BcsCspuImageStatus.DOWNLOAD_TO_LOCAL.getId());
            bo.setAddtime(new Date());
            bo.setUpdatetime(new Date());
            bo.setErrormessage("");
            bo.setHeight(imageBor.getImageSource().getHeight());
            bo.setWidth(imageBor.getImageSource().getWidth());
            bo.setContentMd5(imageBor.getImageSource().getContent_md5());
            bo.setSequence(1);

            String gipsImageJsonStr = JSON.toJSONString(imageBor.getImageGipsTn());
            bo.setGipsImage(gipsImageJsonStr);
            bo.setType((byte) 0);

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToHuiCspuImageBo: ", e);
            return null;
        }
    }

    /**
     * 将 ErrorMsg 转成 cspu image bo对象存储
     * 
     * @param errorMsg IPC处理失败的图片信息
     * @return errorMsg转成的ImageBO
     * 
     * */
    public static BcsCspuImage transToHuiCspuImageBo(ErrorMessage errorMsg) {
        BcsCspuImage bo = new BcsCspuImage();

        try {
            // set cspuid
            Long cspuid = Long.valueOf(getHuiCallerMsgValue(errorMsg.getCaller_msg()));
            bo.setCspuid(cspuid);

            bo.setImageUrl(errorMsg.getOrginUrl());
            bo.setImageBcsUrl("");
            bo.setStatus(BcsCspuImageStatus.DOWNLOAD_ERROR.getId());
            bo.setAddtime(new Date());
            bo.setUpdatetime(new Date());
            bo.setErrormessage(errorMsg.getErrorReason() == null ? "" : errorMsg.getErrorReason());
            bo.setHeight(0);
            bo.setWidth(0);
            bo.setContentMd5("");
            bo.setSequence(1);
            bo.setGipsImage("");
            bo.setType((byte) 0);

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToHuiCspuImageBo: ", e);
            return null;
        }
    }
    
    /**
     * 将 ErrorMsg 转成 mall image bo对象存储
     * 
     * @param errorMsg IPC处理失败的图片信息
     * @return errorMsg转成的ImageBO
     * 
     * */
    public static BcsImage transToMallImageBo(ImageBOR imageBor) {
        BcsImage bo = new BcsImage();

        try {
            String callerMsg = imageBor.getCaller_msg();
            Long imageId = Long.valueOf(getMallImageId(callerMsg));

            bo.setId(imageId);
            bo.setImageurl(imageBor.getImageSource().getImage_source_url());
            bo.setImagebcsurl(imageBor.getImageSource().getImage_bcs_url());
            bo.setBcsStatus(BcsCspuImageStatus.DOWNLOAD_TO_LOCAL.getId());
            bo.setUpdatetime(new Date());
            bo.setErrormessage("");
            bo.setHeight(imageBor.getImageSource().getHeight());
            bo.setWidth(imageBor.getImageSource().getWidth());
            bo.setContentMd5(imageBor.getImageSource().getContent_md5());

            String gipsImageJsonStr = JSON.toJSONString(imageBor.getImageGipsTn());
            bo.setGipsImage(gipsImageJsonStr);

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToMallImageBo: ", e);
            return null;
        }
    }
    
    /**
     * 将 ErrorMsg 转成 mall image bo对象存储
     * 
     * @param errorMsg IPC处理失败的图片信息
     * @return errorMsg转成的ImageBO
     * 
     * */
    public static BcsImage transToMallImageBo(ErrorMessage errorMsg) {
        BcsImage bo = new BcsImage();

        try {
            String callerMsg = errorMsg.getCaller_msg();
            Long imageId = Long.valueOf(getMallImageId(callerMsg));
            
            bo.setId(imageId);
            bo.setImageurl(errorMsg.getOrginUrl());
            bo.setImagebcsurl("");
            bo.setBcsStatus(BcsImageStatus.DOWNLOAD_ERROR.getId());
            bo.setUpdatetime(new Date());
            bo.setErrormessage(errorMsg.getErrorReason() == null ? "" : errorMsg.getErrorReason());
            bo.setHeight(0);
            bo.setWidth(0);
            bo.setContentMd5("");
            bo.setGipsImage("");

            return bo;
        } catch (Exception e) {
            LOG.error("Exception in transToMallImageBo: ", e);
            return null;
        }
    }

}
