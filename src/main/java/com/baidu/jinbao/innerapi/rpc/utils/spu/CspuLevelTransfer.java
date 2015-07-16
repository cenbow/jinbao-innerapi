package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuLevelDto;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;

public class CspuLevelTransfer {
    private static final Logger LOG = Logger.getLogger(CspuLevelTransfer.class);

    public static CspuLevelDto transBoToDto(CspuLevel cspuLevelBo) {
        if (cspuLevelBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CspuLevelDto dto = new CspuLevelDto();
        dto.setId(cspuLevelBo.getId());
        dto.setCspuid(cspuLevelBo.getCspuid());
        if (cspuLevelBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(cspuLevelBo.getAddtime()));
        }
        if (cspuLevelBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(cspuLevelBo.getUpdatetime()));
        }
        return dto;
    }

    public static CspuLevel transDtoToBo(CspuLevelDto cspuLevelDto, boolean setDefaultValue) {
        if (cspuLevelDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CspuLevel cspuLevelBo = new CspuLevel();
            cspuLevelBo.setId(cspuLevelDto.getId());
            cspuLevelBo.setCspuid(cspuLevelDto.getCspuid());
            if (setDefaultValue && (cspuLevelDto.getLevel() == null)) {
                cspuLevelBo.setLevel(0L);
            } else {
                cspuLevelBo.setLevel(cspuLevelDto.getLevel());
            }
            if (cspuLevelDto.getAddtime() != null) {
                cspuLevelBo.setAddtime(dateFormat.parse(cspuLevelDto.getAddtime()));
            } else if (setDefaultValue) {
                cspuLevelBo.setAddtime(new Date());
            }
            if (cspuLevelDto.getUpdatetime() != null) {
                cspuLevelBo.setUpdatetime(dateFormat.parse(cspuLevelDto.getUpdatetime()));
            } else {
                cspuLevelBo.setUpdatetime(new Date());
            }
            return cspuLevelBo;
        } catch (Exception e) {
            LOG.error("Exception in CspuLevelTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(CspuLevelDto cspuLevelDto) {
        if (cspuLevelDto == null) {
            return true;
        }
        if (cspuLevelDto.getCspuid() == null) {
            return false;
        }
        return true;
    }
}
