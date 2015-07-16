package com.baidu.jinbao.innerapi.rpc.utils.spu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.rpc.spu.dto.CspuInfoDto;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;

public class CspuInfoTransfer {
    private static final Logger LOG = Logger.getLogger(CspuInfoTransfer.class);

    public static CspuInfoDto transBoToDto(CspuInfo cspuInfoBo) {
        if (cspuInfoBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CspuInfoDto dto = new CspuInfoDto();
        dto.setCspuid(cspuInfoBo.getCspuid());
        dto.setCid(cspuInfoBo.getCid());
        dto.setSpuid(cspuInfoBo.getSpuid());
        dto.setName(cspuInfoBo.getName());
        dto.setAlias(cspuInfoBo.getAlias());
        dto.setBrand(cspuInfoBo.getBrand());
        dto.setProductModel(cspuInfoBo.getProductModel());
        dto.setProductUpc(cspuInfoBo.getProductUpc());
        dto.setSaleAttribute(cspuInfoBo.getSaleAttribute());
        dto.setAttribute(cspuInfoBo.getAttribute());
        dto.setSaleAttributeOrig(cspuInfoBo.getSaleAttributeOrig());
        dto.setAttributeOrig(cspuInfoBo.getSaleAttributeOrig());
        dto.setExtendAttribute(cspuInfoBo.getExtendAttribute());
        dto.setPackList(cspuInfoBo.getPackList());
        dto.setPrice(Float.parseFloat(cspuInfoBo.getPrice().toString()));
        dto.setUrl(cspuInfoBo.getUrl());
        dto.setBigField(cspuInfoBo.getBigField());
        if (cspuInfoBo.getDeleted() == null) {
            dto.setDeleted(cspuInfoBo.getDeleted().intValue());
        }
        if (cspuInfoBo.getActive() == null) {
            dto.setActive(cspuInfoBo.getActive().intValue());
        }
        dto.setLevel(cspuInfoBo.getLevel());
        if (cspuInfoBo.getCspuFrom() != null) {
            dto.setCspuFrom(cspuInfoBo.getCspuFrom().intValue());
        }
        if (cspuInfoBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(cspuInfoBo.getAddtime()));
        }
        if (cspuInfoBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(cspuInfoBo.getUpdatetime()));
        }
        return dto;
    }

    public static CspuInfo transDtoToBo(CspuInfoDto cspuInfoDto, boolean setDefaultValue) {
        if (cspuInfoDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CspuInfo cspuInfoBo = new CspuInfo();
            cspuInfoBo.setCspuid(cspuInfoDto.getCspuid());
            cspuInfoBo.setCid(cspuInfoDto.getCid());
            if (setDefaultValue && (cspuInfoDto.getSpuid() == null)) {
                cspuInfoBo.setSpuid(-1L);
            } else {
                cspuInfoBo.setSpuid(cspuInfoDto.getSpuid());
            }
            cspuInfoBo.setName(cspuInfoDto.getName());
            if (setDefaultValue && (cspuInfoDto.getAddtime() == null)) {
                cspuInfoBo.setAlias("");
            } else {
                cspuInfoBo.setAlias(cspuInfoDto.getAlias());
            }
            if (setDefaultValue && (cspuInfoDto.getBrand() == null)) {
                cspuInfoBo.setBrand(0L);
            } else {
                cspuInfoBo.setBrand(cspuInfoDto.getBrand());
            }
            if (setDefaultValue && (cspuInfoDto.getProductModel() == null)) {
                cspuInfoBo.setProductModel("");
            } else {
                cspuInfoBo.setProductModel(cspuInfoDto.getProductModel());
            }
            if (setDefaultValue && (cspuInfoDto.getProductUpc() == null)) {
                cspuInfoBo.setProductUpc("");
            } else {
                cspuInfoBo.setProductUpc(cspuInfoDto.getProductUpc());
            }
            if (setDefaultValue && (cspuInfoDto.getSaleAttribute() == null)) {
                cspuInfoBo.setSaleAttribute("");
            } else {
                cspuInfoBo.setSaleAttribute(cspuInfoDto.getSaleAttribute());
            }
            if (setDefaultValue && (cspuInfoDto.getAttribute() == null)) {
                cspuInfoBo.setAttribute("");
            } else {
                cspuInfoBo.setAttribute(cspuInfoDto.getAttribute());
            }
            if (setDefaultValue && (cspuInfoDto.getSaleAttributeOrig() == null)) {
                cspuInfoBo.setSaleAttributeOrig("");
            } else {
                cspuInfoBo.setSaleAttributeOrig(cspuInfoDto.getSaleAttributeOrig());
            }
            if (setDefaultValue && (cspuInfoDto.getAttributeOrig() == null)) {
                cspuInfoBo.setAttributeOrig("");
            } else {
                cspuInfoBo.setAttributeOrig(cspuInfoDto.getAttributeOrig());
            }
            if (setDefaultValue && (cspuInfoDto.getExtendAttribute() == null)) {
                cspuInfoBo.setExtendAttribute("");
            } else {
                cspuInfoBo.setExtendAttribute(cspuInfoDto.getExtendAttribute());
            }
            if (setDefaultValue && (cspuInfoDto.getPackList() == null)) {
                cspuInfoBo.setPackList("");
            } else {
                cspuInfoBo.setPackList(cspuInfoDto.getPackList());
            }
            if (setDefaultValue && (cspuInfoDto.getPrice() == null)) {
                cspuInfoBo.setPrice(0);
            } else {
                cspuInfoBo.setPrice(Float.toString(cspuInfoDto.getPrice()));
            }
            if (setDefaultValue && (cspuInfoDto.getUrl() == null)) {
                cspuInfoBo.setUrl("");
            } else {
                cspuInfoBo.setUrl(cspuInfoDto.getUrl());
            }
            cspuInfoBo.setBigField(cspuInfoDto.getBigField());
            if (cspuInfoDto.getDeleted() != null) {
                cspuInfoBo.setDeleted(cspuInfoDto.getDeleted().byteValue());
            } else if (setDefaultValue) {
                cspuInfoBo.setDeleted(new Integer(0).byteValue());
            }
            if (cspuInfoDto.getActive() != null) {
                cspuInfoBo.setActive(cspuInfoDto.getActive().byteValue());
            } else if (setDefaultValue) {
                cspuInfoBo.setActive(new Integer(1).byteValue());
            }
            if (setDefaultValue && (cspuInfoDto.getLevel() == null)) {
                cspuInfoBo.setLevel(0L);
            } else {
                cspuInfoBo.setLevel(cspuInfoDto.getLevel());
            }
            if (cspuInfoDto.getCspuFrom() != null) {
                cspuInfoBo.setCspuFrom(cspuInfoDto.getCspuFrom().byteValue());
            }
            if (cspuInfoDto.getAddtime() != null) {
                cspuInfoBo.setAddtime(dateFormat.parse(cspuInfoDto.getAddtime()));
            } else if (setDefaultValue) {
                cspuInfoBo.setAddtime(new Date());
            }
            if (cspuInfoDto.getUpdatetime() != null) {
                cspuInfoBo.setUpdatetime(dateFormat.parse(cspuInfoDto.getUpdatetime()));
            } else {
                cspuInfoBo.setUpdatetime(new Date());
            }
            return cspuInfoBo;
        } catch (Exception e) {
            LOG.error("Exception in CspuInfoTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(CspuInfoDto cspuInfoDto) {
        if (cspuInfoDto == null) {
            return true;
        }
        if ((cspuInfoDto.getCid() == null) || (cspuInfoDto.getName() == null) || (cspuInfoDto.getBigField() == null)
                || (cspuInfoDto.getCspuFrom() == null)) {
            return false;
        }
        return true;
    }
}
