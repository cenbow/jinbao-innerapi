package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.BcsCspuImageDto;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;

public class BcsCspuImageTransfer {
    private static final Logger LOG = Logger.getLogger(BcsCspuImageTransfer.class);

    public static BcsCspuImageDto transBoToDto(BcsCspuImage bcsCspuImageBo) {
        if (bcsCspuImageBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BcsCspuImageDto dto = new BcsCspuImageDto();
        dto.setId(bcsCspuImageBo.getId());
        dto.setCspuid(bcsCspuImageBo.getCspuid());
        dto.setImageUrl(bcsCspuImageBo.getImageUrl());
        dto.setImageBcsUrl(bcsCspuImageBo.getImageBcsUrl());
        if (bcsCspuImageBo.getStatus() != null) {
            dto.setStatus(bcsCspuImageBo.getStatus().intValue());
        }
        if (bcsCspuImageBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(bcsCspuImageBo.getAddtime()));
        }
        if (bcsCspuImageBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(bcsCspuImageBo.getUpdatetime()));
        }
        dto.setErrormessage(bcsCspuImageBo.getErrormessage());
        dto.setWidth(bcsCspuImageBo.getWidth());
        dto.setHeight(bcsCspuImageBo.getHeight());
        dto.setContentMd5(bcsCspuImageBo.getContentMd5());
        dto.setSequence(bcsCspuImageBo.getSequence());
        dto.setGipsImage(bcsCspuImageBo.getGipsImage());
        if (bcsCspuImageBo.getType() != null) {
            dto.setImageType(bcsCspuImageBo.getType().intValue());
        }
        return dto;
    }

    public static BcsCspuImage transDtoToBo(BcsCspuImageDto bcsCspuImageDto, boolean setDefaultValue) {
        if (bcsCspuImageDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BcsCspuImage bcsCspuImageBo = new BcsCspuImage();
            bcsCspuImageBo.setId(bcsCspuImageDto.getId());
            bcsCspuImageBo.setCspuid(bcsCspuImageDto.getCspuid());
            if (setDefaultValue && (bcsCspuImageDto.getImageUrl() == null)) {
                bcsCspuImageBo.setImageUrl("");
            } else {
                bcsCspuImageBo.setImageUrl(bcsCspuImageDto.getImageUrl());
            }
            if (setDefaultValue && (bcsCspuImageDto.getImageBcsUrl() == null)) {
                bcsCspuImageBo.setImageBcsUrl("");
            } else {
                bcsCspuImageBo.setImageBcsUrl(bcsCspuImageDto.getImageBcsUrl());
            }
            if (bcsCspuImageDto.getStatus() != null) {
                bcsCspuImageBo.setStatus(bcsCspuImageDto.getStatus().byteValue());
            } else if (setDefaultValue) {
                bcsCspuImageBo.setStatus(new Integer(3).byteValue());
            }
            if (bcsCspuImageDto.getAddtime() != null) {
                bcsCspuImageBo.setAddtime(dateFormat.parse(bcsCspuImageDto.getAddtime()));
            } else if (setDefaultValue) {
                bcsCspuImageBo.setAddtime(new Date());
            }
            if (bcsCspuImageDto.getUpdatetime() != null) {
                bcsCspuImageBo.setUpdatetime(dateFormat.parse(bcsCspuImageDto.getUpdatetime()));
            } else {
                bcsCspuImageBo.setUpdatetime(new Date());
            }
            if (setDefaultValue && (bcsCspuImageDto.getErrormessage() == null)) {
                bcsCspuImageBo.setErrormessage("");
            } else {
                bcsCspuImageBo.setErrormessage(bcsCspuImageDto.getErrormessage());
            }
            if (setDefaultValue && (bcsCspuImageDto.getWidth() == null)) {
                bcsCspuImageBo.setWidth(0);
            } else {
                bcsCspuImageBo.setWidth(bcsCspuImageDto.getWidth());
            }

            if (setDefaultValue && (bcsCspuImageDto.getHeight() == null)) {
                bcsCspuImageBo.setHeight(0);
            } else {
                bcsCspuImageBo.setHeight(bcsCspuImageDto.getHeight());
            }
            if (setDefaultValue && (bcsCspuImageDto.getContentMd5() == null)) {
                bcsCspuImageBo.setContentMd5("");
            } else {
                bcsCspuImageBo.setContentMd5(bcsCspuImageDto.getContentMd5());
            }
            if (setDefaultValue && (bcsCspuImageDto.getSequence() == null)) {
                bcsCspuImageBo.setSequence(1);
            } else {
                bcsCspuImageBo.setSequence(bcsCspuImageDto.getSequence());
            }
            if (setDefaultValue && (bcsCspuImageDto.getGipsImage() == null)) {
                bcsCspuImageBo.setGipsImage("");
            } else {
                bcsCspuImageBo.setGipsImage(bcsCspuImageDto.getGipsImage());
            }
            if (bcsCspuImageDto.getImageType() != null) {
                bcsCspuImageBo.setType(bcsCspuImageDto.getImageType().byteValue());
            }
            return bcsCspuImageBo;
        } catch (Exception e) {
            LOG.error("Exception in BcsCspuImageTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(BcsCspuImageDto bcsCspuImageDto) {
        if (bcsCspuImageDto == null) {
            return true;
        }
        if ((bcsCspuImageDto.getCspuid() == null) || (bcsCspuImageDto.getImageType() == null)) {
            return false;
        }
        return true;
    }
}
