package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * ItemInfoDtoList
 * 
 * @author cgd
 * @date 2015年7月8日 下午2:54:59
 */
public class ItemInfoDtoList {

    @Protobuf(fieldType = FieldType.OBJECT)
    private List<ItemInfoDto> dtoList;

    public List<ItemInfoDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<ItemInfoDto> dtoList) {
        this.dtoList = dtoList;
    }

}
