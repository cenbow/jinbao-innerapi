package com.baidu.jinbao.mall.rpc.util;

import java.util.ArrayList;
import java.util.List;

import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuPpsDto;
import com.baidu.jinbao.mall.rpc.item.dto.MallSkuTotalInfoDto;

public class MallSkuTotalTransfer {
    public static MallSkuTotalInfoDto transferToDto(MallSkuTotalInfo bo) throws Exception {
        MallSkuTotalInfoDto dto = new MallSkuTotalInfoDto();
        if (bo.getMallSkuInfo() != null) {
            MallSkuInfoDto mallSkuInfoDto = MallSkuInfoTransfer.transferToDto(bo.getMallSkuInfo());
            dto.setMallSkuInfoDto(mallSkuInfoDto);
        }
        if (bo.getMallSkuPpsList() != null) {
            List<MallSkuPpsDto> mallSkuPpsDtoList = new ArrayList<MallSkuPpsDto>();
            for (int i = 0; i < bo.getMallSkuPpsList().size(); i++) {
                MallSkuPpsDto mallSkuPpsDto = MallSkuPpsTransfer.transferToDto(bo.getMallSkuPpsList().get(i));
                mallSkuPpsDtoList.add(mallSkuPpsDto);
            }
            dto.setMallSkuPpsDtoList(mallSkuPpsDtoList);
        }
        return dto;
    }

}
