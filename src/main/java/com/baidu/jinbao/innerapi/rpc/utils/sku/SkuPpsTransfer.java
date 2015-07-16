package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuPpsDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;

public class SkuPpsTransfer {
    private static final Logger LOG = Logger.getLogger(SkuPpsTransfer.class);

    public static SkuPpsDto transBoToDto(SkuPps skuPpsBo) {
        if (skuPpsBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuPpsDto dto = new SkuPpsDto();
        dto.setId(skuPpsBo.getId());
        dto.setMerchantid(skuPpsBo.getMerchantid());
        dto.setSkuInnerid(skuPpsBo.getSkuInnerid());
        dto.setSkuid(skuPpsBo.getSkuid());
        dto.setRegionid(skuPpsBo.getRegionid());
        if (skuPpsBo.getTerminal() != null) {
            dto.setTerminal(skuPpsBo.getTerminal().intValue());
        }
        dto.setPromotionInfo(Float.parseFloat(skuPpsBo.getPromotionInfo().toString()));
        dto.setPromotionPriceMachine(Float.parseFloat(skuPpsBo.getPromotionPriceMachine().toString()));
        dto.setPromotionPriceManual(Float.parseFloat(skuPpsBo.getPromotionPriceManual().toString()));
        dto.setOriginalprice(Float.parseFloat(skuPpsBo.getOriginalprice().toString()));
        dto.setDiscountprice(Float.parseFloat(skuPpsBo.getDiscountprice().toString()));
        if (skuPpsBo.getStock() != null) {
            dto.setStock(skuPpsBo.getStock().intValue());
        }
        if (skuPpsBo.getPostPay() != null) {
            dto.setPostPay(skuPpsBo.getPostPay().intValue());
        }
        if (skuPpsBo.getMUpdateTime() != null) {
            dto.setMUpdateTime(dateFormat.format(skuPpsBo.getMUpdateTime()));
        }
        if (skuPpsBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuPpsBo.getAddtime()));
        }
        if (skuPpsBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuPpsBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuPps transDtoToBo(SkuPpsDto skuPpsDto, boolean setDefaultValue) {
        if (skuPpsDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuPps skuPpsBo = new SkuPps();
            skuPpsBo.setId(skuPpsDto.getId());
            skuPpsBo.setMerchantid(skuPpsDto.getMerchantid());
            skuPpsBo.setSkuInnerid(skuPpsDto.getSkuInnerid());
            skuPpsBo.setSkuid(skuPpsDto.getSkuid());
            skuPpsBo.setRegionid(skuPpsDto.getRegionid());
            if (skuPpsDto.getTerminal() != null) {
                skuPpsBo.setTerminal(skuPpsDto.getTerminal().byteValue());
            }
            if (setDefaultValue && (skuPpsDto.getPromotionInfo() == null)) {
                skuPpsBo.setPromotionInfo("0");
            } else if (skuPpsDto.getPromotionInfo() != null) {
                skuPpsBo.setPromotionInfo(Float.toString(skuPpsDto.getPromotionInfo()));
            }
            if (setDefaultValue && (skuPpsDto.getPromotionPriceMachine() == null)) {
                skuPpsBo.setPromotionPriceMachine("0");
            } else if (skuPpsDto.getPromotionPriceMachine() != null) {
                skuPpsBo.setPromotionPriceMachine(Float.toString(skuPpsDto.getPromotionPriceMachine()));
            }
            if (setDefaultValue && (skuPpsDto.getPromotionPriceMachine() == null)) {
                skuPpsBo.setPromotionPriceMachine("0");
            } else if (skuPpsDto.getPromotionPriceMachine() != null) {
                skuPpsBo.setPromotionPriceMachine(Float.toString(skuPpsDto.getPromotionPriceMachine()));
            }
            if (setDefaultValue && (skuPpsDto.getPromotionPriceManual() == null)) {
                skuPpsBo.setPromotionPriceManual("0");
            } else if (skuPpsDto.getPromotionPriceManual() != null) {
                skuPpsBo.setPromotionPriceManual(Float.toString(skuPpsDto.getPromotionPriceManual()));
            }
            if (setDefaultValue && (skuPpsDto.getOriginalprice() == null)) {
                skuPpsBo.setOriginalprice("0.00");
            } else if (skuPpsDto.getOriginalprice() != null) {
                skuPpsBo.setOriginalprice(Float.toString(skuPpsDto.getOriginalprice()));
            }
            if (setDefaultValue && (skuPpsDto.getDiscountprice() == null)) {
                skuPpsBo.setDiscountprice("0.00");
            } else if (skuPpsDto.getDiscountprice() != null) {
                skuPpsBo.setDiscountprice(Float.toString(skuPpsDto.getDiscountprice()));
            }
            if (skuPpsDto.getStock() != null) {
                skuPpsBo.setStock(skuPpsDto.getStock().byteValue());
            } else if (setDefaultValue) {
                skuPpsBo.setStock(new Integer(0).byteValue());
            }
            if (skuPpsDto.getPostPay() != null) {
                skuPpsBo.setPostPay(skuPpsDto.getPostPay().byteValue());
            } else if (setDefaultValue) {
                skuPpsBo.setPostPay(new Integer(0).byteValue());
            }
            if (skuPpsDto.getMUpdateTime() != null) {
                skuPpsBo.setMUpdateTime(dateFormat.parse(skuPpsDto.getMUpdateTime()));
            }
            if (skuPpsDto.getAddtime() != null) {
                skuPpsBo.setAddtime(dateFormat.parse(skuPpsDto.getAddtime()));
            } else if (setDefaultValue) {
                skuPpsBo.setAddtime(new Date());
            }
            if (skuPpsDto.getUpdatetime() != null) {
                skuPpsBo.setUpdatetime(dateFormat.parse(skuPpsDto.getUpdatetime()));
            } else {
                skuPpsBo.setUpdatetime(new Date());
            }
            return skuPpsBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuPpsTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuPpsDto skuPpsDto) {
        if (skuPpsDto == null) {
            return true;
        }
        if ((skuPpsDto.getSkuid() == null) || (skuPpsDto.getMerchantid() == null) || (skuPpsDto.getRegionid() == null)
                || (skuPpsDto.getTerminal() == null) || (skuPpsDto.getMUpdateTime() == null)) {
            return false;
        }
        return true;
    }
}
