package com.shopmanage.entity.DTO;

import com.shopmanage.entity.OderBean;
import com.shopmanage.entity.OderGoods;
import com.shopmanage.entity.OrderInfo;

import java.util.List;

public class OderBeanDTO extends OderBean {
    private List<OderGoods> goods_list;
    private OrderInfo order_info;
    private String formated_shipping_fee;

    public OderBeanDTO() {
    }

    public void initOderDTO(OderBean oderBean){
        this.setUid(oderBean.getUid());
        this.setAddress(oderBean.getAddress());
        this.setState(oderBean.getState());
        this.setName(oderBean.getName());
        this.setOid(oderBean.getOid());
        this.setOrdertime(oderBean.getOrdertime());
        this.setTelephone(oderBean.getTelephone());
        this.setTotal(oderBean.getTotal());
    }
    public OderBeanDTO(List<OderGoods> goods_list, OrderInfo order_info, String formated_shipping_fee) {
        this.goods_list = goods_list;
        this.order_info = order_info;
        this.formated_shipping_fee = formated_shipping_fee;
    }

    public String getFormated_shipping_fee() {
        return formated_shipping_fee;
    }

    public void setFormated_shipping_fee(String formated_shipping_fee) {
        this.formated_shipping_fee = formated_shipping_fee;
    }

    public List<OderGoods> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<OderGoods> goods_list) {
        this.goods_list = goods_list;
    }

    public OrderInfo getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfo order_info) {
        this.order_info = order_info;
    }
}

