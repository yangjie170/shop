package com.shopmanage.controller;

import com.shopmanage.util.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IpController {

    @RequestMapping(value = "/getIp")
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }


}
