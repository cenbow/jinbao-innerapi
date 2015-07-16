package com.baidu.jinbao.innerapi.rpc.spu.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class CspuLevelDto {

    /**
     * 自增id
     */
    @Protobuf(order = 1)
    private Long id;

    /**
     * cspuid
     */
    @Protobuf(order = 2)
    private Long cspuid;
    
    /**
     * 等级，用于对相应挂靠sku，采取不同更新策略
     */
    @Protobuf(order = 3)
    private Long level;
    /**
     * 添加时间
     */
    @Protobuf(order = 4)
    private String addtime;

    /**
     * 更新时间
     */
    @Protobuf(order = 5)
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
     * cspuid
     * 
     * @param cspuid the value for cspuid
     */
    public void setCspuid(Long cspuid) {
        this.cspuid = cspuid;
    }

    /**
     * cspuid
     * 
     * @return cspuid the value for cspuid
     */
    public Long getCspuid() {
        return this.cspuid;
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
