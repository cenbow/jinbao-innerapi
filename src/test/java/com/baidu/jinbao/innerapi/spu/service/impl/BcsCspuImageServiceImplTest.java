package com.baidu.jinbao.innerapi.spu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.baidu.jinbao.innerapi.spu.bo.BcsCspuImage;
import com.baidu.jinbao.innerapi.spu.dao.BcsCspuImageDao;

@Service
public class BcsCspuImageServiceImplTest {
    @Tested
    private BcsCspuImageServiceImpl bcsCspuImageService;

    @Injectable
    private BcsCspuImageDao bcsCspuImageDao;

    @Test
    public void testFindById() {
        new NonStrictExpectations() {
            {
                bcsCspuImageDao.selectByPrimaryKey(anyLong);
                result = new BcsCspuImage();
            }
        };
        BcsCspuImage ret = bcsCspuImageService.findById(1L);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchInsert() {
        new NonStrictExpectations() {
            {
                bcsCspuImageDao.batchInsert((List<BcsCspuImage>) any);
            }
        };
        List<BcsCspuImage> bcsCspuImages = new ArrayList<BcsCspuImage>();
        BcsCspuImage bcsCspuImage1 = new BcsCspuImage();
        bcsCspuImage1.setCspuid(1L);
        BcsCspuImage bcsCspuImage2 = new BcsCspuImage();
        bcsCspuImage2.setCspuid(2L);
        bcsCspuImages.add(bcsCspuImage1);
        bcsCspuImages.add(bcsCspuImage2);
        this.bcsCspuImageDao.batchInsert(bcsCspuImages);
    }

    @Test
    public void testBatchUpdate() {
        new NonStrictExpectations() {
            {
                bcsCspuImageDao.batchUpdate((List<BcsCspuImage>) any);
            }
        };
        List<BcsCspuImage> bcsCspuImages = new ArrayList<BcsCspuImage>();
        BcsCspuImage bcsCspuImage1 = new BcsCspuImage();
        bcsCspuImage1.setCspuid(1L);
        BcsCspuImage bcsCspuImage2 = new BcsCspuImage();
        bcsCspuImage2.setCspuid(2L);
        bcsCspuImages.add(bcsCspuImage1);
        bcsCspuImages.add(bcsCspuImage2);
        this.bcsCspuImageDao.batchUpdate(bcsCspuImages);
    }

    @Test
    public void testBatchSelect() {
        new NonStrictExpectations() {
            {
                bcsCspuImageDao.batchSelect((List<Long>) any);
                result = new ArrayList<BcsCspuImage>();
            }
        };
        List<Long> bcsCspuImageIds = new ArrayList<Long>();
        bcsCspuImageIds.add(1L);
        List<BcsCspuImage> ret = bcsCspuImageDao.batchSelect(bcsCspuImageIds);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testBatchDelete() {
        new NonStrictExpectations() {
            {
                bcsCspuImageDao.batchDelete((List<Long>) any);
            }
        };
        List<Long> bcsCspuImageIds = new ArrayList<Long>();
        bcsCspuImageIds.add(2L);
        bcsCspuImageIds.add(3L);
        this.bcsCspuImageDao.batchDelete(bcsCspuImageIds);
    }
}
