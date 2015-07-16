/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.jinbao.innerapi.category.bo;

import java.util.Date;

public class BaseCmValueBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前台属性值名称
     */
    private String value;

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
     * 前台属性值名称
     * 
     * @param value the value for value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 前台属性值名称
     * 
     * @return value the value for value
     */
    public String getValue() {
        return this.value;
    }
}
