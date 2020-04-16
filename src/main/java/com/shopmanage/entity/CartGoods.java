package com.shopmanage.entity;

public class CartGoods {
    private String goods_name;
    private int rec_id;
    private int goods_id;
    private int goods_number;
    private Picture img;
    private String subtotal;

    public CartGoods() {
    }

    public CartGoods(CarGoods carGoods) {
        Picture picture = new Picture();
        picture.setThumb(carGoods.getImg());
        picture.setSmall(carGoods.getImg());
        picture.setUrl(carGoods.getImg());
        this.goods_name = carGoods.getGoods_name();
        this.rec_id = carGoods.getRec_id();
        this.goods_id = carGoods.getGoods_id();
        this.goods_number = carGoods.getGoods_number();
        this.img = picture;
        this.subtotal = carGoods.getSubtotal();
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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

    public int getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public Picture getImg() {
        return img;
    }

    public void setImg(Picture img) {
        this.img = img;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
