package com.shopmanage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopmanage.entity.*;
import com.shopmanage.service.CarService;
import com.shopmanage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/cart")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/create.do")
    @ResponseBody
        public Rsp insertCarGoods(@RequestParam Map<String,String> map){
            JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
            int good_id = Integer.parseInt(jsonObject.getString("goods_id"));
            int number = Integer.parseInt(jsonObject.getString("number"));
            Session session = JSONObject.toJavaObject(JSON.parseObject(jsonObject.getString("session")),Session.class);
            ProductBean productBean =productService.queryProductBypid(good_id);
            String total = Integer.toString(productBean.getShopprice()*number);
            CarGoods carGoods = new CarGoods();
        carGoods.setGoods_id(good_id);
        carGoods.setGoods_number(number);
        carGoods.setGoods_name(productBean.getPname());
        carGoods.setUid(session.getUid());
        carGoods.setSubtotal(total);
        carGoods.setImg(productBean.getPimage());
        carService.createCarGoods(carGoods);
        Rsp rsp = new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }

    @RequestMapping("/list.do")
    @ResponseBody
    public RspList carList(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));

        Session session = JSON.toJavaObject(JSONObject.parseObject(jsonObject.getString("session")),Session.class);

        List<CarGoods> carGoods = carService.selectAllCarGoods(session.getUid());
        List<CartGoods> cartGoods = new java.util.ArrayList<>(Collections.emptyList());
        int goods_price = 0;
        for (CarGoods car :carGoods) {
            CartGoods cartGood = new CartGoods(car);
            if (cartGood.getSubtotal()==null){
                cartGood.setSubtotal("0");
                goods_price+=Integer.parseInt(cartGood.getSubtotal());
            }else {
                goods_price+=Integer.parseInt(cartGood.getSubtotal());
            }
            cartGoods.add(cartGood);
        }
        CartBill cartBill = new CartBill();
        cartBill.setGoods_price(Integer.toString(goods_price));
        cartBill.setReal_goods_count(carGoods.size());
        RspList rspList = new RspList();
        RspList.Data data = new RspList.Data();

        data.setTotal(cartBill);
        data.setGoods_list(cartGoods);
        rspList.setData(data);
        rspList.setStatus(new Status(1,200,"请求成功"));

        return rspList;
    }
    @RequestMapping("/delete.do")
    @ResponseBody
    public RspDelete deleteCarGood(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        int rec_id = jsonObject.getByteValue("rec_id");

        carService.deleteCargoods(rec_id);

        RspDelete rspDelete = new RspDelete();
        CartBill cartBill = new CartBill();
        cartBill.setGoods_price("");
        cartBill.setReal_goods_count(0);
        rspDelete.setCartBill(cartBill);

        rspDelete.setStatus(new Status(1,200,"请求成功"));
        return rspDelete;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public RspUpdate updateCarGoods(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        int new_number = jsonObject.getIntValue("new_number");
        int rec_id = jsonObject.getIntValue("rec_id");
        Session session = JSON.toJavaObject(JSONObject.parseObject(jsonObject.getString("session")),Session.class);
        //查goods_id,
        CarGoods carGoods = carService.selectOneRec(rec_id);
        ProductBean p = productService.queryProductBypid(carGoods.getGoods_id());
        carService.selectAllCarGoods(session.getUid());
        String subtotal = Integer.toString(p.getShopprice()*new_number);
        carService.updateCarGoods(new_number,rec_id,subtotal);

        RspUpdate rspUpdate = new RspUpdate();
        String goods_price = Integer.toString(p.getShopprice()*new_number);
        CartBill cartBill = new CartBill();
        cartBill.setReal_goods_count(new_number);
        cartBill.setGoods_price(goods_price);
        rspUpdate.setTotal(cartBill);
        rspUpdate.setStatus(new Status(1,200,"请求成功"));
        return rspUpdate;
    }

    public static class Rsp extends Response{

    }


    public static class RspList extends Response{
        Data data;
        public Data getData() {
            return data;
        }
        public void setData(Data data) {
            this.data = data;
        }
        public static class Data{
            private List<CartGoods> goods_list;
            private CartBill total;
            public List<CartGoods> getGoods_list() {
                return goods_list;
            }
            public void setGoods_list(List<CartGoods> goods_list) {
                this.goods_list = goods_list;
            }

            public CartBill getTotal() {
                return total;
            }

            public void setTotal(CartBill total) {
                this.total = total;
            }
        }
    }

    public static class  RspDelete extends Response{
        CartBill cartBill;

        public CartBill getCartBill() {
            return cartBill;
        }

        public void setCartBill(CartBill cartBill) {
            this.cartBill = cartBill;
        }
    }

    public static class RspUpdate extends Response{
        CartBill total;

        public CartBill getTotal() {
            return total;
        }

        public void setTotal(CartBill total) {
            this.total = total;
        }
    }
}
