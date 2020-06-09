package com.shopmanage.entity.DTO;

import com.shopmanage.entity.UserBean;

import java.io.Serializable;

public class RequstParameter  implements Serializable {
    private String name;
    private String value;

    public RequstParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
