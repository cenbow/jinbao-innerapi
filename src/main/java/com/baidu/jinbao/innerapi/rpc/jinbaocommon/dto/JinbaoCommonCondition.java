package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**   
 * jinbao common表相关查询条件
 * @author hbf  
 * @date 2015年6月15日  
 */
public class JinbaoCommonCondition {
    @Protobuf(fieldType = FieldType.INT64, order = 1, required = false)
    private List<Long> idList;   // 主键ID List

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

}
