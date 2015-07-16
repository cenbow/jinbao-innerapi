package com.baidu.jinbao.innerapi.spu.vo;

import com.baidu.jinbao.innerapi.spu.bo.SpuComment;
import com.baidu.jinbao.innerapi.spu.bo.SpuInfo;

/**
 * Spu相关全量数据
 * 
 * @author cgd
 * @date 2015年6月15日 下午3:41:48
 */
public class SpuTotalInfoVo {

    private SpuInfo spuInfo;
    private SpuComment spuComment;

    public SpuInfo getSpuInfo() {
        return spuInfo;
    }

    public void setSpuInfo(SpuInfo spuInfo) {
        this.spuInfo = spuInfo;
    }

    public SpuComment getSpuComment() {
        return spuComment;
    }

    public void setSpuComment(SpuComment spuComment) {
        this.spuComment = spuComment;
    }

}
