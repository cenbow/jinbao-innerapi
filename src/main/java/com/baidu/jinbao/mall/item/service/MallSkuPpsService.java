package com.baidu.jinbao.mall.item.service;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;

import java.util.List;

public interface MallSkuPpsService extends GenericMapperService<MallSkuPps, Long> {
    /**
     * 批量插入MallSkuPps
     * 
     * @param MallSkuPps 插入单条
     * @return 插入条数
     */
    public Long insertRecord(MallSkuPps mallSkuPps);

    /**
     * 批量更新MallSkuPps
     * 
     * @param MallSkuPps 更新单条 By pps_md5
     * @return 更新条数
     */
    public int updateRecord(MallSkuPps mallSkuPps);

    /**
     * 批量删除itemid in MallSkuPps table
     * 
     * @param ppdMd5 删除单条MallSkuPps
     * @return 删除条数
     */
    public int deleteRecord(String ppsMd5);

    /**
     * 查询单条
     * 
     * @param 查询单条MallSkuPps by ppsMd5
     * @return Item
     */
    public MallSkuPps getRecord(String ppsMd5);

    /**
     * 批量更新MallSkuPps
     * 
     * @param MallSkuPps 更新批量更新 By pps_md5
     * @return 更新条数
     */
    public int batchUpdateRecord(List<MallSkuPps> mallSkuPps);

    /**
     * 批量插入MallSkuPps
     * 
     * @param MallSkuPps 批量插入 By pps_md5
     * @return 插入条数
     */
    public int batchInsertRecord(List<MallSkuPps> mallSkuPpsList);

    /**
     * 批量根据skuids查询
     * 
     * @param skuIdList
     * @return List<MallSkuPps>
     */ 
    public List<MallSkuPps> batchGetRecordsBySkuIds(List<Long> skuIdList);

    /**
     * 根据skuId查询
     * 
     * @param skuid
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> getRecordsBySkuId(Long skuid);

    /**
     * 根据 skuid,regionid,device组合的list返回查询结果
     * 
     * @param skuIdList, regionIdList, deviceList
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> getSkuPpsInfoList(List<Long> skuIdList, List<Integer> regionIdList,
            List<Integer> deviceList);

}
