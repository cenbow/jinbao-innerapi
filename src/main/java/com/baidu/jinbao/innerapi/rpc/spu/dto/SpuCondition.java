package com.baidu.jinbao.innerapi.rpc.spu.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * 2C类目表相关查询条件
 * 
 * @author cgd
 * @date 2015年6月11日 下午2:09:41
 */
public class SpuCondition {
    @Protobuf(fieldType = FieldType.INT64, order = 1, required = false)
    private List<Long> idList; // 主键ID List

    @Protobuf(fieldType = FieldType.INT64, order = 2, required = false)
    private List<Long> spuIdList; // SPU ID List

    @Protobuf(fieldType = FieldType.INT64, order = 3, required = false)
    private List<Long> cspuidList; // CSPU ID List

    // ------------------------------------------------
    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getSpuIdList() {
        return spuIdList;
    }

    public void setSpuIdList(List<Long> spuIdList) {
        this.spuIdList = spuIdList;
    }

    public List<Long> getCspuidList() {
        return cspuidList;
    }

    public void setCspuidList(List<Long> cspuidList) {
        this.cspuidList = cspuidList;
    }
}
