package com.baidu.jinbao.innerapi.spu.vo;

import com.baidu.jinbao.innerapi.spu.bo.CspuComment;
import com.baidu.jinbao.innerapi.spu.bo.CspuInfo;
import com.baidu.jinbao.innerapi.spu.bo.CspuLevel;

/**
 * Cspu相关全量数据
 * 
 * @author cgd
 * @date 2015年6月15日 下午3:41:59
 */
public class CspuTotalInfoVo {

    private CspuInfo cspuInfo;
    private CspuLevel cspuLevel;
    private CspuComment cspuComment;

    public CspuInfo getCspuInfo() {
        return cspuInfo;
    }

    public void setCspuInfo(CspuInfo cspuInfo) {
        this.cspuInfo = cspuInfo;
    }

    public CspuLevel getCspuLevel() {
        return cspuLevel;
    }

    public void setCspuLevel(CspuLevel cspuLevel) {
        this.cspuLevel = cspuLevel;
    }

    public CspuComment getCspuComment() {
        return cspuComment;
    }

    public void setCspuComment(CspuComment cspuComment) {
        this.cspuComment = cspuComment;
    }

}
