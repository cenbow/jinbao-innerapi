package com.baidu.jinbao.innerapi.category.bo;

public class BaseValBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 属性值
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
     * 属性值
     * 
     * @param value the value for value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 属性值
     * 
     * @return value the value for value
     */
    public String getValue() {
        return this.value;
    }
}
