package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuAttributeDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;

public class SkuAttributeTransfer {
    private static final Logger LOG = Logger.getLogger(SkuAttributeTransfer.class);

    public static SkuAttributeDto transBoToDto(SkuAttribute skuAttributeBo) {
        if (skuAttributeBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuAttributeDto dto = new SkuAttributeDto();
        dto.setId(skuAttributeBo.getId());
        dto.setSkuInnerid(skuAttributeBo.getSkuInnerid());
        dto.setSkuid(skuAttributeBo.getSkuid());
        dto.setMerchantid(skuAttributeBo.getMerchantid());
        dto.setPropertyId(skuAttributeBo.getPropertyId());
        dto.setPropertyValues(skuAttributeBo.getPropertyValues());
        dto.setPropertyHash(skuAttributeBo.getPropertyHash());
        if (skuAttributeBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuAttributeBo.getAddtime()));
        }
        if (skuAttributeBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuAttributeBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuAttribute transDtoToBo(SkuAttributeDto skuAttributeDto, boolean setDefaultValue) {
        if (skuAttributeDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuAttribute skuAttributeBo = new SkuAttribute();
            skuAttributeBo.setId(skuAttributeDto.getId());
            skuAttributeBo.setSkuInnerid(skuAttributeDto.getSkuInnerid());
            skuAttributeBo.setSkuid(skuAttributeDto.getSkuid());
            skuAttributeBo.setMerchantid(skuAttributeDto.getMerchantid());
            if (setDefaultValue && (skuAttributeDto.getPropertyId() == null)) {
                skuAttributeBo.setPropertyId("");
            } else {
                skuAttributeBo.setPropertyId(skuAttributeDto.getPropertyId());
            }
            if (setDefaultValue && (skuAttributeDto.getPropertyValues() == null)) {
                skuAttributeBo.setPropertyValues("");
            } else {
                skuAttributeBo.setPropertyValues(skuAttributeDto.getPropertyValues());
            }
            if ((skuAttributeBo.getPropertyId() != null) && (skuAttributeBo.getPropertyValues() != null)) {
                skuAttributeBo.setPropertyHash(MD5Util.getMD5Value(skuAttributeBo.getPropertyId()
                        + skuAttributeBo.getPropertyValues()));
            }
            if (skuAttributeDto.getAddtime() != null) {
                skuAttributeBo.setAddtime(dateFormat.parse(skuAttributeDto.getAddtime()));
            } else if (setDefaultValue) {
                skuAttributeBo.setAddtime(new Date());
            }
            if (skuAttributeDto.getUpdatetime() != null) {
                skuAttributeBo.setUpdatetime(dateFormat.parse(skuAttributeDto.getUpdatetime()));
            } else {
                skuAttributeBo.setUpdatetime(new Date());
            }
            return skuAttributeBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuAttributeTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuAttributeDto skuAttributeDto) {
        if (skuAttributeDto == null) {
            return true;
        }
        if ((skuAttributeDto.getSkuid() == null) || (skuAttributeDto.getMerchantid() == null)) {
            return false;
        }
        return true;
    }
}
