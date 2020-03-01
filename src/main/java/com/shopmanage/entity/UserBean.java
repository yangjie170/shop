package com.shopmanage.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户
 */
@Slf4j
@Data
public class UserBean  extends  BaseBean{
    private  Integer uid;
    private  String  username;
    private  Integer password;
    private  String name;
    private  String sex;
    private  Integer telephone;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}
