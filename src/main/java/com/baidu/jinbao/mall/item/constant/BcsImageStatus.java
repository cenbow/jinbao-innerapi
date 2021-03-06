package com.baidu.jinbao.mall.item.constant;

/**
 * BcsCspuImage相关常量定义
 * 
 * @author cgd
 * @date 2015年6月23日 上午11:23:30
 */
public enum BcsImageStatus {

    DOWNLOAD_TO_LOCAL(Byte.valueOf("0"), "已下载到本地"), LOADING(Byte.valueOf("1"), "正在下载"), DOWNLOAD_ERROR(Byte
            .valueOf("2"), "下载出错"), NOT_STARTED(Byte.valueOf("3"), "未开始");

    private Byte id;
    private String desc;

    private BcsImageStatus(Byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static BcsImageStatus get(Number id) {
        if (null == id) {
            return null;
        }
        for (BcsImageStatus temp : BcsImageStatus.values()) {
            if (temp.getId().equals(id.byteValue())) {
                return temp;
            }
        }
        return null;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
