package com.shopmanage.service;

import com.shopmanage.entity.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> getUserList();
    Integer updateUser(UserBean user);
    UserBean getUserByuId(Integer uid);
    Integer addUser(UserBean user);
    List<UserBean> getUserByUsername(String username);
    Integer delUser(Integer uid);

}
