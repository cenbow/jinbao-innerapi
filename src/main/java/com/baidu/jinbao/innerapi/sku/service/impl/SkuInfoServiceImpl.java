/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.jinbao.innerapi.sku.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.jinbao.innerapi.common.constants.PdsConstants;
import com.baidu.jinbao.innerapi.common.transaction.SplitModule;
import com.baidu.jinbao.innerapi.sku.bo.SkuInfo;
import com.baidu.jinbao.innerapi.sku.bo.SkuAttribute;
import com.baidu.jinbao.innerapi.sku.bo.SkuCdt;
import com.baidu.jinbao.innerapi.sku.bo.SkuComment;
import com.baidu.jinbao.innerapi.sku.bo.SkuCspu;
import com.baidu.jinbao.innerapi.sku.bo.SkuDescription;
import com.baidu.jinbao.innerapi.sku.bo.SkuLevel;
import com.baidu.jinbao.innerapi.sku.bo.SkuPps;
import com.baidu.jinbao.innerapi.sku.dao.SkuInfoDao;
import com.baidu.jinbao.innerapi.sku.service.SkuAttributeService;
import com.baidu.jinbao.innerapi.sku.service.SkuCdtService;
import com.baidu.jinbao.innerapi.sku.service.SkuCommentService;
import com.baidu.jinbao.innerapi.sku.service.SkuCspuService;
import com.baidu.jinbao.innerapi.sku.service.SkuDescriptionService;
import com.baidu.jinbao.innerapi.sku.service.SkuInfoService;
import com.baidu.jinbao.innerapi.sku.service.SkuLevelService;
import com.baidu.jinbao.innerapi.sku.service.SkuPpsService;
import com.baidu.jinbao.innerapi.sku.util.SignatureUtil;
import com.baidu.jinbao.innerapi.sku.vo.SkuQueryCondition;
import com.baidu.jinbao.innerapi.sku.vo.SkuTotalInfoVo;

