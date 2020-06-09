package com.shopmanage.controller;

import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.ProductDTO;
import com.shopmanage.service.CategoryService;
import com.shopmanage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/data")
    @ResponseBody
    public RspBanner getCategory(){
        RspBanner rspBanner = new RspBanner();
        List<Banner> banners = new ArrayList<>();
        List<ProductDTO> productDTOS = new LinkedList<>();
        List<ProductBean> list = productService.queryByCategoryId(1,"desc");
        //促销商品
        for (ProductBean productBean :list){
            Picture picture = new Picture();
            ProductDTO productDTO = new ProductDTO();
            picture.setUrl(productBean.getPimage());
            picture.setSmall(productBean.getPimage());
            picture.setThumb(productBean.getPimage());
            productDTO.initProduct(productBean);
            productDTO.setPicture(picture);
            productDTOS.add(productDTO);
        }

        //轮播图

        List<ProductBean> list1 = productService.queryByCategoryId(2,"desc");
        for (ProductBean p: list1) {
            Banner banner = new Banner();
            Picture picture = new Picture();
            picture.setUrl(p.getPimage());
            picture.setSmall(p.getPimage());
            picture.setThumb(p.getPimage());
            System.out.println(picture.getSmall());
            banner.setPhoto(picture);
            banners.add(banner);
        }
        RspBanner.Data data = new RspBanner.Data();
        data.setPlayer(banners);
        data.setPromote_goods(productDTOS);
        rspBanner.setData(data);
        rspBanner.setStatus(new Status(1,200,"请求成功"));
        return rspBanner;
    }

    @RequestMapping("/category")
    @ResponseBody
    public Rsp initData(){
        //首页数据
        List<CategoryBean> categoryBeans = categoryService.getAllCategory();
        List<CategoryHome> list1 = new ArrayList<>(Collections.emptyList());
        String a ="desc";
        for (CategoryBean c : categoryBeans) {
            CategoryHome categoryHome = new CategoryHome();
            List<ProductBean> productBeans = productService.queryByCategoryId(c.getCid(),a);
            List<ProductDTO> list = new ArrayList<>();
            for (ProductBean p :productBeans) {
                ProductDTO productDTO = new ProductDTO();
                Picture picture = new Picture();
                picture.setThumb(p.getPimage());
                picture.setSmall(p.getPimage());
                picture.setUrl(p.getPimage());
                productDTO.setPicture(picture);
                productDTO.initProduct(p);
                list.add(productDTO);
                categoryHome.setCid(p.getCid());
                categoryHome.setCname(p.getCname());
            }
            categoryHome.setGoods(list);
            categoryHome.setCid(c.getCid());
            categoryHome.setCname(c.getCname());
            list1.add(categoryHome);
        }

        Rsp rsp =new Rsp();
        rsp.setData(list1);
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }

    public static class Rsp extends Response{
        List<CategoryHome> data;

        public List<CategoryHome> getData() {
            return data;
        }

        public void setData(List<CategoryHome> data) {
            this.data = data;
        }
    }
    //轮播图响应
    public static class RspBanner extends Response{
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class Data{
            private List<Banner> player;
            private List<ProductDTO> promote_goods;

            public List<ProductDTO> getPromote_goods() {
                return promote_goods;
            }

            public void setPromote_goods(List<ProductDTO> promote_goods) {
                this.promote_goods = promote_goods;
            }

            public List<Banner> getPlayer() {
                return player;
            }

            public void setPlayer(List<Banner> player) {
                this.player = player;
            }
        }
    }
}
