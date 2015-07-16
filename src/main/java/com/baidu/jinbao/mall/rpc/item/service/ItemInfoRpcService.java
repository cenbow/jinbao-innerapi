package com.baidu.jinbao.mall.rpc.item.service;

import com.baidu.jinbao.innerapi.rpc.base.ModifyResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemCondition;
import com.baidu.jinbao.mall.rpc.item.dto.ItemConditionList;
import com.baidu.jinbao.mall.rpc.item.dto.ItemInfoDtoList;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoDto;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoListResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInfoResponse;
import com.baidu.jinbao.mall.rpc.item.dto.ItemTotalInsertResponse;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemCondition;
import com.baidu.jinbao.mall.rpc.item.dto.PageItemTotalInfoListResponse;
import com.baidu.jprotobuf.pbrpc.ProtobufRPC;

public interface ItemInfoRpcService {
    /**
     * Item 相关全量数据更新
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "updateItemTotalInfo", onceTalkTimeout = 30000)
    public ModifyResponse updateItemTotalInfo(ItemTotalInfoDto data);

    /**
     * Item 相关全量数据插入
     * 
     * @param data 全量数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "insertItemTotalInfo", onceTalkTimeout = 30000)
    public ItemTotalInsertResponse insertItemTotalInfo(ItemTotalInfoDto data);

    /**
     * Item merchant_status 的状态更新
     * 
     * @param data ItemInfo数据
     * @return ModifyResponse 返回的信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "updateItemInfoList", onceTalkTimeout = 30000)
    public ModifyResponse updateItemInfoList(ItemInfoDtoList dtoList);

    /**
     * Item 相关全量数据查询,根据shop_id多个, leafcategoryid多个，manual_status，merchant_status
     * 
     * @param condition 分页查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "getPageItemTotalInfo", onceTalkTimeout = 30000)
    public PageItemTotalInfoListResponse getPageItemTotalInfo(PageItemCondition condition);

    /**
     * Item 相关全量数据查询,指定Item的单个商品的数据，支持指定查询项
     * 
     * @param condition 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "getItemTotalInfo", onceTalkTimeout = 30000)
    public ItemTotalInfoResponse getItemTotalInfo(ItemCondition condition);

    /**
     * Item 相关全量数据批量查询,指定item_ids的商品数据，支持制定查询条件
     * 
     * @param data 查询条件
     * @return ModifyResponse 返回的数据信息
     * */
    @ProtobufRPC(serviceName = "mallItemInfoRpcService", methodName = "getItemTotalInfoList", onceTalkTimeout = 30000)
    public ItemTotalInfoListResponse getItemTotalInfoList(ItemConditionList data);
}
