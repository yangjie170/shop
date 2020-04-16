package com.shopmanage.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.CategoryPrimary;
import com.shopmanage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**************************************************
 * copyright (c) 2020, www.winan.com.cn All Rights Reserved.
 * created by peng.cao
 * date:       2020/3/1 0001
 * since:      1.0.0.1
 * description:
 *
 **************************************************/
@Controller
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;



    @RequestMapping("/getAllCategory")
    @ResponseBody
    public BlPageInfo getAllCategory(@RequestParam(value="pn",defaultValue="1")Integer pn,
                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pn,pageSize);
        List<CategoryBean> data = categoryService.getAllCategory();
        PageInfo<CategoryBean> objectPageInfo = new PageInfo<>(data);
        BlPageInfo blPageInfo =new BlPageInfo();
        blPageInfo.setTotal(objectPageInfo.getTotal());
        blPageInfo.setList(objectPageInfo.getList());
        return blPageInfo;
    }

    @RequestMapping("/addCategoryBean")
    @ResponseBody
    public ResponseBean addCategoryBean(CategoryBean categoryBean) {
        if(Objects.isNull(categoryBean)){
            return  null;
        }
        ResponseBean<CategoryBean> result = new ResponseBean<>();
        CategoryBean categoryBean1 = categoryService.addCategoryBean(categoryBean);
        if(!Objects.isNull(categoryBean1)){
            result.setCode(400);
        }
        return result;
    }

    @RequestMapping("/updateCategoryBean")
    @ResponseBody
    public ResponseBean updateCategoryBean(CategoryBean categoryBean) {
        if(Objects.isNull(categoryBean)){
            return  null;
        }
        ResponseBean<CategoryBean> result = new ResponseBean<>();
        CategoryBean updateCategoryBean = categoryService.updateCategoryBean(categoryBean);
        if(!Objects.isNull(updateCategoryBean)){
            result.setCode(400);
        }
        return result;

    }

    @RequestMapping("/deleteCategoryBean")
    @ResponseBody
    public ResponseBean deleteCategoryBean(String cid) {

        int data = categoryService.deleteCategoryBean(cid);
        ResponseBean responseBean = new ResponseBean();
        if(data==0){
            responseBean.setCode(400);
        }
        return responseBean;
    }

    @RequestMapping("/getCategory.do")
    @ResponseBody
    public Rsp getCategory(){
        List<CategoryBean> list =  categoryService.getAllCategory();
        List<CategoryPrimary> categoryPrimaryList = new ArrayList<>(Collections.emptyList());
        for (CategoryBean c :list) {
            CategoryPrimary categoryPrimary = new CategoryPrimary();
            categoryPrimary.setCid(c.getCid());
            categoryPrimary.setCname(c.getCname());
            List<CategoryBean> list3 = new ArrayList<>(Collections.emptyList());
            list3.add(c);
            categoryPrimary.setChildren(list3);
            categoryPrimaryList.add(categoryPrimary);
        }
        Rsp rsp =new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        rsp.setData(categoryPrimaryList);
        return rsp;
    }

    public static class Rsp extends Response{
        private List<CategoryPrimary> data;
        public List<CategoryPrimary> getData() {
            return data;
        }
        public void setData(List<CategoryPrimary> data) {
            this.data = data;
        }
        }

}
