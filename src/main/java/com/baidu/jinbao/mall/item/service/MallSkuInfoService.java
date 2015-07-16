package com.baidu.jinbao.mall.item.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;
import com.baidu.jinbao.mall.item.bo.MallSkuTotalInfo;
import com.baidu.jinbao.mall.item.vo.MallSkuTotalInfoVo;

public interface MallSkuInfoService extends GenericMapperService<MallSkuInfo, Long> {
    /**
     * 批量插入MallSkuInfo
     * 
     * @param MallSkuInfo 插入单条
     * @return Skuid
     */
    public Integer insertRecord(MallSkuInfo mallSkuInfo);

    /**
     * 批量更新MallSkuInfo
     * 
     * @param MallSkuInfo 更新单条
     * @return 更新条数
     */
    public int updateRecord(MallSkuInfo mallSkuInfo);

    /**
     * 批量更新MallSkuInfo
     * 
     * @param MallSkuInfo 更新单条
     * @return 更新条数
     */
    public int updateRecords(List<MallSkuInfo> mallSkuInfos);

    /**
     * 批量删除itemid in MallSkuInfo table
     * 
     * @param itemId 删除单条MallSkuInfo
     * @return 删除条数
     */
    public int deleteRecord(Long skuId);

    /**
     * 
     * @param skuid 查询单条MallSkuInfo by skuid
     * @return Item
     */
    public MallSkuInfo getRecord(Long skuId);

    /**
     * Sku 相关全量数据更新
     * 
     * @param data 全量数据
     * @return 更新条数
     * @throws Exception
     * */
    public Integer updateSkuTotalInfo(MallSkuTotalInfoVo vo) throws Exception;

    /**
     * Sku 相关全量数据插入
     * 
     * @param data 全量数据
     * @return 插入后SKU_ID
     * */
    public Long insertSkuTotalInfo(MallSkuTotalInfoVo vo) throws Exception;

    /**
     * 根据itemid查询
     * 
     * @param itemId
     * @return
     */
    public List<MallSkuInfo> getRecordsByItemId(Long itemId);

    /**
     * 通过itemIds查询
     * 
     * @param idsList
     * @return List<MallSkuInfo>
     */
    public List<MallSkuInfo> getRecordsByItemIdList(List<Long> idsList);

    /**
     * 根据itemids查询skuTotalInfo
     * 
     * @param idsList
     * @return List<MallSkuTotalInfo>
     */
    public List<MallSkuTotalInfo> getSkuTotalInfoByItemIdList(List<Long> itemIdsList);
}
