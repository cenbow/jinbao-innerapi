package com.baidu.jinbao.innerapi.rpc.sku.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.jinbao.innerapi.jinbaocommon.bo.MerchantRouter;
import com.baidu.jinbao.innerapi.jinbaocommon.service.MerchantRouterService;

@Component
public class ShardRouterService {
    @Autowired
    private MerchantRouterService merchantRouterService;

    public String getSplitDbInfo(String skuId) {
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Long merchantid = Long.valueOf(skuId.split("_")[0]);
        String outerid = skuId.split("_")[1];
        List<MerchantRouter> merchantRouterList = merchantRouterService.getRecordsByMerchantid(merchantid);
        if (CollectionUtils.isEmpty(merchantRouterList)) {
            return "";
        } else {
            int msize = merchantRouterList.size();
            return merchantRouterList.get((outerid.hashCode() % msize + msize) % msize).getUsedShards();
        }
    }
}
