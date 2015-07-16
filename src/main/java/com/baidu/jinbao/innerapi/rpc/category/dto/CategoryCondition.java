package com.baidu.jinbao.innerapi.rpc.category.dto;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**   
 * 2C类目表相关查询条件
 * @author cgd  
 * @date 2015年6月11日 下午2:09:41 
 */
public class CategoryCondition {
    @Protobuf(fieldType = FieldType.INT64, order = 1, required = false)
    private List<Long> idList;   // 主键ID List
    
    @Protobuf(fieldType = FieldType.INT64, order = 2, required = false)
    private List<Long> categoryIdList;   // 分类ID List
    
    // ------------------------------------------------
    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Long> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }
}
