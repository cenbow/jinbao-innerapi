package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.BcsSkuImageDto;
import com.baidu.jinbao.innerapi.sku.bo.BcsSkuImage;

public class BcsSkuImageTransfer {
    private static final Logger LOG = Logger.getLogger(BcsSkuImageTransfer.class);

    public static BcsSkuImageDto transBoToDto(BcsSkuImage bcsSkuImageBo) {
        if (bcsSkuImageBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BcsSkuImageDto dto = new BcsSkuImageDto();
        dto.setId(bcsSkuImageBo.getId());
        dto.setSkuInnerid(bcsSkuImageBo.getSkuInnerid());
        dto.setSkuid(bcsSkuImageBo.getSkuid());
        dto.setImageUrl(bcsSkuImageBo.getImageUrl());
        dto.setImageBcsUrl(bcsSkuImageBo.getImageBcsUrl());
        if (bcsSkuImageBo.getStatus() != null) {
            dto.setStatus(bcsSkuImageBo.getStatus().intValue());
        }
        if (bcsSkuImageBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(bcsSkuImageBo.getAddtime()));
        }
        if (bcsSkuImageBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(bcsSkuImageBo.getUpdatetime()));
        }
        dto.setErrormessage(bcsSkuImageBo.getErrormessage());
        dto.setWidth(bcsSkuImageBo.getWidth());
        dto.setHeight(bcsSkuImageBo.getHeight());
        dto.setContentMd5(bcsSkuImageBo.getContentMd5());
        dto.setSequence(bcsSkuImageBo.getSequence());
        dto.setGipsImage(bcsSkuImageBo.getGipsImage());
        if (bcsSkuImageBo.getType() != null) {
            dto.setType(bcsSkuImageBo.getType().intValue());
        }
        return dto;
    }

    public static BcsSkuImage transDtoToBo(BcsSkuImageDto bcsSkuImageDto, boolean setDefaultValue) {
        if (bcsSkuImageDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BcsSkuImage bcsSkuImageBo = new BcsSkuImage();
            bcsSkuImageBo.setId(bcsSkuImageDto.getId());
            bcsSkuImageBo.setSkuInnerid(bcsSkuImageDto.getSkuInnerid());
            bcsSkuImageBo.setSkuid(bcsSkuImageDto.getSkuid());
            if (setDefaultValue && (bcsSkuImageDto.getImageUrl() == null)) {
                bcsSkuImageBo.setImageUrl("");
            } else {
                bcsSkuImageBo.setImageUrl(bcsSkuImageDto.getImageUrl());
            }
            if (setDefaultValue && (bcsSkuImageDto.getImageBcsUrl() == null)) {
                bcsSkuImageBo.setImageBcsUrl("");
            } else {
                bcsSkuImageBo.setImageBcsUrl(bcsSkuImageDto.getImageBcsUrl());
            }
            if (bcsSkuImageDto.getStatus() != null) {
                bcsSkuImageBo.setStatus(bcsSkuImageDto.getStatus().byteValue());
            } else if (setDefaultValue) {
                bcsSkuImageBo.setStatus(new Integer(3).byteValue());
            }
            if (bcsSkuImageDto.getAddtime() != null) {
                bcsSkuImageBo.setAddtime(dateFormat.parse(bcsSkuImageDto.getAddtime()));
            } else if (setDefaultValue) {
                bcsSkuImageBo.setAddtime(new Date());
            }
            if (bcsSkuImageDto.getUpdatetime() != null) {
                bcsSkuImageBo.setUpdatetime(dateFormat.parse(bcsSkuImageDto.getUpdatetime()));
            } else {
                bcsSkuImageBo.setUpdatetime(new Date());
            }
            if (setDefaultValue && (bcsSkuImageDto.getErrormessage() == null)) {
                bcsSkuImageBo.setErrormessage("");
            } else {
                bcsSkuImageBo.setErrormessage(bcsSkuImageDto.getErrormessage());
            }
            if (setDefaultValue && (bcsSkuImageDto.getWidth() == null)) {
                bcsSkuImageBo.setWidth(0);
            } else {
                bcsSkuImageBo.setWidth(bcsSkuImageDto.getWidth());
            }
            if (setDefaultValue && (bcsSkuImageDto.getHeight() == null)) {
                bcsSkuImageBo.setHeight(0);
            } else {
                bcsSkuImageBo.setHeight(bcsSkuImageDto.getHeight());
            }
            if (setDefaultValue && (bcsSkuImageDto.getContentMd5() == null)) {
                bcsSkuImageBo.setContentMd5("");
            } else {
                bcsSkuImageBo.setContentMd5(bcsSkuImageDto.getContentMd5());
            }
            if (setDefaultValue && (bcsSkuImageDto.getSequence() == null)) {
                bcsSkuImageBo.setSequence(1);
            } else {
                bcsSkuImageBo.setSequence(bcsSkuImageDto.getSequence());
            }
            bcsSkuImageBo.setGipsImage(bcsSkuImageDto.getGipsImage());
            if (bcsSkuImageDto.getType() != null) {
                bcsSkuImageBo.setType(bcsSkuImageDto.getType().byteValue());
            }
            return bcsSkuImageBo;
        } catch (Exception e) {
            LOG.error("Exception in BcsSkuImageTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(BcsSkuImageDto bcsSkuImageDto) {
        if (bcsSkuImageDto == null) {
            return true;
        }
        if ((bcsSkuImageDto.getSkuid() == null) || (bcsSkuImageDto.getGipsImage() == null)
                || (bcsSkuImageDto.getType() == null)) {
            return false;
        }
        return true;
    }
}
