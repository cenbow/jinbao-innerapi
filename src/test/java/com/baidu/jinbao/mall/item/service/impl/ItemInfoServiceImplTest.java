package com.baidu.jinbao.mall.item.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.jinbao.innerapi.base.AbstractDAOTests;
import com.baidu.jinbao.mall.item.dao.ItemInfoDao;
import com.baidu.jinbao.mall.item.vo.PageItemVo;

public class ItemInfoServiceImplTest extends AbstractDAOTests {

    @Tested
    private ItemInfoServiceImpl itemInfoService;

    @Injectable
    private ItemInfoDao itemInfoDao;

    @Test
    public void testGetPageItemTotalInfo() {
        new NonStrictExpectations() {
            {
                itemInfoDao.selectPageItemTotalInfo((PageItemVo) any);
                result = new ArrayList<PageItemVo>();
            }
        };
        PageItemVo pageItemVo = new PageItemVo();
        pageItemVo.setPageSize(5);
        pageItemVo.setPageNumber(1);
        pageItemVo.setLeafcategoryidList(Arrays.asList(2L, 3L));
        pageItemVo.setShopIdList(Arrays.asList(1L));
        pageItemVo.setManualStatus(new Integer(0).byteValue());
        pageItemVo.setMerchantStatus(new Integer(0).byteValue());

        itemInfoService.getPageItemTotalInfo(pageItemVo);
        Assert.assertTrue(true);
    }

}
