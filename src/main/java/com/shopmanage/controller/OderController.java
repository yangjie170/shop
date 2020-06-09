package com.shopmanage.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.OderBeanDTO;
import com.shopmanage.service.*;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/orders")
@Transactional
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
    public String deletOrder(String oid){

        return oderService.deletOrder(oid)>0?"true":"failed";
    }

    //改,特殊情况时修改订单的所有可修改信息
    @RequestMapping("/updateOrder.do")
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
    public Rsp getOrderByUid(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        String type = jsonObject.getString("type");
        Session session = jsonObject.getObject("session",Session.class);

       //List<OderBean> list = oderService.queryOrderByUid(session.getUid());
        OderBean oderBean = new OderBean();
        oderBean.setUid(session.getUid().toString());
        oderBean.setState(type.equals("finished")?1:0);
        List<OderBean> oderBeans = oderService.selectByMap(oderBean);
        //根据付款状态的订单查询
        List<OderGoods> oderGoodsList =oderGoodsService.queryOderGoodsByOderState(Integer.toString(type.equals("finished")?1:0));
        List<OderGoods> oderGoodsList1 = new ArrayList<>();
        //订单中货物查询
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
            oderBeanDTO.setOrder_info(new OrderInfo("已付款",1,1,"hzss","312"));
            oderBeanDTO.setFormated_shipping_fee("123");
            list2.add(oderBeanDTO);
        }

        Rsp rsp = new Rsp();
        rsp.setData(list2);
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }

    @RequestMapping("/done")
    public Rsp done(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session",Session.class);

        OderBean oderBean = new OderBean();

        String oid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        Date nowDate = new Date();
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNow = simpleDateFormat.format(nowDate);*/
        List<Address> addresses = addressService.selectOneAddress(session.getUid());
        Address address = addresses.get(0);
        List<CarGoods> list = carService.selectAllCarGoods(session.getUid());
        int total =0;
        for (CarGoods c: list
             ) {
            total+=Integer.parseInt(c.getSubtotal());
        }
        oderBean.setState(0);
        oderBean.setUid(session.getUid().toString());
        oderBean.setTelephone(address.getMobile());
        oderBean.setOrdertime(nowDate);
        oderBean.setOid(oid);
        oderBean.setAddress(address.getAddress());
        oderBean.setTotal(total);
        oderBean.setName(address.getAddress());


        carService.deleteALlCarGoods(session.getUid());

        oderService.addOrder(oderBean);
        Rsp rsp = new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }


    @RequestMapping("/cancel")
    public Rsp cancel(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        String order_sn = jsonObject.getString("order_sn");
        Session session = jsonObject.getObject("session",Session.class);

        oderService.deletOrder(order_sn);
        Rsp rsp = new Rsp();
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }


    @RequestMapping("/checkOrder")
    public RspCheck checkOrder(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session",Session.class);
        System.out.println(session.toString());
        RspCheck rspCheck = new RspCheck();
        RspCheck.Data data = new RspCheck.Data();
        /*
        * 测试
        * */
        List<Address> addresses = addressService.selectOneAddress(session.getUid());
        Address address = addresses.get(0);
        List<Payment> paymentList = new ArrayList<>();
        List<Shipping> shippings = new ArrayList<>();
        List<CartGoods> cartGoods = new ArrayList<>();
        Picture picture = new Picture();
        for(int i = 1;i<5;i++){
            Payment payment = new Payment(i,"支付方式:"+i,"优惠："+i,"支付代码+"+i);
            paymentList.add(payment);
            Shipping shipping = new Shipping(i,"派送方式:"+i,""+i);
            shippings.add(shipping);
        }
        List<CarGoods> list = carService.selectAllCarGoods(session.getUid());
        for (CarGoods c: list
             ) {
            CartGoods cartGood = new CartGoods(c);
            cartGoods.add(cartGood);
            System.out.println(c.toString());
        }
 //       CarGoods carGoods1 = new CarGoods(1,1,1,"13r",1,"200","products/1/c_0014.jpg");
        for (CartGoods c: cartGoods
             ) {
            System.out.println(c.toString());
        }
        data.setGoods_list(cartGoods);
        data.setPayment_list(paymentList);
        data.setShipping_list(shippings);
        data.setConsignee(address);
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
            private List<CartGoods> goods_list;
            private Address consignee;
            private List<Shipping> shipping_list;
            private List<Payment> payment_list;

            public List<CartGoods> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<CartGoods> goods_list) {
                this.goods_list = goods_list;
            }

            public Address getConsignee() {
                return consignee;
            }

            public void setConsignee(Address consignee) {
                this.consignee = consignee;
            }

            public List<Shipping> getShipping_list() {
                return shipping_list;
            }

            public void setShipping_list(List<Shipping> shipping_list) {
                this.shipping_list = shipping_list;
            }

            public List<Payment> getPayment_list() {
                return payment_list;
            }

            public void setPayment_list(List<Payment> payment_list) {
                this.payment_list = payment_list;
            }
        }

    }

}
