package com.baidu.jinbao.mall.item.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.MallSkuInfo;

public interface MallSkuInfoDao extends GenericMapperDao<MallSkuInfo, Long> {
    /**
     * 插入MallSkuInfo
     * 
     * @param 插入MallSkuInfo
     * @return 插入后获得自增id
     */
    public Integer insertOne(MallSkuInfo mallSkuInfo);

    /**
     * 更新MallSkuInfo
     * 
     * @param 更新MallSkuInfo
     * @return 更新条数
     */
    public int update(MallSkuInfo mallSkuInfo);
    /**
     * 批量更新MallSkuInfo
     * 
     * @param 更新MallSkuInfo
     * @return 更新条数
     */
    public int updateRecords(List<MallSkuInfo> mallSkuInfoList);
    /**
     * 删除MallSkuInfo
     * 
     * @param 删除增主键skuId
     * @return 删除条数
     */
    public int delete(Long skuId);

    /**
     * 查询MallSkuInfo
     * 
     * @param 查询自增主键SkuId
     * @return 查询的MallSkuInfo结果
     */
    public MallSkuInfo select(Long skuId);
    
    /**
     * 根据itemid查询
     * @param itemid
     * @returnList<MallSkuInfo>
     */
    public List<MallSkuInfo> selectByItemId(Long itemid);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @returnList<MallSkuInfo>
     */
    public List<MallSkuInfo> batchSelectByItemIdList(List<Long> idsList);
}
