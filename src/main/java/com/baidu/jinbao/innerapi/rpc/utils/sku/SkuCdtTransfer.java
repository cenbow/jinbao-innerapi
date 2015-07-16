package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCdtDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;

public class SkuCdtTransfer {
    private static final Logger LOG = Logger.getLogger(SkuCdtTransfer.class);

    public static SkuCdtDto transBoToDto(SkuCdt skuCdtBo) {
        if (skuCdtBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuCdtDto dto = new SkuCdtDto();
        dto.setId(skuCdtBo.getId());
        dto.setSkuInnerid(skuCdtBo.getSkuInnerid());
        dto.setSkuid(skuCdtBo.getSkuid());
        dto.setLeafcategoryid(skuCdtBo.getLeafcategoryid());
        dto.setClassificationtype(skuCdtBo.getClassificationtype() & 0xff);
        dto.setConfidence(skuCdtBo.getConfidence());
        if (skuCdtBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuCdtBo.getAddtime()));
        }
        if (skuCdtBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuCdtBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuCdt transDtoToBo(SkuCdtDto skuCdtDto, boolean setDefaultValue) {
        if (skuCdtDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuCdt skuCdtBo = new SkuCdt();
            skuCdtBo.setId(skuCdtDto.getId());
            skuCdtBo.setSkuInnerid(skuCdtDto.getSkuInnerid());
            skuCdtBo.setSkuid(skuCdtDto.getSkuid());
            skuCdtBo.setLeafcategoryid(skuCdtDto.getLeafcategoryid());
            skuCdtBo.setClassificationtype(Byte.valueOf(skuCdtDto.getClassificationtype().toString()));
            if (setDefaultValue && (skuCdtDto.getConfidence() == null)) {
                skuCdtBo.setConfidence(new Float(0));
            } else {
                skuCdtBo.setConfidence(skuCdtDto.getConfidence());
            }
            if (skuCdtDto.getAddtime() != null) {
                skuCdtBo.setAddtime(dateFormat.parse(skuCdtDto.getAddtime()));
            } else if (setDefaultValue) {
                skuCdtBo.setAddtime(new Date());
            }
            if (skuCdtDto.getUpdatetime() != null) {
                skuCdtBo.setUpdatetime(dateFormat.parse(skuCdtDto.getUpdatetime()));
            } else {
                skuCdtBo.setUpdatetime(new Date());
            }
            return skuCdtBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuCdtTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuCdtDto skuCdtDto) {
        if (skuCdtDto == null) {
            return true;
        }
        if ((skuCdtDto.getSkuid() == null) || (skuCdtDto.getLeafcategoryid() == null)
                || (skuCdtDto.getClassificationtype() == null)) {
            return false;
        }
        return true;
    }
}
