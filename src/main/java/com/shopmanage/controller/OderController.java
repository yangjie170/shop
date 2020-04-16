package com.shopmanage.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.OderBeanDTO;
import com.shopmanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
public class OderController {
    @Autowired
    private OderService oderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OderGoodsService oderGoodsService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CarService carService;

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

    @RequestMapping("/queryOrderByUid")
    @ResponseBody
    public Rsp getOrderByUid(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        String type = jsonObject.getString("type");
        Session session = jsonObject.getObject("session",Session.class);

       //List<OderBean> list = oderService.queryOrderByUid(session.getUid());
        OderBean oderBean = new OderBean();
        oderBean.setUid(session.getUid().toString());
        oderBean.setState(type.equals("finished") ?1:0);
        List<OderBean> oderBeans = oderService.selectByMap(oderBean);
        List<OderGoods> oderGoodsList =oderGoodsService.queryOderGoodsByOderState(Integer.toString(type.equals("finished")?1:0));
        List<OderGoods> oderGoodsList1 = new ArrayList<>();
        for (OderGoods oderGoods : oderGoodsList){
            Picture picture = new Picture();
            String url=productService.queryProductBypid(oderGoods.getGoods_id()).getPimage();
            picture.setUrl(url);
            picture.setSmall(url);
            picture.setThumb(url);
            oderGoods.setImg(picture);
            oderGoodsList1.add(oderGoods);
        }
        List<OderBeanDTO> list2 = new ArrayList<>();
        //oderben原始类，oderbeanDTO新增两个属性goods,info
        for (OderBean oderBean1: oderBeans) {
            OderBeanDTO oderBeanDTO = new OderBeanDTO();
            oderBeanDTO.initOderDTO(oderBean1);
            oderBeanDTO.setGoods_list(oderGoodsList1);
            oderBeanDTO.setOrder_info(new OrderInfo("已付款",1,1,"w","312"));
            list2.add(oderBeanDTO);
        }

        Rsp rsp = new Rsp();
        rsp.setData(list2);
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }

    @RequestMapping("/done")
    @ResponseBody
    public Rsp done(@RequestParam Map<String,String> map){
        Rsp rsp = new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }


    @ResponseBody
    @RequestMapping("/cancel")
    public Rsp cancel(@RequestParam Map<String,String> map){
        Rsp rsp = new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }


    @ResponseBody
    @RequestMapping("/checkOrder")
    public RspCheck checkOrder(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = JSONObject.toJavaObject(jsonObject,Session.class);
        Address address = addressService.selectOneAddress(session.getUid());
        RspCheck rspCheck = new RspCheck();
        RspCheck.Data data = new RspCheck.Data();
        data.setConsignee(address);
        List<Payment> paymentList = new ArrayList<>();
        List<Shipping> shippings = new ArrayList<>();
        for(int i = 1;i<5;i++){
            Payment payment = new Payment(i,"支付方式:"+i,"优惠："+i,"支付代码+"+i);
            Shipping shipping = new Shipping(i,"shippingname:"+i,"fee :"+i);
            paymentList.add(payment);
            shippings.add(shipping);
        }
        List<CarGoods> carGoods = carService.selectAllCarGoods(session.getUid());
        data.setGoodsList(carGoods);
        data.setPaymentList(paymentList);
        data.setShippingList(shippings);
        rspCheck.setData(data);
        rspCheck.setStatus(new Status(1,200,"请求成功"));
        return rspCheck;
    }

    public static class Rsp extends Response{
        private List<OderBeanDTO> data;
        private ProductController.Paginated paginated;

        public ProductController.Paginated getPaginated() {
            return paginated;
        }

        public void setPaginated(ProductController.Paginated paginated) {
            this.paginated = paginated;
        }

        public List<OderBeanDTO> getData() {
            return data;
        }

        public void setData(List<OderBeanDTO> data) {
            this.data = data;
        }
    }

    public static class RspCheck extends Response{

        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class  Data{
            private List<CarGoods> goodsList;
            private Address consignee;
            private List<Shipping> shippingList;
            private List<Payment> paymentList;

            public List<CarGoods> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<CarGoods> goodsList) {
                this.goodsList = goodsList;
            }

            public Address getConsignee() {
                return consignee;
            }

            public void setConsignee(Address consignee) {
                this.consignee = consignee;
            }

            public List<Shipping> getShippingList() {
                return shippingList;
            }

            public void setShippingList(List<Shipping> shippingList) {
                this.shippingList = shippingList;
            }

            public List<Payment> getPaymentList() {
                return paymentList;
            }

            public void setPaymentList(List<Payment> paymentList) {
                this.paymentList = paymentList;
            }
        }

    }

}
