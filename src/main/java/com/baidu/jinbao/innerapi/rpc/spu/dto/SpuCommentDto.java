package com.baidu.jinbao.innerapi.rpc.spu.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SpuCommentDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * Spuid
     */
    @Protobuf(order = 2)
    private Long spuid;

    /**
     * 评论数
     */
    @Protobuf(order = 3)
    private Integer reviewcount;

    /**
     * 好评数
     */
    @Protobuf(order = 4)
    private Integer goodcount;

    /**
     * 中评数
     */
    @Protobuf(order = 5)
    private Integer medcount;

    /**
     * 差评数
     */
    @Protobuf(order = 6)
    private Integer badcount;

    /**
     * json串，评论提取tag
     */
    @Protobuf(order = 7)
    private String tags;

    /**
     * 平均分
     */
    @Protobuf(order = 8)
    private Double score;

    /**
     * 添加时间
     */
    @Protobuf(order = 9)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 10)
    private String updatetime;

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
     * Spuid
     * 
     * @param spuid the value for spuid
     */
    public void setSpuid(Long spuid) {
        this.spuid = spuid;
    }

    /**
     * Spuid
     * 
     * @return spuid the value for spuid
     */
    public Long getSpuid() {
        return this.spuid;
    }

    /**
     * 评论数
     * 
     * @param reviewcount the value for reviewcount
     */
    public void setReviewcount(Integer reviewcount) {
        this.reviewcount = reviewcount;
    }

    /**
     * 评论数
     * 
     * @return reviewcount the value for reviewcount
     */
    public Integer getReviewcount() {
        return this.reviewcount;
    }

    /**
     * 好评数
     * 
     * @param goodcount the value for goodcount
     */
    public void setGoodcount(Integer goodcount) {
        this.goodcount = goodcount;
    }

    /**
     * 好评数
     * 
     * @return goodcount the value for goodcount
     */
    public Integer getGoodcount() {
        return this.goodcount;
    }

    /**
     * 中评数
     * 
     * @param medcount the value for medcount
     */
    public void setMedcount(Integer medcount) {
        this.medcount = medcount;
    }

    /**
     * 中评数
     * 
     * @return medcount the value for medcount
     */
    public Integer getMedcount() {
        return this.medcount;
    }

    /**
     * 差评数
     * 
     * @param badcount the value for badcount
     */
    public void setBadcount(Integer badcount) {
        this.badcount = badcount;
    }

    /**
     * 差评数
     * 
     * @return badcount the value for badcount
     */
    public Integer getBadcount() {
        return this.badcount;
    }

    /**
     * json串，评论提取tag
     * 
     * @param tags the value for tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * json串，评论提取tag
     * 
     * @return tags the value for tags
     */
    public String getTags() {
        return this.tags;
    }

    /**
     * 平均分
     * 
     * @param score the value for score
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * 平均分
     * 
     * @return score the value for score
     */
    public Double getScore() {
        return this.score;
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
}
