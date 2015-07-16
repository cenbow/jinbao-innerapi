package com.baidu.jinbao.mall.item.bo;

import java.util.List;

public class MallSkuTotalInfo {
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
