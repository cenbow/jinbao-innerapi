package com.baidu.jinbao.mall.item.bo;

import java.util.Date;

public class ItemCdtBase implements java.io.Serializable {

    private static final long serialVersionUID = 2947488425926076598L;

    /**
     * 自增id
     */
    private Long id;

    /**
     * 商品内部id
     */
    private Long itemid;

    /**
     * 叶子类目ID
     */
    private Long leafcategoryid;

    /**
     * 类目来源 0运营修改，1：商家修改，2：机器学习
     */
    private Byte classificationtype;

    /**
     * 商家或者运营的UCID，机器为0
     */
    private Long operator;

    /**
     * 取值0-100，可信度，默认100
     */
    private Integer confidence;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 对item_id+classificationtype做MD5，用于重复判断
     */
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
    public void setClassificationtype(Byte classificationtype) {
        this.classificationtype = classificationtype;
    }

    /**
     * 类目来源 0运营修改，1：商家修改，2：机器学习
     * 
     * @return classificationtype the value for classificationtype
     */
    public Byte getClassificationtype() {
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
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 添加时间
     * 
     * @return addtime the value for addtime
     */
    public Date getAddtime() {
        return this.addtime;
    }

    /**
     * 更新时间
     * 
     * @param updatetime the value for updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 更新时间
     * 
     * @return updatetime the value for updatetime
     */
    public Date getUpdatetime() {
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
