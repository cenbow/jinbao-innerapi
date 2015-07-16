package com.baidu.jinbao.mall.rpc.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidu.jinbao.mall.item.bo.ItemTotalInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;
import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.item.vo.ItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.MallSkuTotalInfoVo;
import com.baidu.jinbao.mall.rpc.item.dto.ItemAttributeDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemCdtDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoDto;

public class ItemTotalInfoTransfer {
    public static ItemTotalInfoDto transferToDto(ItemTotalInfo bo) throws Exception {
        ItemTotalInfoDto dto = new ItemTotalInfoDto();
        if (bo.getItemInfo() != null) {
            ItemInfoDto itemInfoDto = ItemInfoTransfer.transferToDto(bo.getItemInfo());
            dto.setItemInfoDto(itemInfoDto);
        }
        if (bo.getItemAttribute() != null) {
            ItemAttributeDto itemAttributeDto = ItemAttributeTransfer.transferToDto(bo.getItemAttribute());
            dto.setItemAttributeDto(itemAttributeDto);
        }
        if (bo.getItemCdtList() != null) {
            List<ItemCdtDto> itemCdtDtoList = new ArrayList<ItemCdtDto>();
            for (int i = 0; i < bo.getItemCdtList().size(); i++) {
                ItemCdtDto itemCdtDto = ItemCdtTransfer.transferToDto(bo.getItemCdtList().get(i));
                itemCdtDtoList.add(itemCdtDto);
            }
            dto.setItemCdtDtoList(itemCdtDtoList);
        }
        List<MallSkuTotalInfoDto> mallSkuTotalDto = new ArrayList<MallSkuTotalInfoDto>();
        if (CollectionUtils.isNotEmpty(bo.getMallSkuTotalInfoList())) {
            for (MallSkuTotalInfo info : bo.getMallSkuTotalInfoList()) {
                MallSkuTotalInfoDto tempDto = MallSkuTotalTransfer.transferToDto(info);
                mallSkuTotalDto.add(tempDto);
            }
        }
        dto.setMallSkuTotalInfoDtoList(mallSkuTotalDto);
        return dto;
    }

    public static ItemTotalInfoVo transferToVo(ItemTotalInfoDto dto) throws Exception {
        ItemTotalInfoVo vo = new ItemTotalInfoVo();

        if (dto.getItemInfoDto() != null) {
            vo.setItemInfo(ItemInfoTransfer.transferToBo(dto.getItemInfoDto()));
        }
        if (dto.getItemAttributeDto() != null) {
            vo.setItemAttribute(ItemAttributeTransfer.transferToBo(dto.getItemAttributeDto()));
        }
        if (dto.getItemDescriptionDto() != null) {
            vo.setItemDescription(ItemDescriptionTransfer.transferToBo(dto.getItemDescriptionDto()));
        }
        if (dto.getItemCdtDtoList() != null) {
            vo.setItemCdtList(ItemCdtTransfer.transferToBoList(dto.getItemCdtDtoList()));
        }
        if (dto.getBcsImageDtoList() != null) {
            vo.setBcsImageList(BcsImageTransfer.transferToBoList(dto.getBcsImageDtoList()));
        }
        if (CollectionUtils.isNotEmpty(dto.getMallSkuTotalInfoDtoList())) {
            List<MallSkuTotalInfoVo> skuTotalVoList = new ArrayList<MallSkuTotalInfoVo>();
            for (MallSkuTotalInfoDto totalInfoDto : dto.getMallSkuTotalInfoDtoList()) {
                MallSkuTotalInfoVo totalInfoVo = new MallSkuTotalInfoVo();
                // skuinfo一定要有
                totalInfoVo.setMallSkuInfo(MallSkuInfoTransfer.transferToBo(totalInfoDto.getMallSkuInfoDto()));
                // skupps 不一定要有
                if (CollectionUtils.isNotEmpty(totalInfoDto.getMallSkuPpsDtoList())) {
                    List<MallSkuPps> ppsList = new ArrayList<MallSkuPps>();
                    for (MallSkuPpsDto ppsDto : totalInfoDto.getMallSkuPpsDtoList()) {
                        MallSkuPps ppsBo = MallSkuPpsTransfer.transferToBo(ppsDto);
                        ppsList.add(ppsBo);
                    }
                    totalInfoVo.setMallSkuPpsList(ppsList);
                }
                skuTotalVoList.add(totalInfoVo);
            }
            vo.setMallSkuTotalInfoList(skuTotalVoList);
        }
        return vo;
    }

}
