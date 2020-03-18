package com.shopmanage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.BlPageInfo;
import com.shopmanage.entity.OderBean;
import com.shopmanage.entity.ProductBean;
import com.shopmanage.entity.ResponseBean;
import com.shopmanage.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
public class OderController {
    @Autowired
    private OderService oderService;
    //增删改查
    //面向管理员的订单管理
    //增,按照规则增加订单,?
    @RequestMapping("/addOrder.do")
    public String addOrder(OderBean oderBean){
       oderService.addOrder(oderBean);
       return "page/orders";
    }

    //删,删除一个或多个不需要的订单从数据库中
    @DeleteMapping("/deletOrder.do")
    @ResponseBody
    public String deletOrder(int oid){
        return "page/orders";
    }

    //改,特殊情况时修改订单的所有可修改信息
    @RequestMapping("/updateOrder.do")
    @ResponseBody
    public ResponseBean updateOrder(OderBean oderBean){
        if(Objects.isNull(oderBean)){
            return null;
        }
        ResponseBean responseBean = new ResponseBean();
        int data = oderService.updateOrder(oderBean);
        if(data==0){
            responseBean.setData(null);
            responseBean.setCode(400);
        }else {
            responseBean.setData(data);
        }
        return  responseBean;
    }

    //查询所有订单
    @ResponseBody
    @RequestMapping("/queryAll.do")
    public BlPageInfo queryAll(ProductBean productBean,@RequestParam(defaultValue = "1",value = "pn") Integer page,@RequestParam(defaultValue = "10",value = "pageSize")Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<OderBean> list = oderService.selectAllOrder();
        PageInfo<OderBean> pageInfo = new PageInfo<>(list);
        BlPageInfo blPageInfo =new BlPageInfo();
        blPageInfo.setTotal(pageInfo.getTotal());
        blPageInfo.setList(pageInfo.getList());
        return blPageInfo;
    }

    //查,管理人员可按条件查看满足条件的订单
    @ResponseBody
    @RequestMapping("/selectByMap.do")
    public BlPageInfo selectByMap(OderBean oderBean,@RequestParam(defaultValue = "1",value = "pn") Integer page,@RequestParam(defaultValue = "10",value = "pageSize")Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<OderBean> list = oderService.selectByMap(oderBean);
        PageInfo<OderBean> pageInfo = new PageInfo<>(list);
        BlPageInfo blPageInfo =new BlPageInfo();
        blPageInfo.setTotal(pageInfo.getTotal());
        blPageInfo.setList(pageInfo.getList());
        return blPageInfo;
    }

    //模糊查,管理员无准确条件，可通过total模糊查询订单
    @ResponseBody
    @RequestMapping("/selectLike")
    public List<OderBean> selectLike(int s){
        List<OderBean> list = oderService.selectLike(s);
        return list;
    }

    @RequestMapping("/editOrder.do")
    public  String editOrder(String oid, Model model){
        OderBean list = oderService.editOrder(oid);
        model.addAttribute("order",list);
        return  "page/edit_order.html";
    }




}
