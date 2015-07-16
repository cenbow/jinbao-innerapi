package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuDescriptionDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;

public class SkuDescriptionTransfer {
    private static final Logger LOG = Logger.getLogger(SkuDescriptionTransfer.class);

    public static SkuDescriptionDto transBoToDto(SkuDescription skuDescriptionBo) {
        if (skuDescriptionBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuDescriptionDto dto = new SkuDescriptionDto();
        dto.setId(skuDescriptionBo.getId());
        dto.setMerchantid(skuDescriptionBo.getMerchantid());
        dto.setSkuInnerid(skuDescriptionBo.getSkuInnerid());
        dto.setSkuid(skuDescriptionBo.getSkuid());
        dto.setSkuDescOri(skuDescriptionBo.getSkuDescOri());
        dto.setPdHash(skuDescriptionBo.getPdHash());
        dto.setSkuDesc(skuDescriptionBo.getSkuDesc());
        if (skuDescriptionBo.getPdStatus() != null) {
            dto.setPdStatus(skuDescriptionBo.getPdStatus().intValue());
        }
        dto.setArea(skuDescriptionBo.getArea());
        dto.setWordCount(skuDescriptionBo.getWordCount());
        if (skuDescriptionBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuDescriptionBo.getAddtime()));
        }
        if (skuDescriptionBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuDescriptionBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuDescription transDtoToBo(SkuDescriptionDto skuDescriptionDto, boolean setDefaultValue) {
        if (skuDescriptionDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuDescription skuDescriptionBo = new SkuDescription();
            skuDescriptionBo.setId(skuDescriptionDto.getId());
            skuDescriptionBo.setSkuInnerid(skuDescriptionDto.getSkuInnerid());
            skuDescriptionBo.setMerchantid(skuDescriptionDto.getMerchantid());
            skuDescriptionBo.setSkuid(skuDescriptionDto.getSkuid());
            skuDescriptionBo.setSkuDescOri(skuDescriptionDto.getSkuDescOri());
            if (skuDescriptionBo.getSkuDescOri() != null) {
                skuDescriptionBo.setPdHash(MD5Util.getMD5Value(new String(skuDescriptionBo.getSkuDescOri(), "UTF-8")));
            }
            skuDescriptionBo.setSkuDesc(skuDescriptionDto.getSkuDesc());
            if (skuDescriptionDto.getPdStatus() != null) {
                skuDescriptionBo.setPdStatus(skuDescriptionDto.getPdStatus().byteValue());
            } else if (setDefaultValue) {
                skuDescriptionBo.setPdStatus(new Integer(0).byteValue());
            }
            if (setDefaultValue && (skuDescriptionDto.getArea() == null)) {
                skuDescriptionBo.setArea(0);
            } else {
                skuDescriptionDto.setArea(skuDescriptionDto.getArea());
            }
            if (setDefaultValue && (skuDescriptionDto.getWordCount() == null)) {
                skuDescriptionBo.setWordCount(0);
            } else {
                skuDescriptionBo.setWordCount(skuDescriptionDto.getWordCount());
            }
            if (skuDescriptionDto.getAddtime() != null) {
                skuDescriptionBo.setAddtime(dateFormat.parse(skuDescriptionDto.getAddtime()));
            } else if (setDefaultValue) {
                skuDescriptionBo.setAddtime(new Date());
            }
            if (skuDescriptionDto.getUpdatetime() != null) {
                skuDescriptionBo.setUpdatetime(dateFormat.parse(skuDescriptionDto.getUpdatetime()));
            } else {
                skuDescriptionBo.setUpdatetime(new Date());
            }
            return skuDescriptionBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuDescriptionTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuDescriptionDto skuDescriptionDto) {
        if (skuDescriptionDto == null) {
            return true;
        }
        if ((skuDescriptionDto.getSkuid() == null) || (skuDescriptionDto.getMerchantid() == null)
                || (skuDescriptionDto.getSkuDescOri() == null) || (skuDescriptionDto.getSkuDesc() == null)) {
            return false;
        }
        return true;
    }
}
