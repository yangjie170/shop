package com.shopmanage.entity;

public class CartBill {
    //货物价格
    private String goods_price;
    //货物数量
    private int real_goods_count;

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public int getReal_goods_count() {
        return real_goods_count;
    }

    public void setReal_goods_count(int real_goods_count) {
        this.real_goods_count = real_goods_count;
    }
}
