package com.baidu.jinbao.innerapi.rpc.utils.sku;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.baidu.jinbao.innerapi.common.utils.MD5Util;
import com.baidu.jinbao.innerapi.rpc.sku.dto.SkuInfoDto;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;

public class SkuInfoTransfer {
    private static final Logger LOG = Logger.getLogger(SkuInfoTransfer.class);

    public static SkuInfoDto transBoToDto(SkuInfo skuInfoBo) {
        if (skuInfoBo == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SkuInfoDto dto = new SkuInfoDto();
        dto.setSkuInnerid(skuInfoBo.getSkuInnerid());
        dto.setSkuHashkey(skuInfoBo.getSkuHashkey());
        dto.setSkuid(skuInfoBo.getSkuid());
        dto.setMerchantid(skuInfoBo.getMerchantid());
        dto.setUniqueKey(skuInfoBo.getUniqueKey());
        dto.setFeedid(skuInfoBo.getFeedid());
        dto.setUcid(skuInfoBo.getUcid());
        dto.setTitle(skuInfoBo.getTitle());
        dto.setSubtitle(skuInfoBo.getSubtitle());
        dto.setUrl(skuInfoBo.getUrl());
        dto.setMobileurl(skuInfoBo.getMobileurl());
        dto.setOuterid(skuInfoBo.getOuterid());
        dto.setCategoryOri(skuInfoBo.getCategoryOri());
        dto.setBrandOri(skuInfoBo.getBrandOri());
        if (skuInfoBo.getStarttime() != null) {
            dto.setStarttime(dateFormat.format(skuInfoBo.getStarttime()));
        }
        if (skuInfoBo.getEndtime() != null) {
            dto.setEndtime(dateFormat.format(skuInfoBo.getEndtime()));
        }
        if (skuInfoBo.getManualStatus() != null) {
            dto.setMerchantStatus(skuInfoBo.getMerchantStatus().intValue());
        }
        if (skuInfoBo.getManualStatus() != null) {
            dto.setManualStatus(skuInfoBo.getManualStatus().intValue());
        }
        dto.setSignature(skuInfoBo.getSignature());
        if (skuInfoBo.getIsselfopen() != null) {
            dto.setIsselfopen(skuInfoBo.getIsselfopen().intValue());
        }
        dto.setDataversion(skuInfoBo.getDataversion());
        if (skuInfoBo.getInactivetime() != null) {
            dto.setInactivetime(dateFormat.format(skuInfoBo.getInactivetime()));
        }
        if (skuInfoBo.getAddtime() != null) {
            dto.setAddtime(dateFormat.format(skuInfoBo.getAddtime()));
        }
        if (skuInfoBo.getUpdatetime() != null) {
            dto.setUpdatetime(dateFormat.format(skuInfoBo.getUpdatetime()));
        }
        if (skuInfoBo.getDeleted() != null) {
            dto.setDeleted(skuInfoBo.getDeleted().intValue());
        }
        dto.setSellerName(skuInfoBo.getSellerName());
        dto.setUpc(skuInfoBo.getUpc());

        return dto;
    }

    public static SkuInfo transDtoToBo(SkuInfoDto skuInfoDto, boolean setDefaultValue) {
        if (skuInfoDto == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SkuInfo skuInfoBo = new SkuInfo();
            if (skuInfoDto.getSkuid() != null) {
                skuInfoBo.setSkuHashkey(Long.valueOf(skuInfoDto.getSkuid().hashCode()));
            }
            skuInfoBo.setSkuid(skuInfoDto.getSkuid());
            skuInfoBo.setMerchantid(skuInfoDto.getMerchantid());
            skuInfoBo.setFeedid(skuInfoDto.getFeedid());
            if (setDefaultValue && (skuInfoDto.getUcid() == null)) {
                skuInfoBo.setUcid(0L);
            } else {
                skuInfoBo.setUcid(skuInfoDto.getUcid());
            }
            // skuInfoBo.setUcid((setDefaultValue && skuInfoDto.getUcid() == null) ? 0L : skuInfoDto.getUcid());
            skuInfoBo.setTitle(skuInfoDto.getTitle());
            skuInfoBo
                    .setSubtitle(setDefaultValue && (skuInfoDto.getSubtitle() == null) ? "" : skuInfoDto.getSubtitle());
            skuInfoBo.setUrl(setDefaultValue && (skuInfoDto.getUrl() == null) ? "" : skuInfoDto.getUrl());
            skuInfoBo.setMobileurl(setDefaultValue && (skuInfoDto.getMobileurl() == null) ? "" : skuInfoDto
                    .getMobileurl());
            skuInfoBo.setOuterid(skuInfoDto.getOuterid());
            if (skuInfoBo.getOuterid() != null) {
                skuInfoBo.setUniqueKey(MD5Util.getMD5Value(skuInfoBo.getOuterid()));
            }
            if (setDefaultValue && (skuInfoDto.getCategoryOri() == null)) {
                skuInfoBo.setCategoryOri("");
            } else {
                skuInfoBo.setCategoryOri(skuInfoDto.getCategoryOri());
            }
            if (setDefaultValue && (skuInfoDto.getBrandOri() == null)) {
                skuInfoBo.setBrandOri("");
            } else {
                skuInfoBo.setBrandOri(skuInfoDto.getBrandOri());
            }

            if (skuInfoDto.getStarttime() != null) {
                skuInfoBo.setStarttime(dateFormat.parse(skuInfoDto.getStarttime()));
            }
            if (skuInfoDto.getEndtime() != null) {
                skuInfoBo.setEndtime(dateFormat.parse(skuInfoDto.getEndtime()));
            }
            if (skuInfoDto.getMerchantStatus() != null) {
                skuInfoBo.setMerchantStatus(skuInfoDto.getMerchantStatus().byteValue());
            } else if (setDefaultValue) {
                skuInfoBo.setMerchantStatus(new Integer(0).byteValue());
            }
            if (skuInfoDto.getManualStatus() != null) {
                skuInfoBo.setManualStatus(skuInfoDto.getManualStatus().byteValue());
            } else if (setDefaultValue) {
                skuInfoBo.setManualStatus(new Integer(0).byteValue());
            }
            skuInfoBo.setSignature(skuInfoDto.getSignature());
            if (skuInfoDto.getIsselfopen() != null) {
                skuInfoBo.setIsselfopen(skuInfoDto.getIsselfopen().byteValue());
            }
            skuInfoBo.setDataversion(skuInfoDto.getDataversion());
            if (skuInfoDto.getInactivetime() != null) {
                skuInfoBo.setInactivetime(dateFormat.parse(skuInfoDto.getInactivetime()));
            }
            if (skuInfoDto.getAddtime() != null) {
                skuInfoBo.setAddtime(dateFormat.parse(skuInfoDto.getAddtime()));
            } else if (setDefaultValue) {
                skuInfoBo.setAddtime(new Date());
            }
            if (skuInfoDto.getUpdatetime() != null) {
                skuInfoBo.setUpdatetime(dateFormat.parse(skuInfoDto.getUpdatetime()));
            } else {
                skuInfoBo.setUpdatetime(new Date());
            }
            if (skuInfoDto.getDeleted() != null) {
                skuInfoBo.setDeleted(skuInfoDto.getDeleted().byteValue());
            } else if (setDefaultValue) {
                skuInfoBo.setDeleted(new Integer(0).byteValue());
            }
            if (setDefaultValue && (skuInfoDto.getSellerName() == null)) {
                skuInfoBo.setSellerName("");
            } else {
                skuInfoBo.setSellerName(skuInfoDto.getSellerName());
            }
            if (setDefaultValue && (skuInfoDto.getUpc() == null)) {
                skuInfoBo.setUpc(0L);
            } else {
                skuInfoBo.setUpc(skuInfoDto.getUpc());
            }
            return skuInfoBo;
        } catch (Exception e) {
            LOG.error("Exception in SkuInfoTransfer:transDtoToBo", e);
            return null;
        }
    }

    public static boolean checkRequiredField(SkuInfoDto skuInfoDto) {
        if (skuInfoDto == null) {
            return true;
        }
        if ((skuInfoDto.getSkuid() == null) || (skuInfoDto.getMerchantid() == null) || (skuInfoDto.getFeedid() == null)
                || (skuInfoDto.getTitle() == null) || (skuInfoDto.getOuterid() == null)
                || (skuInfoDto.getStarttime() == null) || (skuInfoDto.getEndtime() == null)
                || (skuInfoDto.getSignature() == null) || (skuInfoDto.getIsselfopen() == null)
                || (skuInfoDto.getDataversion() == null) || (skuInfoDto.getInactivetime() == null)) {
            return false;
        }
        return true;
    }
}
