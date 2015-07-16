package com.baidu.jinbao.mall.item.dao.mapper;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.mall.item.bo.ItemInfo;
import com.baidu.jinbao.mall.item.vo.PageItemTotalInfoVo;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemInfoMapper extends GenericMapper<ItemInfo, Long> {
    /**
     * 插入itemInfo
     * 
     * @param 插入itemInfo
     * @return 插入条数
     */
    public int insertOne(ItemInfo itemInfo);

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
     * @return 查询的ItemInfo结果
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
     * 获取Item相关信息: 支持排序（按照价格、上架时间）
     * 
     * @param pageItemVo 查询条件
     * @return ItemTotalInfoVo
     */
    public List<PageItemTotalInfoVo> selectPageItemTotalInfo(@Param("condition") PageItemVo pageItemVo);

    /**
     * 根据list获取
     * @param idsList
     * @return List<ItemInfo>
     */
    public List<ItemInfo> batchSelectByItemIdList(List<Long> idsList);
}