package com.baidu.jinbao.innerapi.rpc.jinbaocommon.dto;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RegionMapDto {
    @Protobuf(order = 1)
    private Long id;

    @Protobuf(order = 2)
    private Integer cityid;

    @Protobuf(order = 3)
    private String cityname;

    @Protobuf(order = 4)
    private Integer regionid;

    @Protobuf(order = 5)
    private String regionname;

    @Protobuf(order = 6)
    private String addtime;

    @Protobuf(order = 7)
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

}
