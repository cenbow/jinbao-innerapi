package com.baidu.jinbao.mall.rpc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.baidu.jinbao.mall.item.bo.BcsImage;
import com.baidu.jinbao.mall.rpc.item.dto.BcsImageDto;

/**
 * Transfer BcsImage Dto to Bo
 * 
 * @author cgd
 * @date 2015年7月6日 下午2:56:35
 */
public class BcsImageTransfer {
    public static List<BcsImage> transferToBoList(List<BcsImageDto> dtos) throws Exception {
        List<BcsImage> vos = new ArrayList<BcsImage>();
        for (BcsImageDto dto : dtos) {
            vos.add(BcsImageTransfer.transferToBo(dto));
        }
        return vos;
    }

    public static BcsImage transferToBo(BcsImageDto dto) throws Exception {
        BcsImage bo = new BcsImage();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        bo.setId(dto.getId());
        if (dto.getContentMd5() != null) {
            bo.setContentMd5(dto.getContentMd5());
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getSkuid() != null) {
            bo.setSkuid(dto.getSkuid());
        }
        if (dto.getImageurl() != null) {
            bo.setImageurl(dto.getImageurl());
        }
        if (dto.getImagebcsurl() != null) {
            bo.setImagebcsurl(dto.getImagebcsurl());
        }
        if (dto.getBcsStatus() != null) {
            bo.setBcsStatus(Byte.valueOf(dto.getBcsStatus().toString()));
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        if (dto.getErrormessage() != null) {
            bo.setErrormessage(dto.getErrormessage());
        }
        if (dto.getWidth() != null) {
            bo.setWidth(dto.getWidth());
        }
        if (dto.getHeight() != null) {
            bo.setHeight(dto.getHeight());
        }
        if (dto.getSequence() != null) {
            bo.setSequence(dto.getSequence());
        }
        if (dto.getGipsImage() != null) {
            bo.setGipsImage(dto.getGipsImage());
        }
        if (dto.getPicType() != null) {
            bo.setPicType(Byte.valueOf(dto.getPicType().toString()));
        }

        return bo;
    }

    public static BcsImageDto transferToDto(BcsImage bo) {
        BcsImageDto dto = new BcsImageDto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (bo.getContentMd5() != null) {
            dto.setContentMd5(bo.getContentMd5());
        }
        if (bo.getItemid() != null) {
            dto.setItemid(bo.getItemid());
        }
        if (bo.getSkuid() != null) {
            dto.setSkuid(bo.getSkuid());
        }
        if (bo.getImageurl() != null) {
            dto.setImageurl(bo.getImageurl());
        }
        if (bo.getImagebcsurl() != null) {
            dto.setImagebcsurl(bo.getImagebcsurl());
        }
        if (bo.getBcsStatus() != null) {
            dto.setBcsStatus(Integer.valueOf(bo.getBcsStatus()));
        }
        if (bo.getAddtime() != null) {
            dto.setAddtime(sdf.format(bo.getAddtime()));
        }
        if (bo.getUpdatetime() != null) {
            dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        }
        if (bo.getErrormessage() != null) {
            dto.setErrormessage(bo.getErrormessage());
        }
        if (bo.getWidth() != null) {
            dto.setWidth(bo.getWidth());
        }
        if (bo.getHeight() != null) {
            dto.setHeight(bo.getHeight());
        }
        if (bo.getSequence() != null) {
            dto.setSequence(bo.getSequence());
        }
        if (bo.getGipsImage() != null) {
            dto.setGipsImage(bo.getGipsImage());
        }
        if (bo.getPicType() != null) {
            dto.setPicType(Integer.valueOf(bo.getPicType()));
        }

        return dto;
    }
}
