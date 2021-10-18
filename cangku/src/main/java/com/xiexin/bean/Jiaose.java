package com.xiexin.bean;

import java.io.Serializable;

/**
 * jiaose
 * @author 
 */
public class Jiaose implements Serializable {
    private Integer id;

    private String jname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}