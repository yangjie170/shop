package com.shopmanage.service.impl;
import com.shopmanage.entity.UserBean;
import com.shopmanage.mapper.UserMapper;
import com.shopmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserBean> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public Integer updateUser(UserBean user) {

        return userMapper.updateUser(user);
    }

    @Override
    public UserBean getUserByuId(Integer uid) {
        return userMapper.getUserByuId(uid);
    }

    @Override
    public Integer addUser(UserBean user) {
        return userMapper.addUser(user);
    }

    @Override
    public  List<UserBean> getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer delUser(Integer uid) {
        return userMapper.delUser(uid);
    }

    @Override
    public UserBean login(String username, Integer password) {

            return userMapper.login(username,password);
    }
}
