package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuLevelDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;

public class SkuLevelTransfer {
    private static final Logger LOG = Logger.getLogger(SkuLevelTransfer.class);

    public static SkuLevelDto transBoToDto(SkuLevel skuLevelBo) {
        if (skuLevelBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuLevelDto dto = new SkuLevelDto();
        dto.setId(skuLevelBo.getId());
        dto.setSkuInnerid(skuLevelBo.getSkuInnerid());
        dto.setSkuid(skuLevelBo.getSkuid());
        dto.setLevel(skuLevelBo.getLevel());
        if (skuLevelBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuLevelBo.getAddtime()));
        }
        if (skuLevelBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuLevelBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuLevel transDtoToBo(SkuLevelDto skuLevelDto, boolean setDefaultValue) {
        if (skuLevelDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuLevel skuLevelBo = new SkuLevel();
            skuLevelBo.setId(skuLevelDto.getId());
            skuLevelBo.setSkuInnerid(skuLevelDto.getSkuInnerid());
            skuLevelBo.setSkuid(skuLevelDto.getSkuid());
            if (setDefaultValue && (skuLevelDto.getLevel() == null)) {
                skuLevelBo.setLevel(0);
            } else {
                skuLevelBo.setLevel(skuLevelDto.getLevel());
            }
            if (skuLevelDto.getAddtime() != null) {
                skuLevelBo.setAddtime(dateFormat.parse(skuLevelDto.getAddtime()));
            } else if (setDefaultValue) {
                skuLevelBo.setAddtime(new Date());
            }
            if (skuLevelDto.getUpdatetime() != null) {
                skuLevelBo.setUpdatetime(dateFormat.parse(skuLevelDto.getUpdatetime()));
            } else {
                skuLevelBo.setUpdatetime(new Date());
            }
            return skuLevelBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuLevelTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuLevelDto skuLevelDto) {
        if (skuLevelDto == null) {
            return true;
        }
        if (skuLevelDto.getSkuid() == null) {
            return false;
        }
        return true;
    }
}
