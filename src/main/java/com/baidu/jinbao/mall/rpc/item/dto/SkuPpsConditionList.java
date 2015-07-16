package com.baidu.jinbao.mall.rpc.item.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * SkuPpsCondition
 * 
 * @author cgd
 * @date 2015年7月3日 下午4:04:12
 */
public class SkuPpsConditionList {

    @Protobuf(fieldType = FieldType.OBJECT, required = false)
    private List<SkuPpsCondition> conditionList;

    public List<SkuPpsCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<SkuPpsCondition> conditionList) {
        this.conditionList = conditionList;
    }

}
