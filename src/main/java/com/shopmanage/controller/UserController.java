package com.shopmanage.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.UserDTO;
import com.shopmanage.service.OderService;
import com.shopmanage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Api(tags ="UserController",description = "用户信息")
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OderService oderService;
    //需要一个session，记录用户的订单，购物车，地址数据。
    //服务器根据客户端的header带过来的sessionId找到一个session,将session里的信息带给客户端,叫做session的保持
    //
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
        //log.info("res"+res);
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
        //log.info("data"+data);
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

    @RequestMapping("login")
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

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public Rsp login(@RequestParam Map<String, String> map,HttpSession session){
        JSON json = JSON.parseObject(map.get("json"));
        System.out.println(json);
        UserBean user = JSONObject.toJavaObject(json,UserBean.class);
        //创建一个响应实例
        Rsp result = new Rsp();
        if(user.getUsername()==null||user.getPassword()==null){
            result.setStatus(new Status(0,444,"用户名和密码不能为空"));
        }
        UserBean loginUser = userService.login(user.getUsername(), user.getPassword());
        OrderNumber oderNum = new OrderNumber();
        if(loginUser!=null){
            List<OderBean> o = oderService.queryOrderByUid(loginUser.getUid());
            List<OrderNum> orderNums = oderService.selectOrderNum(loginUser.getUid());
            Map<Integer,Integer> map1 = new HashMap<>();
            for (OrderNum orderNum:orderNums) {
                map1.put(orderNum.getState(),orderNum.getCount());
            }
            int await_pay = 0;
            int finished= 0;
            if(orderNums.size()>0){
                try{
                    await_pay = map1.get(0);
                    finished = map1.get(1);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    finished =0;
                }
            }
            oderNum.initNum(await_pay,await_pay,finished,finished);
            UserDTO userDTO = new UserDTO(loginUser,oderNum);
            result.setData(new Rsp.Data(new Session(loginUser.getUid(),session.getId()),userDTO));
            result.setStatus(new Status(1,200,"登录成功"));
        }else {
            result.setStatus(new Status(0,404,"用户名或密码错误，请重试"));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/info")
    public Rsp userInfo(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session",Session.class);
        OrderNumber oderNum = new OrderNumber();
        List<OrderNum> orderNums = oderService.selectOrderNum(session.getUid());
        Map<Integer,Integer> map1 = new HashMap<>();
        for (OrderNum orderNum:orderNums) {
            map1.put(orderNum.getState(),orderNum.getCount());
        }
        int await_pay = 0;
        int finished= 0;
        if(orderNums.size()>0){
            try{
                await_pay = map1.get(0);
                finished = map1.get(1);
            }catch(Exception e){
                System.out.println(e.getMessage());
                finished =0;
            }
        }
        oderNum.initNum(await_pay,await_pay,finished,finished);
        Rsp rsp = new Rsp();
        rsp.setData(new Rsp.Data(session,new UserDTO(userService.getUserByuId(session.getUid()),oderNum)));
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public  Rsp  register(@RequestParam Map<String, String> map,HttpSession session){

        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        String userName=jsonObject.getString("name");
        String userPassword = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        ResponseBean<UserBean> result= new ResponseBean<>();

        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setTelephone(Integer.parseInt(email));
        userBean.setPassword(Integer.parseInt(userPassword));
        UserBean isExist = userService.existUser(userName);

        Rsp rsp = new Rsp();
        if (isExist==null){
            int i = userService.register(userBean);
            UserBean registerUser = userService.existUser(userName);
            result.setMessage(i==1?"注册成功":"注册失败");
            rsp.setStatus(new Status(1,200,"请求成功"));
            Session session1 = new Session(registerUser.getUid(),session.getId());
            OrderNumber oderNum = new OrderNumber();
            UserDTO userDTO = new UserDTO(registerUser,oderNum);
            rsp.setData(new Rsp.Data(session1, userDTO));
        }else {
            rsp.setStatus(new Status(1,200,"请求成功"));
            Session session1 = new Session(isExist.getUid(),session.getId());
            OrderNumber oderNum = new OrderNumber();
            List<OrderNum> orderNums= oderService.selectOrderNum(isExist.getUid());
            Map<Integer,Integer> map1 = new HashMap<>();
            for (OrderNum orderNum:orderNums) {
                map1.put(orderNum.getState(),orderNum.getCount());
            }
            int await_pay = 0;
            int finished= 0;
            if(orderNums.size()>0){
                try{
                    await_pay = map1.get(0);
                    finished = map1.get(1);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    finished =0;
                }
            }
            oderNum.initNum(await_pay,await_pay,finished,finished);
            UserDTO userDTO = new UserDTO(isExist,oderNum);
            rsp.setData(new Rsp.Data(session1,userDTO));
        }
        return rsp;
    }


    public static class Rsp extends Response{
        Data data;
        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }
        public static  class Data{
            public Data(Session session, UserDTO user) {
                this.session = session;
                this.user = user;
            }
            private Session session;
            private UserDTO user;

            public Session getSession() {
                return session;
            }

            public void setSession(Session session) {
                this.session = session;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }
        }
    }

    public static List<Integer> add(List<Integer> list){
        List<Integer> chushihuaList = Arrays.asList(0,0);
        if (list.size()==0){
            return chushihuaList;
        }
        int i = list.size()-1;
        for (Integer integer : list) {
            chushihuaList.set(i, integer);
            i--;
        }
        return chushihuaList;
    }

}