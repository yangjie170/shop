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
}