@Service("skuInfoService")
@SplitModule(moduleName = PdsConstants.SKU_MODULE_DATASOURCE_KEY)
public class SkuInfoServiceImpl extends BaseService<SkuInfo, Long> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuAttributeService skuAttributeService;
    @Autowired
    private SkuCdtService skuCdtService;
    @Autowired
    private SkuCommentService skuCommentService;
    @Autowired
    private SkuCspuService skuCspuService;
    @Autowired
    private SkuDescriptionService skuDescriptionService;
    @Autowired
    private SkuLevelService skuLevelService;
    @Autowired
    private SkuPpsService skuPpsService;

    @Override
    public GenericMapperDao<SkuInfo, Long> getDao() {
        return skuInfoDao;
    }

    @Override
    public Integer insertRecord(String splitDbInfo, SkuInfo skuInfo) {
        if (splitDbInfo == null || skuInfo == null) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        skuInfo.setSignature(SignatureUtil.setSignature(SignatureUtil.DEFAULT_VALUE, skuInfo.signature(),
                SignatureUtil.SKU_INFO));
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        skuInfoList.add(skuInfo);
        if (CollectionUtils.isEmpty(filter(splitDbInfo, skuInfoList))) {
            return 0;
        }
        return skuInfoDao.insertWithSplitNumber(splitNumber, skuInfo);
    }

    @Override
    public Integer insertRecords(String splitDbInfo, List<SkuInfo> skuInfoList) {
        if (splitDbInfo == null || CollectionUtils.isEmpty(skuInfoList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        for (SkuInfo skuInfo : skuInfoList) {
            skuInfo.setSignature(SignatureUtil.setSignature(SignatureUtil.DEFAULT_VALUE, skuInfo.signature(),
                    SignatureUtil.SKU_INFO));
        }
        return this.skuInfoDao.batchInsert(splitNumber, filter(splitDbInfo, skuInfoList));
    }

    @Override
    public Integer updateRecords(String splitDbInfo, List<SkuInfo> skuInfoList) {
        if (splitDbInfo == null || CollectionUtils.isEmpty(skuInfoList)) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        List<String> skuIdList = new ArrayList<String>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuIdList.add(skuInfo.getSkuid());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuInfo> queryList = this.skuInfoDao.batchSelect(splitNumber, condition);
        Map<String, SkuInfo> queryMap = new HashMap<String, SkuInfo>();
        for (SkuInfo skuInfo : queryList) {
            queryMap.put(skuInfo.getSkuid(), skuInfo);
        }
        for (SkuInfo skuInfo : skuInfoList) {
            if (queryMap.containsKey(skuInfo.getSkuid())) {
                // 与更新前的初始值合并设置签名
                skuInfo.mergeValue(queryMap.get(skuInfo.getSkuid()));
                skuInfo.setSignature(SignatureUtil.setSignature(SignatureUtil.DEFAULT_VALUE, skuInfo.signature(),
                        SignatureUtil.SKU_INFO));
            }
        }
        return this.skuInfoDao.batchUpdate(splitNumber, skuInfoList);
    }

    @Override
    public Integer deleteRecords(String splitDbInfo, SkuQueryCondition condition) {
        if (splitDbInfo == null || condition == null) {
            return 0;
        }

        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuInfoDao.batchDelete(splitNumber, condition);
    }

    @Override
    public List<SkuInfo> getRecords(String splitDbInfo, SkuQueryCondition condition) {
        if (splitDbInfo == null || condition == null) {
            return new ArrayList<SkuInfo>();
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuInfoDao.batchSelect(splitNumber, condition);
    }

    @Override
    public List<SkuInfo> getRecordsBySkuHashkey(String splitDbInfo, List<Long> skuHashKeyList) {
        if (splitDbInfo == null || CollectionUtils.isEmpty(skuHashKeyList)) {
            return new ArrayList<SkuInfo>();
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        return this.skuInfoDao.batchSelectBySkuHashkey(splitNumber, skuHashKeyList);
    }

    @Override
    public Integer insertSkuTotalInfo(String splitDbInfo, SkuTotalInfoVo vo) {
        if (splitDbInfo == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        if (vo.getSkuInfo() == null) {
            return 0;
        }
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        skuInfoList.add(vo.getSkuInfo());
        if (CollectionUtils.isEmpty(filter(splitDbInfo, skuInfoList))) {
            return 0;
        }
        // 设置SkuInfo签名
        vo.getSkuInfo().setSignature(SignatureUtil.setSignature(SignatureUtil.DEFAULT_VALUE, 
                vo.getSkuInfo().signature(), SignatureUtil.SKU_INFO));
        // 设置全量数据签名
        setTotalSignature(vo);
        // 插入SkuInfo
        this.skuInfoDao.insertWithSplitNumber(splitNumber, vo.getSkuInfo());

        // 设置外建sku_innerid，联表插入
        List<SkuAttribute> skuAttributeList = new ArrayList<SkuAttribute>();
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        List<SkuCspu> skuCspuList = new ArrayList<SkuCspu>();
        List<SkuDescription> skuDescriptionList = new ArrayList<SkuDescription>();
        List<SkuLevel> skuLevelList = new ArrayList<SkuLevel>();
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        if (vo.getSkuAttribute() != null) {
            vo.getSkuAttribute().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuAttributeList.add(vo.getSkuAttribute());
            this.skuAttributeService.directInsertRecords(splitDbInfo, skuAttributeList);
        }
        if (vo.getSkuCdt() != null) {
            vo.getSkuCdt().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuCdtList.add(vo.getSkuCdt());
            this.skuCdtService.directInsertRecords(splitDbInfo, skuCdtList);
        }
        if (vo.getSkuComment() != null) {
            vo.getSkuComment().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuCommentList.add(vo.getSkuComment());
            this.skuCommentService.directInsertRecords(splitDbInfo, skuCommentList);
        }
        if (vo.getSkuCspu() != null) {
            vo.getSkuCspu().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuCspuList.add(vo.getSkuCspu());
            this.skuCspuService.directInsertRecords(splitDbInfo, skuCspuList);
        }
        if (vo.getSkuDescription() != null) {
            vo.getSkuDescription().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuDescriptionList.add(vo.getSkuDescription());
            this.skuDescriptionService.directInsertRecords(splitDbInfo, skuDescriptionList);
        }
        if (vo.getSkuLevel() != null) {
            vo.getSkuLevel().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuLevelList.add(vo.getSkuLevel());
            this.skuLevelService.directInsertRecords(splitDbInfo, skuLevelList);
        }
        if (vo.getSkuPps() != null) {
            vo.getSkuPps().setSkuInnerid(vo.getSkuInfo().getSkuInnerid());
            skuPpsList.add(vo.getSkuPps());
            this.skuPpsService.directInsertRecords(splitDbInfo, skuPpsList);
        }
        return 1;
    }

    @Override
    public Integer insertSkuTotalInfoList(String splitDbInfo, List<SkuTotalInfoVo> voList) {
        if (splitDbInfo == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);
        // 过滤重复元素
        voList = filterTotalInfo(splitDbInfo, voList);
        List<SkuInfo> skuInfoList = new ArrayList<SkuInfo>();
        for (SkuTotalInfoVo vo : voList) {
            if (vo.getSkuInfo() != null) {
                skuInfoList.add(vo.getSkuInfo());
            }
        }
        if (skuInfoList.size() != voList.size()) {
            return 0;
        }
        Integer successNum = 0;
        // 设置全量数据签名
        setTotalListSignature(voList);
        // 插入SkuInfo List
        successNum = this.skuInfoDao.batchInsert(splitNumber, skuInfoList);
        // 插入后通过skuId获取 主键sku_innerid
        List<String> skuIdList = new ArrayList<String>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuIdList.add(skuInfo.getSkuid());
        }
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        skuInfoList = this.getRecords(splitDbInfo, condition);

        Map<String, SkuInfo> skuInfoMap = new HashMap<String, SkuInfo>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuInfoMap.put(skuInfo.getSkuid(), skuInfo);
        }

        // 设置外建sku_innerid，联表插入
        List<SkuAttribute> skuAttributeList = new ArrayList<SkuAttribute>();
        List<SkuCdt> skuCdtList = new ArrayList<SkuCdt>();
        List<SkuComment> skuCommentList = new ArrayList<SkuComment>();
        List<SkuCspu> skuCspuList = new ArrayList<SkuCspu>();
        List<SkuDescription> skuDescriptionList = new ArrayList<SkuDescription>();
        List<SkuLevel> skuLevelList = new ArrayList<SkuLevel>();
        List<SkuPps> skuPpsList = new ArrayList<SkuPps>();
        successNum.byteValue();
        for (SkuTotalInfoVo vo : voList) {
            SkuAttribute skuAttribute = vo.getSkuAttribute();
            if (skuAttribute != null && skuInfoMap.containsKey(skuAttribute.getSkuid())) {
                skuAttribute.setSkuInnerid(skuInfoMap.get(skuAttribute.getSkuid()).getSkuInnerid());
                skuAttributeList.add(skuAttribute);
                successNum = Math.min(successNum, 
                        this.skuAttributeService.directInsertRecords(splitDbInfo, skuAttributeList));
            }
            SkuCdt skuCdt = vo.getSkuCdt();
            if (skuCdt != null && skuInfoMap.containsKey(skuAttribute.getSkuid())) {
                skuCdt.setSkuInnerid(skuInfoMap.get(skuCdt.getSkuid()).getSkuInnerid());
                skuCdtList.add(skuCdt);
                successNum = Math.min(successNum, 
                        this.skuCdtService.directInsertRecords(splitDbInfo, skuCdtList));
            }
            SkuComment skuComment = vo.getSkuComment();
            if (skuComment != null && skuInfoMap.containsKey(skuComment.getSkuid())) {
                skuComment.setSkuInnerid(skuInfoMap.get(skuComment.getSkuid()).getSkuInnerid());
                skuCommentList.add(skuComment);
                successNum = Math.min(successNum, 
                        this.skuCommentService.directInsertRecords(splitDbInfo, skuCommentList));
            }
            SkuCspu skuCspu = vo.getSkuCspu();
            if (skuCspu != null && skuInfoMap.containsKey(skuCspu.getSkuid())) {
                skuCspu.setSkuInnerid(skuInfoMap.get(skuCspu.getSkuid()).getSkuInnerid());
                skuCspuList.add(skuCspu);
                successNum = Math.min(successNum, 
                        this.skuCspuService.directInsertRecords(splitDbInfo, skuCspuList));
            }
            SkuDescription skuDescription = vo.getSkuDescription();
            if (skuDescription != null && skuInfoMap.containsKey(skuDescription.getSkuid())) {
                skuDescription.setSkuInnerid(skuInfoMap.get(skuDescription.getSkuid()).getSkuInnerid());
                skuDescriptionList.add(skuDescription);
                successNum = Math.min(successNum, 
                        this.skuDescriptionService.directInsertRecords(splitDbInfo, skuDescriptionList));
            }
            SkuLevel skuLevel = vo.getSkuLevel();
            if (skuLevel != null && skuInfoMap.containsKey(skuLevel.getSkuid())) {
                skuLevel.setSkuInnerid(skuInfoMap.get(skuLevel.getSkuid()).getSkuInnerid());
                skuLevelList.add(skuLevel);
                successNum = Math.min(successNum, 
                        this.skuLevelService.directInsertRecords(splitDbInfo, skuLevelList));
            }
            SkuPps skuPps = vo.getSkuPps();
            if (skuPps != null && skuInfoMap.containsKey(skuPps.getSkuid())) {
                skuPps.setSkuInnerid(skuInfoMap.get(skuPps.getSkuid()).getSkuInnerid());
                skuPpsList.add(skuPps);
                successNum = Math.min(successNum, 
                        this.skuPpsService.directInsertRecords(splitDbInfo, skuPpsList));
            }
        }
        return successNum;
    }

    @Override
    public Integer updateInsert(String splitDbInfo, SkuTotalInfoVo vo) {
        if (splitDbInfo == null || vo.getSkuInfo() == null) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        // 获取对应的SkuInfo表数据
        List<String> skuIdList = new ArrayList<String>();
        skuIdList.add(vo.getSkuInfo().getSkuid());
        SkuQueryCondition condition = new SkuQueryCondition();
        condition.setSkuIdList(skuIdList);
        List<SkuInfo> skuInfoList = this.skuInfoDao.batchSelect(splitNumber, condition);
        // 如果不存在则全量插入
        if (CollectionUtils.isEmpty(skuInfoList)) {
            if (vo.getSkuInfo() != null) {
                vo.getSkuInfo().setDefaultValue();
            }
            if (vo.getSkuAttribute() != null) {
                vo.getSkuAttribute().setDefaultValue();
            }
            if (vo.getSkuDescription() != null) {
                vo.getSkuDescription().setDefaultValue();
            }
            if (vo.getSkuLevel() != null) {
                vo.getSkuLevel().setDefaultValue();
            }
            if (vo.getSkuCspu() != null) {
                vo.getSkuCspu().setDefaultValue();
            }
            if (vo.getSkuCdt() != null) {
                vo.getSkuCdt().setDefaultValue();
            }
            if (vo.getSkuPps() != null) {
                vo.getSkuPps().setDefaultValue();
            }
            if (vo.getSkuComment() != null) {
                vo.getSkuComment().setDefaultValue();
            }
            return this.insertSkuTotalInfo(splitDbInfo, vo);
        }
        boolean updateSignature = false;
        // 获取到的skuInfo表
        SkuInfo skuInfo = skuInfoList.get(0);
        // SkuAttribute插入或更新
        SkuAttribute skuAttribute = vo.getSkuAttribute();
        if (skuAttribute != null && skuInfo.getSkuid().equals(skuAttribute.getSkuid())) {
            // 设置外建sku_innerid
            skuAttribute.setSkuInnerid(skuInfo.getSkuInnerid());
            // 不存在则插入
            if ("".equals(SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_ATTRIBUTE))) {
                List<SkuAttribute> insertList = new ArrayList<SkuAttribute>();
                skuAttribute.setDefaultValue();
                insertList.add(skuAttribute);
                this.skuAttributeService.directInsertRecords(splitDbInfo, insertList);
                updateSignature = true;
            } else {
                // 获取数据库中的值
                List<SkuAttribute> queryList = this.skuAttributeService.getRecords(splitDbInfo, condition);
                // 合并值，并计算property_hash
                skuAttribute.mergeValue(queryList.size() >= 1 ? queryList.get(0) : null);
                // 存在且签名不相同则更新
                if (!skuAttribute.signature().equals(
                        SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_ATTRIBUTE))) {

                    this.skuAttributeService.updateBySkuId(splitDbInfo, skuAttribute);
                    updateSignature = true;
                }
            }
        }
        // SkuDescription插入或更新
        SkuDescription skuDescription = vo.getSkuDescription();
        if (skuDescription != null && skuInfo.getSkuid().equals(skuDescription.getSkuid())) {
            // 设置外建sku_innerid
            skuDescription.setSkuInnerid(skuInfo.getSkuInnerid());
            // 不存在则插入
            if ("".equals(SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_DESCRIPTION))) {
                List<SkuDescription> insertList = new ArrayList<SkuDescription>();
                skuDescription.setDefaultValue();
                insertList.add(skuDescription);
                this.skuDescriptionService.directInsertRecords(splitDbInfo, insertList);
                updateSignature = true;
            }
            else {
                // 获取数据库中的值
                List<SkuDescription> queryList = this.skuDescriptionService.getRecords(splitDbInfo, condition);
                // 合并值
                skuDescription.mergeValue(queryList.size() >= 1 ? queryList.get(0) : null);
                // 存在且签名不相同则更新
                if (!skuDescription.signature().equals(
                        SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_DESCRIPTION))) {
                    this.skuDescriptionService.updateBySkuId(splitDbInfo, skuDescription);
                    updateSignature = true;
                }
            }
        }
        // SkuLevel插入或更新
        SkuLevel skuLevel = vo.getSkuLevel();
        if (skuLevel != null && skuInfo.getSkuid().equals(skuLevel.getSkuid())) {
            // 设置外建sku_innerid
            skuLevel.setSkuInnerid(skuInfo.getSkuInnerid());
            // 不存在则插入
            if ("".equals(SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_LEVEL))) {
                List<SkuLevel> insertList = new ArrayList<SkuLevel>();
                skuLevel.setDefaultValue();
                insertList.add(skuLevel);
                this.skuLevelService.directInsertRecords(splitDbInfo, insertList);
                updateSignature = true;
            }
            else {
                // 获取数据库中的值
                List<SkuLevel> queryList = this.skuLevelService.getRecords(splitDbInfo, condition);
                skuLevel.mergeValue(queryList.size() >= 1 ? queryList.get(0) : null);
                // 存在且签名不相同则更新
                if (!skuLevel.signature().equals(
                        SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_LEVEL))) {
                    this.skuLevelService.updateBySkuId(splitDbInfo, skuLevel);
                    updateSignature = true;
                }
            }
        }
        // SkuPps插入
        SkuPps skuPps = vo.getSkuPps();
        if (skuPps != null && skuInfo.getSkuid().equals(skuPps.getSkuid())) {
            // 设置外建sku_innerid
            skuPps.setSkuInnerid(skuInfo.getSkuInnerid());
            this.skuPpsService.updateInsertRecord(splitDbInfo, skuPps);
        }
        // SkuCdt插入
        SkuCdt skuCdt = vo.getSkuCdt();
        if (skuCdt != null && skuInfo.getSkuid().equals(skuCdt.getSkuid())) {
            // 设置外建sku_innerid
            skuCdt.setSkuInnerid(skuInfo.getSkuInnerid());
            this.skuCdtService.updateInsertRecord(splitDbInfo, skuCdt);
        }
        // SkuCspu插入
        SkuCspu skuCspu = vo.getSkuCspu();
        if (skuCspu != null && skuInfo.getSkuid().equals(skuCspu.getSkuid())) {
            // 设置外建sku_innerid
            skuCspu.setSkuInnerid(skuInfo.getSkuInnerid());
            this.skuCspuService.updateInsertRecord(splitDbInfo, skuCspu);
        }
        // SkuComment插入
        SkuComment skuComment = vo.getSkuComment();
        if (skuComment != null && skuInfo.getSkuid().equals(skuComment.getSkuid())) {
            // 设置外建sku_innerid
            skuComment.setSkuInnerid(skuInfo.getSkuInnerid());
            this.skuCommentService.updateInsertRecord(splitDbInfo, skuComment);
        }
        // 与更新前的初始值合并设置签名
        vo.getSkuInfo().mergeValue(skuInfo);
        if (!vo.getSkuInfo().signature()
                .equals(SignatureUtil.parseSignature(skuInfo.getSignature(), SignatureUtil.SKU_INFO))) {
            updateSignature = true;
        }
        if (updateSignature) {
            // SkuInfo存在且字段修改或签名修改则更新, 并更新skuInfo签名字段
            vo.getSkuInfo().setSkuInnerid(skuInfo.getSkuInnerid());
            vo.getSkuInfo().setSignature(
                    SignatureUtil.setSignature(skuInfo.getSignature(), vo.getSkuInfo().signature(),
                            SignatureUtil.SKU_INFO));
            setTotalSignature(vo);
            // 更新SkuInfo，以及其他表的签名
            this.skuInfoDao.updateBySkuId(splitNumber, vo.getSkuInfo());
        }

        return 1;
    }

    @Override
    public Integer updateSignature(String splitDbInfo, List<String> skuIdList, 
            List<String> signatureList, int tableId) {
        if (splitDbInfo == null) {
            return 0;
        }
        if (CollectionUtils.isEmpty(skuIdList) || CollectionUtils.isEmpty(signatureList)
                || skuIdList.size() != signatureList.size()) {
            return 0;
        }
        // 获取splitDbInfo中对应的分片序号(eg: db1_1)
        Integer splitNumber = Integer.valueOf(splitDbInfo.split("_")[1]);

        SkuQueryCondition conditon = new SkuQueryCondition();
        conditon.setSkuIdList(skuIdList);
        List<SkuInfo> querySkuInfoList = this.skuInfoDao.batchSelect(splitNumber, conditon);
        Map<String, SkuInfo> querySkuInfoMap = new HashMap<String, SkuInfo>();
        for (SkuInfo skuInfo : querySkuInfoList) {
            querySkuInfoMap.put(skuInfo.getSkuid(), skuInfo);
        }
        List<SkuInfo> updateList = new ArrayList<SkuInfo>();
        for (int i = 0; i < skuIdList.size(); ++i) {
            if (querySkuInfoMap.containsKey(skuIdList.get(i))) {
                SkuInfo skuInfo = querySkuInfoMap.get(skuIdList.get(i));
                skuInfo.setSignature(SignatureUtil.setSignature(skuInfo.getSignature(), signatureList.get(i), tableId));
                updateList.add(skuInfo);
            }
        }
        return this.skuInfoDao.batchUpdate(splitNumber, updateList);
    }

    private List<SkuInfo> filter(String splitDbInfo, List<SkuInfo> skuInfoList) {
        List<String> skuIdList = new ArrayList<String>();
        for (SkuInfo skuInfo : skuInfoList) {
            skuIdList.add(skuInfo.getSkuid());
        }
        SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
        skuQueryCondition.setSkuIdList(skuIdList);
        List<SkuInfo> querySkuInfoList = this.getRecords(splitDbInfo, skuQueryCondition);
        Map<String, SkuInfo> querySkuInfoMap = new HashMap<String, SkuInfo>();
        for (SkuInfo skuInfo : querySkuInfoList) {
            querySkuInfoMap.put(skuInfo.getSkuid(), skuInfo);
        }

        List<SkuInfo> ret = new ArrayList<SkuInfo>();
        for (SkuInfo skuInfo : skuInfoList) {
            if (!querySkuInfoMap.containsKey(skuInfo.getSkuid())) {
                ret.add(skuInfo);
            }
        }
        return ret;
    }

    private List<SkuTotalInfoVo> filterTotalInfo(String splitDbInfo, List<SkuTotalInfoVo> skuTotalInfoVoList) {
        List<String> skuIdList = new ArrayList<String>();
        for (SkuTotalInfoVo vo : skuTotalInfoVoList) {
            skuIdList.add(vo.getSkuInfo().getSkuid());
        }
        SkuQueryCondition skuQueryCondition = new SkuQueryCondition();
        skuQueryCondition.setSkuIdList(skuIdList);
        List<SkuInfo> querySkuInfoList = this.getRecords(splitDbInfo, skuQueryCondition);
        Map<String, SkuInfo> querySkuInfoMap = new HashMap<String, SkuInfo>();
        for (SkuInfo skuInfo : querySkuInfoList) {
            querySkuInfoMap.put(skuInfo.getSkuid(), skuInfo);
        }

        List<SkuTotalInfoVo> ret = new ArrayList<SkuTotalInfoVo>();
        for (SkuTotalInfoVo vo : skuTotalInfoVoList) {
            if (!querySkuInfoMap.containsKey(vo.getSkuInfo().getSkuid())) {
                ret.add(vo);
            }
        }
        return ret;
    }

    private void setTotalSignature(SkuTotalInfoVo vo) {
        String signature = vo.getSkuInfo().getSignature();
        if (vo.getSkuAttribute() != null) {
            signature =
                    SignatureUtil
                            .setSignature(signature, vo.getSkuAttribute().signature(), SignatureUtil.SKU_ATTRIBUTE);
        }
        if (vo.getSkuDescription() != null) {
            signature =
                    SignatureUtil.setSignature(signature, vo.getSkuDescription().signature(),
                            SignatureUtil.SKU_DESCRIPTION);
        }
        if (vo.getSkuLevel() != null) {
            signature = SignatureUtil.setSignature(signature, vo.getSkuLevel().signature(), SignatureUtil.SKU_LEVEL);
        }
        vo.getSkuInfo().setSignature(signature);
    }
    
    private void setTotalListSignature(List<SkuTotalInfoVo> voList) {
        for (SkuTotalInfoVo vo : voList) {
            String signature = vo.getSkuInfo().getSignature();
            if (vo.getSkuAttribute() != null) {
                signature =
                        SignatureUtil.setSignature(signature, vo.getSkuAttribute().signature(),
                                SignatureUtil.SKU_ATTRIBUTE);
            }
            if (vo.getSkuDescription() != null) {
                signature =
                        SignatureUtil.setSignature(signature, vo.getSkuDescription().signature(),
                                SignatureUtil.SKU_DESCRIPTION);
            }
            if (vo.getSkuLevel() != null) {
                signature =
                        SignatureUtil.setSignature(signature, vo.getSkuLevel().signature(), SignatureUtil.SKU_LEVEL);
            }
            vo.getSkuInfo().setSignature(signature);
        }
    }
}
