package com.shopmanage.entity;

public class OderGoods {
    private int goods_id;
    private String name;
    private int goods_number;
    private String subtotal;
    private String formated_shop_price;
    private Picture img;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getFormated_shop_price() {
        return formated_shop_price;
    }

    public void setFormated_shop_price(String formated_shop_price) {
        this.formated_shop_price = formated_shop_price;
    }

    public Picture getImg() {
        return img;
    }

    public void setImg(Picture img) {
        this.img = img;
    }
}
