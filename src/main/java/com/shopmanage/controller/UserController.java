package com.shopmanage.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.BlPageInfo;
import com.shopmanage.entity.ResponseBean;
import com.shopmanage.entity.UserBean;
import com.shopmanage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Api(tags ="UserController",description = "用户信息")
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户列表")
    @RequestMapping("/getUserList")
    @ResponseBody
    public BlPageInfo getUserList(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pn, pageSize);
        BlPageInfo res =new BlPageInfo();
        List<UserBean> result = userService.getUserList();
        PageInfo pageInfo =new PageInfo(result);
        res.setTotal(pageInfo.getTotal());
        res.setList(pageInfo.getList());
        log.info("res"+res);
        return  res;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public  ResponseBean  updateUser(UserBean user){
        Integer data=userService.updateUser(user);
        ResponseBean<UserBean> result= new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return result;
    }

    @RequestMapping("/edit")
    public String edit(Integer uid,Model model){
        UserBean data=userService.getUserByuId(uid);
        model.addAttribute("us",data);
        log.info("data"+data);
        return "page/edit_user.html";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public  ResponseBean addUser(UserBean user){
        Integer data=userService.addUser(user);
        ResponseBean<UserBean> result=new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return result;
    }
    @RequestMapping("/queryUserByUsername")
    @ResponseBody
    public BlPageInfo queryUserByUsername(@RequestParam(value="pn",defaultValue="1")Integer pn,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,String username){
        PageHelper.startPage(pn, pageSize);
        List<UserBean> data=userService.getUserByUsername(username);
        BlPageInfo<UserBean> result=new BlPageInfo<>();
        PageInfo pageInfo =new PageInfo(data);
        result.setList(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return  result;

    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public  ResponseBean login(String username,Integer password){
        UserBean login = userService.login(username, password);
        ResponseBean<UserBean> result=new ResponseBean<>();
        result.setData(login);
        if(login==null){
            result.setCode(400);
        }
     return result;
    }

    @RequestMapping("/delUser")
    public  String delUser(Integer uid){
        Integer data=userService.delUser(uid);
        ResponseBean<UserBean> result=new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return "page/userlist.html";
    }

}