package com.baidu.jinbao.innerapi.category.bo;

public class BaseCmPropertyBase implements java.io.Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 前台属性名称
     */
    private String name;

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
     * 前台属性名称
     * 
     * @param name the value for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 前台属性名称
     * 
     * @return name the value for name
     */
    public String getName() {
        return this.name;
    }
}
