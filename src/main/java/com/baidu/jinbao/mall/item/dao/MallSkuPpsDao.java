package com.baidu.jinbao.mall.item.dao;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;

import java.util.List;

public interface MallSkuPpsDao extends GenericMapperDao<MallSkuPps, Long> {
    /**
     * 插入MallSkuPps
     * 
     * @param 插入MallSkuPps
     * @return 插入后获得自增id
     */
    public Long insertOne(MallSkuPps mallSkuPps);

    /**
     * 更新MallSkuPps
     * 
     * @param 更新MallSkuPps
     * @return 更新条数
     */
    public int update(MallSkuPps mallSkuPps);

    /**
     * 删除MallSkuPps
     * 
     * @param 删除增ppsMd5
     * @return 删除条数
     */
    public int delete(String ppsMd5);

    /**
     * 查询MallSkuPps
     * 
     * @param 查询自增主键ppsMd5
     * @return 查询的MallSkuPps结果
     */
    public MallSkuPps select(String ppsMd5);

    /**
     * 批量更新MallSkuPps
     * 
     * @param 批量更新MallSkuPps
     * @return 更新条数
     */
    public int batchUpdate(List<MallSkuPps> mallSkuPpsList);

    /**
     * 批量插入MallSkuPps
     * 
     * @param 批量插入MallSkuPps
     * @return 插入条数
     */
    public int batchInsert(List<MallSkuPps> mallSkuPpsList);
    
    /**
     * 根据skuidList 查询
     * @param skuIdList
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> batchSelectBySkuIdList(List<Long> skuIdList);
    
    /**
     * 根据skuid查询
     * @param skuid
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> selectBySkuId (Long skuid);
    
    /**
     * 根据 skuid,regionid,device组合的ppsmd5 查询
     * @param  ppsMdtList list
     * @return MallSkuPps list
     */
    public List<MallSkuPps> selectByPpsMd5(List<String> ppsMd5List);
}
