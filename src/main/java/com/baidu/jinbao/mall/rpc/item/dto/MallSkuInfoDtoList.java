package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * MallSkuInfoDtoList
 * 
 * @author cgd
 * @date 2015年7月10日 下午4:27:27
 */
public class MallSkuInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT, required = false)
    private List<MallSkuInfoDto> dtoList;

    public List<MallSkuInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<MallSkuInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
