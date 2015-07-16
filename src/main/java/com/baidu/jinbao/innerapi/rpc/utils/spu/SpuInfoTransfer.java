package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.SpuInfoDto;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;

public class SpuInfoTransfer {
    private static final Logger LOG = Logger.getLogger(SpuInfoTransfer.class);

    public static SpuInfoDto tranBoToDto(SpuInfo spuInfoBo) {
        if (spuInfoBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpuInfoDto dto = new SpuInfoDto();
        dto.setSpuid(spuInfoBo.getSpuid());
        dto.setCid(spuInfoBo.getCid());
        dto.setName(spuInfoBo.getName());
        dto.setBrand(spuInfoBo.getBrand());
        dto.setProductModel(spuInfoBo.getProductModel());
        if (spuInfoBo.getDeleted() != null) {
            dto.setDeleted(spuInfoBo.getDeleted().intValue());
        }
        if (spuInfoBo.getActive() != null) {
            dto.setActive(spuInfoBo.getActive().intValue());
        }
        if (spuInfoBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(spuInfoBo.getAddtime()));
        }
        if (spuInfoBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(spuInfoBo.getUpdatetime()));
        }
        return dto;
    }

    public static SpuInfo transDtoToBo(SpuInfoDto spuInfoDto, boolean setDefaultValue) {
        if (spuInfoDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SpuInfo spuInfoBo = new SpuInfo();
            spuInfoBo.setSpuid(spuInfoDto.getSpuid());
            spuInfoBo.setCid(spuInfoDto.getCid());
            spuInfoBo.setName(spuInfoDto.getName());
            if (setDefaultValue && (spuInfoDto.getBrand() == null)) {
                spuInfoBo.setBrand(0L);
            } else {
                spuInfoBo.setBrand(spuInfoDto.getBrand());
            }
            if (setDefaultValue && (spuInfoDto.getProductModel() == null)) {
                spuInfoBo.setProductModel("");
            } else {
                spuInfoBo.setProductModel(spuInfoDto.getProductModel());
            }
            if (spuInfoDto.getDeleted() != null) {
                spuInfoBo.setDeleted(spuInfoDto.getDeleted().byteValue());
            } else if (setDefaultValue) {
                spuInfoBo.setDeleted(new Integer(0).byteValue());
            }
            if (spuInfoDto.getActive() != null) {
                spuInfoBo.setActive(spuInfoDto.getActive().byteValue());
            } else if (setDefaultValue) {
                spuInfoBo.setActive(new Integer(1).byteValue());
            }
            if (spuInfoDto.getAddtime() != null) {
                spuInfoBo.setAddtime(dateFormat.parse(spuInfoDto.getAddtime()));
            } else if (setDefaultValue) {
                spuInfoBo.setAddtime(new Date());
            }
            if (spuInfoDto.getUpdatetime() != null) {
                spuInfoBo.setUpdatetime(dateFormat.parse(spuInfoDto.getUpdatetime()));
            } else {
                spuInfoBo.setUpdatetime(new Date());
            }
            return spuInfoBo;
        } catch (Exception e) {
            LOG.error("Exception in SpuInfoTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static Boolean checkRequiredField(SpuInfoDto spuInfoDto) {
        if (spuInfoDto == null) {
            return true;
        }
        if ((spuInfoDto.getCid() == null) || (spuInfoDto.getName() == null)) {
            return false;
        }
        return true;
    }

}
