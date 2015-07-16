package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuCommentDto;
import com.baidu.jinbao.innerapi.spu.bo.SpuComment;

public class SpuCommentTransfer {
    private static final Logger LOG = Logger.getLogger(SpuCommentTransfer.class);

    public static SpuCommentDto transBoToDto(SpuComment spuCommentBo) {
        if (spuCommentBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpuCommentDto dto = new SpuCommentDto();
        dto.setId(spuCommentBo.getId());
        dto.setSpuid(spuCommentBo.getSpuid());
        dto.setReviewcount(spuCommentBo.getReviewcount());
        dto.setGoodcount(spuCommentBo.getGoodcount());
        dto.setMedcount(spuCommentBo.getMedcount());
        dto.setBadcount(spuCommentBo.getBadcount());
        dto.setTags(spuCommentBo.getTags());
        dto.setScore(spuCommentBo.getScore());
        if (spuCommentBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(spuCommentBo.getAddtime()));
        }
        if (spuCommentBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(spuCommentBo.getUpdatetime()));
        }
        return dto;
    }

    public static SpuComment transDtoToBo(SpuCommentDto spuCommentDto, boolean setDefaultValue) {
        if (spuCommentDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SpuComment spuCommentBo = new SpuComment();
            spuCommentBo.setId(spuCommentDto.getId());
            spuCommentBo.setSpuid(spuCommentDto.getSpuid());
            if (setDefaultValue && (spuCommentDto.getReviewcount() == null)) {
                spuCommentBo.setReviewcount(0);
            } else {
                spuCommentBo.setReviewcount(spuCommentDto.getReviewcount());
            }
            if (setDefaultValue && (spuCommentDto.getGoodcount() == null)) {
                spuCommentBo.setGoodcount(0);
            } else {
                spuCommentBo.setGoodcount(spuCommentDto.getGoodcount());
            }
            if (setDefaultValue && (spuCommentDto.getMedcount() == null)) {
                spuCommentBo.setMedcount(0);
            } else {
                spuCommentBo.setMedcount(spuCommentDto.getMedcount());
            }
            if (setDefaultValue && (spuCommentDto.getBadcount() == null)) {
                spuCommentBo.setBadcount(0);
            } else {
                spuCommentBo.setBadcount(spuCommentDto.getBadcount());
            }
            if (setDefaultValue && (spuCommentDto.getTags() == null)) {
                spuCommentBo.setTags("");
            } else {
                spuCommentBo.setTags(spuCommentDto.getTags());
            }
            if (setDefaultValue && (spuCommentDto.getScore() == null)) {
                spuCommentBo.setScore(new Double(0));
            } else {
                spuCommentBo.setScore(spuCommentDto.getScore());
            }
            if (spuCommentDto.getAddtime() != null) {
                spuCommentBo.setAddtime(dateFormat.parse(spuCommentDto.getAddtime()));
            } else if (setDefaultValue) {
                spuCommentBo.setAddtime(new Date());
            }
            if (spuCommentDto.getUpdatetime() != null) {
                spuCommentBo.setUpdatetime(dateFormat.parse(spuCommentDto.getUpdatetime()));
            } else {
                spuCommentBo.setUpdatetime(new Date());
            }
            return spuCommentBo;
        } catch (Exception e) {
            LOG.error("Exception in SpuCommentTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SpuCommentDto spuCommentDto) {
        if (spuCommentDto == null) {
            return true;
        }
        if (spuCommentDto.getSpuid() == null) {
            return false;
        }
        return true;
    }
}
