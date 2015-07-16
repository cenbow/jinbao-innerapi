package com.baidu.jinbao.mall.item.dao;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.mall.item.bo.ItemInfo;

import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

import java.util.List;


public interface ItemInfoDao extends GenericMapperDao<ItemInfo, Long> {
    /**
     * 插入itemInfo
     * 
     * @param 插入itemInfo
     * @return 插入后获得自增id
     */
    public Long insertOne(ItemInfo itemInfo);

    /**
     * 更新itemInfo
     * 
     * @param 更新itemInfo
     * @return 更新条数
     */
    public int update(ItemInfo itemInfo);

    /**
     * 删除itemInfo
     * 
     * @param 删除itemId
     * @return 删除条数
     */
    public int delete(Long itemId);

    /**
     * 查询itemInfo
     * 
     * @param 查询itemId
     * @return 查询的ItemInfo结果
     */
    public ItemInfo select(Long itemId);
    
    /**
     * 查询itemInfo
     * 
     * @param 查询hash_code
     * @return 查询的ItemInfo结果list
     */

    public List<ItemInfo> selectByHashCode(Integer hashCode);
    /**
     * 更新itemInfo
     * 
     * @param 更新itemInfos
     * @return 更新条数
     */
    public int batchUpdate(List<ItemInfo> itemInfos);
    
    /**
     * 通过itemIds查询
     * @param idsList
     * @returnList<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
    
    /**
     * 获取Item相关信息: 支持排序（按照价格、上架时间）
     * @param pageItemVo 查询条件
     * @return ItemTotalInfoVo
     */
    public List<PageItemTotalInfoVo> selectPageItemTotalInfo(PageItemVo pageItemVo);

}
