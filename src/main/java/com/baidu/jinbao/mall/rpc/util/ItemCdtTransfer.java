package com.baidu.jinbao.mall.rpc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.baidu.jinbao.mall.item.bo.ItemCdt;
import com.baidu.jinbao.mall.rpc.item.dto.ItemCdtDto;

public class ItemCdtTransfer {

    public static ItemCdt transferToBo(ItemCdtDto dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemCdt bo = new ItemCdt();
        if (dto.getId() != null) {
            bo.setId(dto.getId());
        }
        if (dto.getItemid() != null) {
            bo.setItemid(dto.getItemid());
        }
        if (dto.getLeafcategoryid() != null) {
            bo.setLeafcategoryid(dto.getLeafcategoryid());
        }
        if (dto.getClassificationtype() != null) {
            bo.setClassificationtype(dto.getClassificationtype().byteValue());
        }
        if (dto.getOperator() != null) {
            bo.setOperator(dto.getOperator());
        }
        if (dto.getConfidence() != null) {
            bo.setConfidence(dto.getConfidence());
        }
        if (dto.getCdtMd5() != null) {
            bo.setCdtMd5(dto.getCdtMd5());
        }
        if (dto.getAddtime() != null) {
            bo.setAddtime(sdf.parse(dto.getAddtime()));
        }
        if (dto.getUpdatetime() != null) {
            bo.setUpdatetime(sdf.parse(dto.getUpdatetime()));
        }
        return bo;
    }

    public static ItemCdtDto transferToDto(ItemCdt bo) {
        if (bo == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ItemCdtDto dto = new ItemCdtDto();
        dto.setId(bo.getId());
        dto.setItemid(bo.getItemid());
        dto.setLeafcategoryid(bo.getLeafcategoryid());
        dto.setClassificationtype(Integer.valueOf(bo.getClassificationtype()));
        dto.setOperator(bo.getOperator());
        dto.setConfidence(bo.getConfidence());
        dto.setCdtMd5(bo.getCdtMd5());
        dto.setAddtime(sdf.format((bo.getAddtime())));
        dto.setUpdatetime(sdf.format(bo.getUpdatetime()));
        return dto;
    }

    public static List<ItemCdt> transferToBoList(List<ItemCdtDto> itemCdtDtoList) throws ParseException {
        ArrayList<ItemCdt> itemCdtList = new ArrayList<ItemCdt>();
        for (ItemCdtDto itemCdtDto : itemCdtDtoList) {
            itemCdtList.add(ItemCdtTransfer.transferToBo(itemCdtDto));
        }
        return itemCdtList;
    }
}
