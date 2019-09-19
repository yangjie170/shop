package com.shopmanage.controller;

import com.Config;
import com.shopmanage.entity.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cp
 */
@ Controller
public class AdminController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean login(@RequestParam(value = "userName", defaultValue = "") String userName
            , @RequestParam(value = "password", defaultValue = "") String password) {
        ResponseBean result = new ResponseBean<>();
        int code = 400;
        String message = "";
        String passwd = Config.account.get(userName);
        if (null != passwd) {
            if (password.equals(passwd)) {
                code = 200;
                message = "登录成功";

                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                request.getSession().setAttribute("userName", userName);
            } else {
                message = "密码错误";
                code = 401;
            }
        } else {
            message = "无此用户";
            code = 400;
        }
        result.setCode(code);
        result.setData(null);
        result.setMessage(message);

        return result;
    }
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        session.invalidate();
        return "login";
    }

    @RequestMapping("/login")
    public String login(){
        return "page/login.html";
    }
}
