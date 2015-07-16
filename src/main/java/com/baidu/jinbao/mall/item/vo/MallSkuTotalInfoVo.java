package com.baidu.jinbao.mall.item.vo;

import java.util.List;

import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;

public class MallSkuTotalInfoVo {
    private MallSkuInfo mallSkuInfo;
    private List<MallSkuPps> mallSkuPpsList;

    public MallSkuInfo getMallSkuInfo() {
        return mallSkuInfo;
    }

    public void setMallSkuInfo(MallSkuInfo mallSkuInfo) {
        this.mallSkuInfo = mallSkuInfo;
    }

    public List<MallSkuPps> getMallSkuPpsList() {
        return mallSkuPpsList;
    }

    public void setMallSkuPpsList(List<MallSkuPps> mallSkuPpsList) {
        this.mallSkuPpsList = mallSkuPpsList;
    }
}
