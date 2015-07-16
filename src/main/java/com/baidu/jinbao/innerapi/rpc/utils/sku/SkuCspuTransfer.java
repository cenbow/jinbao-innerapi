package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCspuDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;

public class SkuCspuTransfer {
    private static final Logger LOG = Logger.getLogger(SkuCspuTransfer.class);

    public static SkuCspuDto tranBoToDto(SkuCspu skuCspuBo) {
        if (skuCspuBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuCspuDto dto = new SkuCspuDto();
        dto.setId(skuCspuBo.getId());
        dto.setSkuInnerid(skuCspuBo.getSkuInnerid());
        dto.setSkuid(skuCspuBo.getSkuid());
        dto.setCspuid(skuCspuBo.getCspuid());
        if (skuCspuBo.getType() != null) {
            dto.setType(skuCspuBo.getType().intValue());
        }
        dto.setConfidence(skuCspuBo.getConfidence());
        if (skuCspuBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuCspuBo.getAddtime()));
        }
        if (skuCspuBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuCspuBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuCspu transDtoToBo(SkuCspuDto skuCspuDto, boolean setDefaultValue) {
        if (skuCspuDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuCspu skuCspuBo = new SkuCspu();
            skuCspuBo.setId(skuCspuDto.getId());
            skuCspuBo.setSkuInnerid(skuCspuDto.getSkuInnerid());
            skuCspuBo.setSkuid(skuCspuDto.getSkuid());
            skuCspuBo.setCspuid(skuCspuDto.getCspuid());
            if (skuCspuDto.getType() != null) {
                skuCspuBo.setType(skuCspuDto.getType().byteValue());
            }
            if (setDefaultValue && (skuCspuDto.getConfidence() == null)) {
                skuCspuBo.setConfidence(new Float(0));
            } else {
                skuCspuBo.setConfidence(skuCspuDto.getConfidence());
            }
            if (skuCspuDto.getAddtime() != null) {
                skuCspuBo.setAddtime(dateFormat.parse(skuCspuDto.getAddtime()));
            } else if (setDefaultValue) {
                skuCspuBo.setAddtime(new Date());
            }
            if (skuCspuDto.getUpdatetime() != null) {
                skuCspuBo.setUpdatetime(dateFormat.parse(skuCspuDto.getUpdatetime()));
            } else {
                skuCspuBo.setUpdatetime(new Date());
            }
            return skuCspuBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuCspuTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuCspuDto skuCspuDto) {
        if (skuCspuDto == null) {
            return true;
        }
        if ((skuCspuDto.getSkuid() == null) || (skuCspuDto.getCspuid() == null) || (skuCspuDto.getType() == null)) {
            return false;
        }
        return true;
    }
}
