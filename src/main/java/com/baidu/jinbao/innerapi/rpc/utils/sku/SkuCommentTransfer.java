package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuCommentDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;

public class SkuCommentTransfer {
    private static final Logger LOG = Logger.getLogger(SkuCommentTransfer.class);

    public static SkuCommentDto transBoToDto(SkuComment skuCommentBo) {
        if (skuCommentBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuCommentDto dto = new SkuCommentDto();
        dto.setId(skuCommentBo.getId());
        dto.setSkuInnerid(skuCommentBo.getSkuInnerid());
        dto.setSkuid(skuCommentBo.getSkuid());
        dto.setReviewcount(skuCommentBo.getReviewcount());
        dto.setGoodcount(skuCommentBo.getGoodcount());
        dto.setMedcount(skuCommentBo.getMedcount());
        dto.setBadcount(skuCommentBo.getBadcount());
        dto.setTags(skuCommentBo.getTags());
        dto.setScore(skuCommentBo.getScore());
        if (skuCommentBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuCommentBo.getAddtime()));
        }
        if (skuCommentBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuCommentBo.getUpdatetime()));
        }
        return dto;
    }

    public static SkuComment transDtoToBo(SkuCommentDto skuCommentDto, boolean setDefaultValue) {
        if (skuCommentDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuComment skuCommentBo = new SkuComment();
            skuCommentBo.setId(skuCommentDto.getId());
            skuCommentBo.setSkuInnerid(skuCommentDto.getSkuInnerid());
            skuCommentBo.setSkuid(skuCommentDto.getSkuid());
            skuCommentBo.setReviewcount(skuCommentDto.getReviewcount());
            skuCommentBo.setGoodcount(skuCommentDto.getGoodcount());
            skuCommentBo.setMedcount(skuCommentDto.getMedcount());
            skuCommentBo.setBadcount(skuCommentDto.getBadcount());
            if (setDefaultValue && (skuCommentDto.getTags() == null)) {
                skuCommentBo.setTags("");
            } else {
                skuCommentBo.setTags(skuCommentDto.getTags());
            }
            skuCommentBo.setScore(skuCommentDto.getScore());
            if (skuCommentDto.getAddtime() != null) {
                skuCommentBo.setAddtime(dateFormat.parse(skuCommentDto.getAddtime()));
            } else if (setDefaultValue) {
                skuCommentBo.setAddtime(new Date());
            }
            if (skuCommentDto.getUpdatetime() != null) {
                skuCommentBo.setUpdatetime(dateFormat.parse(skuCommentDto.getUpdatetime()));
            } else {
                skuCommentBo.setUpdatetime(new Date());
            }
            return skuCommentBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuCommentTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuCommentDto skuCommentDto) {
        if (skuCommentDto == null) {
            return true;
        }
        if ((skuCommentDto.getSkuid() == null) || (skuCommentDto.getReviewcount() == null)
                || (skuCommentDto.getGoodcount() == null) || (skuCommentDto.getMedcount() == null)
                || (skuCommentDto.getBadcount() == null) || (skuCommentDto.getScore() == null)) {
            return false;
        }
        return true;
    }
}
