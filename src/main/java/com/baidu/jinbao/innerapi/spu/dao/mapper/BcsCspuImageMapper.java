package com.baidu.jinbao.innerapi.spu.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;

public interface BcsCspuImageMapper extends GenericMapper<BcsCspuImage, Long> {
    /**
     * 批量插入BcsCspuImage
     * 
     * @param bcsCspuImages 插入bcsCspuImage列表
     * @return 插入条数
     */
    public Integer batchInsert(List<BcsCspuImage> bcsCspuImages);

    /**
     * 批量更新BcsCspuImage
     * 
     * @param bcsCspuImages 更新bcsCspuImage列表
     * @return 更新条数
     */
    public Integer batchUpdate(List<BcsCspuImage> bcsCspuImages);

    /**
     * 批量删除BcsCspuImage
     * 
     * @param bcsCspuImageIds 删除BcsCspuImageId列表
     * @return 删除条数
     */
    public Integer batchDelete(List<Long> bcsCspuImageIds);

    /**
     * 批量查询BcsCspuImage
     * 
     * @param bcsCspuImageIds 查询BcsCspuImage Id 列表
     * @return BcsCspuImage列表
     */
    public List<BcsCspuImage> batchSelect(List<Long> bcsCspuImageIds);
}
