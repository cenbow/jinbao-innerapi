package com.baidu.jinbao.mall.rpc.category.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * CategoryId
 * 
 * @author cgd
 * @date 2015年7月2日 下午5:10:03
 */
public class CategoryId {

    @Protobuf
    private Long parentId; // 主键ID List

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
