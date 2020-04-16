package com.shopmanage.entity;

public class CarGoods {
    private int uid;
    private int rec_id;
    private int goods_id;
    private String goods_name;
    private int goods_number;
    private String subtotal;
    private String img;

    public CarGoods(Integer uid, Integer rec_id, Integer goods_id, String goods_name, Integer goods_number, String subtotal, String img) {
        this.uid = uid;
        this.rec_id = rec_id;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_number = goods_number;
        this.subtotal = subtotal;
        this.img = img;
    }

    public CarGoods() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
