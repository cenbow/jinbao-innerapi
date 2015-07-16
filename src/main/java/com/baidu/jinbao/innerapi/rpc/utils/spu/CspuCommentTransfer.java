package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuCommentDto;
import com.baidu.jinbao.innerapi.spu.bo.CspuComment;

public class CspuCommentTransfer {
    private static final Logger LOG = Logger.getLogger(CspuCommentTransfer.class);

    public static CspuCommentDto transBoToDTo(CspuComment cspuCommentBo) {
        if (cspuCommentBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CspuCommentDto dto = new CspuCommentDto();
        dto.setId(cspuCommentBo.getId());
        dto.setCspuid(cspuCommentBo.getCspuid());
        dto.setReviewcount(cspuCommentBo.getReviewcount());
        dto.setGoodcount(cspuCommentBo.getGoodcount());
        dto.setMedcount(cspuCommentBo.getMedcount());
        dto.setBadcount(cspuCommentBo.getBadcount());
        dto.setTags(cspuCommentBo.getTags());
        dto.setScore(cspuCommentBo.getScore());
        if (cspuCommentBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(cspuCommentBo.getAddtime()));
        }
        if (cspuCommentBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(cspuCommentBo.getUpdatetime()));
        }
        return dto;
    }

    public static CspuComment transDtoToBo(CspuCommentDto cspuCommentDto, boolean setDefaultValue) {
        if (cspuCommentDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CspuComment cspuCommentBo = new CspuComment();
            cspuCommentBo.setId(cspuCommentDto.getId());
            cspuCommentBo.setCspuid(cspuCommentDto.getCspuid());
            if (setDefaultValue && (cspuCommentDto.getReviewcount() == null)) {
                cspuCommentBo.setReviewcount(0);
            } else {
                cspuCommentBo.setReviewcount(cspuCommentDto.getReviewcount());
            }
            if (setDefaultValue && (cspuCommentDto.getGoodcount() == null)) {
                cspuCommentBo.setGoodcount(0);
            } else {
                cspuCommentBo.setGoodcount(cspuCommentDto.getGoodcount());
            }
            if (setDefaultValue && (cspuCommentDto.getMedcount() == null)) {
                cspuCommentBo.setMedcount(0);
            } else {
                cspuCommentBo.setMedcount(cspuCommentDto.getMedcount());
            }
            if (setDefaultValue && (cspuCommentBo.getBadcount() == null)) {
                cspuCommentBo.setBadcount(0);
            } else {
                cspuCommentBo.setBadcount(cspuCommentDto.getBadcount());
            }
            if (setDefaultValue && (cspuCommentDto.getTags() == null)) {
                cspuCommentBo.setTags("");
            } else {
                cspuCommentBo.setTags(cspuCommentDto.getTags());
            }
            if (setDefaultValue && (cspuCommentDto.getScore() == null)) {
                cspuCommentBo.setScore(new Double(0));
            } else {
                cspuCommentBo.setScore(cspuCommentDto.getScore());
            }
            if (cspuCommentDto.getAddtime() != null) {
                cspuCommentBo.setAddtime(dateFormat.parse(cspuCommentDto.getAddtime()));
            } else if (setDefaultValue) {
                cspuCommentBo.setAddtime(new Date());
            }
            if (cspuCommentDto.getUpdatetime() != null) {
                cspuCommentBo.setUpdatetime(dateFormat.parse(cspuCommentDto.getUpdatetime()));
            } else {
                cspuCommentBo.setUpdatetime(new Date());
            }
            return cspuCommentBo;
        } catch (Exception e) {
            LOG.error("Exception in CspuCommentTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(CspuCommentDto cspuCommentDto) {
        if (cspuCommentDto == null) {
            return true;
        }
        if (cspuCommentDto.getCspuid() == null) {
            return false;
        }
        return true;
    }
}
