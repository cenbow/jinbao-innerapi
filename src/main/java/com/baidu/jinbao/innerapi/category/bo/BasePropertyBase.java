/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

public class BasePropertyBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 属性中文名
     */
    private String propertyName;

    /**
     * 属性英文名
     */
    private String enName;

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
     * 属性中文名
     * 
     * @param propertyName the value for property_name
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 属性中文名
     * 
     * @return propertyName the value for property_name
     */
    public String getPropertyName() {
        return this.propertyName;
    }

    /**
     * 属性英文名
     * 
     * @param enName the value for en_name
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 属性英文名
     * 
     * @return enName the value for en_name
     */
    public String getEnName() {
        return this.enName;
    }
}
