package com.baidu.jinbao.mall.item.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.MallSkuPps;

public interface MallSkuPpsMapper extends GenericMapper<MallSkuPps, Long> {
    /**
     * 插入MallSkuPps
     * 
     * @param 插入MallSkuPps
     * @return 插入条数
     */
    public int insertOne(MallSkuPps mallSkuPps);

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
     * @param 删除增主键ppsMd5
     * @return 删除条数
     */
    public int delete(String ppsMd5);

    /**
     * 查询MallSkuPps
     * 
     * @param 查询ppsMd5
     * @return 查询的MallSkuPps结果
     */
    public MallSkuPps select(String ppsMd5);

    /**
     * 批量更新MallSkuPps
     * 
     * @param 批量更新MallSkuPps
     * @return 更新条数
     */
    public int batchUpdate(List<MallSkuPps> mallSkuPps);
    
    /**
     * 批量插入MallSkuPps
     * 
     * @param 批量插入MallSkuPps
     * @return 插入条数
     */
    public int batchInsert(List<MallSkuPps> mallSkuPpsList);
    
    /**
     * 根据skuid List查询
     * @param skuIds
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> batchSelectBySkuIdList(List<Long> skuIdList);
    
    /**
     * 根据skuId查询
     * @param skuid
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> selectBySkuId (Long skuid);
    
    /**
     * 根据 skuid,regionid,device组合的list返回查询结果
     * @param  List<String> ppsmd5
     * @return List<MallSkuPps>
     */
    public List<MallSkuPps> selectByPpsMd5(List<String> ppsMd5List);
}