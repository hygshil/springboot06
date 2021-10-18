package com.xiexin.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * store
 * @author 
 */
@ToString  //lombok的插件的注解，不用写toString
@Data
public class Store implements Serializable {
    private Integer id;

    private String name;

    private String address;

    private String manager;

    private static final long serialVersionUID = 1L;


}