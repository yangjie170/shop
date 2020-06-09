package com.shopmanage.entity;

public class Shipping {
    private int shipping_id;
    private String shipping_name;
    private String format_shipping_fee;

    public Shipping() {
    }

    public Shipping(int shipping_id, String shipping_name, String format_shipping_fee) {
        this.shipping_id = shipping_id;
        this.shipping_name = shipping_name;
        this.format_shipping_fee = format_shipping_fee;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getFormat_shipping_fee() {
        return format_shipping_fee;
    }

    public void setFormat_shipping_fee(String format_shipping_fee) {
        this.format_shipping_fee = format_shipping_fee;
    }
}
