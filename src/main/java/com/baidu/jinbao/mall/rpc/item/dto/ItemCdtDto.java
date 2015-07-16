package com.baidu.jinbao.mall.rpc.item.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ItemCdtDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * 商品内部id
     */
    @Protobuf(order = 2)
    private Long itemid;

    /**
     * 叶子类目ID
     */
    @Protobuf(order = 3)
    private Long leafcategoryid;

    /**
     * 类目来源 0运营修改，1：商家修改，2：机器学习
     */
    @Protobuf(order = 4)
    private Integer classificationtype;

    /**
     * 商家或者运营的UCID，机器为0
     */
    @Protobuf(order = 5)
    private Long operator;

    /**
     * 取值0-100，可信度，默认100
     */
    @Protobuf(order = 6)
    private Integer confidence;

    /**
     * 添加时间
     */
    @Protobuf(order = 7)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 8)
    private String updatetime;

    /**
     * 对item_id+classificationtype做MD5，用于重复判断
     */
    @Protobuf(order = 9)
    private String cdtMd5;

    /**
     * 自增id
     * 
     * @param id the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 自增id
     * 
     * @return id the value for id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 商品内部id
     * 
     * @param itemid the value for itemid
     */
    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    /**
     * 商品内部id
     * 
     * @return itemid the value for itemid
     */
    public Long getItemid() {
        return this.itemid;
    }

    /**
     * 叶子类目ID
     * 
     * @param leafcategoryid the value for leafcategoryid
     */
    public void setLeafcategoryid(Long leafcategoryid) {
        this.leafcategoryid = leafcategoryid;
    }

    /**
     * 叶子类目ID
     * 
     * @return leafcategoryid the value for leafcategoryid
     */
    public Long getLeafcategoryid() {
        return this.leafcategoryid;
    }

    /**
     * 类目来源 0运营修改，1：商家修改，2：机器学习
     * 
     * @param classificationtype the value for classificationtype
     */
    public void setClassificationtype(Integer classificationtype) {
        this.classificationtype = classificationtype;
    }

    /**
     * 类目来源 0运营修改，1：商家修改，2：机器学习
     * 
     * @return classificationtype the value for classificationtype
     */
    public Integer getClassificationtype() {
        return this.classificationtype;
    }

    /**
     * 商家或者运营的UCID，机器为0
     * 
     * @param operator the value for operator
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * 商家或者运营的UCID，机器为0
     * 
     * @return operator the value for operator
     */
    public Long getOperator() {
        return this.operator;
    }

    /**
     * 取值0-100，可信度，默认100
     * 
     * @param confidence the value for confidence
     */
    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    /**
     * 取值0-100，可信度，默认100
     * 
     * @return confidence the value for confidence
     */
    public Integer getConfidence() {
        return this.confidence;
    }

    /**
     * 添加时间
     * 
     * @param addtime the value for addtime
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
     * 
     * @return addtime the value for addtime
     */
    public String getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public String getUpdatetime() {
        return this.updatetime;
    }

    /**
     * 对item_id+classificationtype做MD5，用于重复判断
     * 
     * @param cdtMd5 the value for cdt_md5
     */
    public void setCdtMd5(String cdtMd5) {
        this.cdtMd5 = cdtMd5;
    }

    /**
     * 对item_id+classificationtype做MD5，用于重复判断
     * 
     * @return cdtMd5 the value for cdt_md5
     */
    public String getCdtMd5() {
        return this.cdtMd5;
    }
}
