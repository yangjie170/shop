package com.shopmanage.mapper;

import com.shopmanage.entity.UserBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author cp
 */

public interface UserMapper {
    /**
     * 查询所有的user
     * @return
     */
    @Select("select * from user order by uid desc")
    List<UserBean> getUserList();

    /**
     * 增加user
     * @param user
     * @return
     */
    @Insert("insert into user(username,password,name,telephone,sex)  values(#{username},#{password},#{name},#{telephone},#{sex})")
    Integer addUser(UserBean user);

    /**
     * 修改user
     * @param user
     * @return
     */
     @Update("update user set username=#{username},password=#{password},name=#{name},telephone=#{telephone},sex=#{sex} where uid=#{uid} ")
       Integer updateUser(UserBean user);

    /**
     * 删除user
     * @param uid
     * @return
     */
     @Delete("delete from user where uid=#{uid}")
     Integer delUser(Integer uid);

    /**
     * 通过username查询
     * @param username
     * @return
     */
     @Select("select * from user where username=#{username}")
     List<UserBean> getUserByUsername(String username);

    /**
     * 统计用户数量
     * @return
     */
     @Select("select count(0) from user ")
     Integer countUser();

    /**
     * 通过uid查询
     * @param uid
     * @return
     */
    @Select("select * from user where uid=#{uid}")
    UserBean getUserByuId(Integer uid);

    @Select("select * from user where username=#{username} and password=#{password}")
    UserBean login(String username, Integer password);

    @Insert("insert into user(username,password,telephone)  values(#{username},#{password},#{telephone})")
    UserBean register(UserBean user);


    @Select("select * from user where username=#{username}")
    UserBean existUser(UserBean user);

}
