package com.springsecurity.study.bean;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.Serializable;

/**
 * @author jhye4
 * @date 2020/11/23 11:06
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 825384782616737527L;

    private Integer id;

    private String name;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
